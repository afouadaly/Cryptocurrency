import java.util.*;

public class Graph {

    final private HashMap<Node, Set<Node>> adjacencyList;
    // to prevent a user from announcing a transaction twice
    public HashMap<Node, Transaction> callCheck;
    // block size or number of transactions to form a block
    final private int transactionQuota = 5;

    public HashMap<Node, Set<Node>> getAdjacencyList() {
        return adjacencyList;
    }

    public Graph() {
        this.adjacencyList = new HashMap<>();
        this.callCheck = new HashMap<>();
    }

    public void addVertex(Node n) {
        if (this.adjacencyList.containsKey(n)) {
            throw new IllegalArgumentException("Vertex already exists.");
        }
        this.adjacencyList.put(n, new HashSet<>());
    }

    public void addEdge(Node v, Node u) {
        if (!this.adjacencyList.containsKey(v) || !this.adjacencyList.containsKey(u)) {
            throw new IllegalArgumentException();
        }

        this.adjacencyList.get(v).add(u);
        this.adjacencyList.get(u).add(v);
    }

    public boolean isAdjacent(Node v, Node u) {
        return this.adjacencyList.get(v).contains(u);
    }

    public Iterable<Node> getNeighbors(Node n) {
        return this.adjacencyList.get(n);
    }

    public ArrayList<Node> getRandomNeighbors(Node n) {

        ArrayList<Node> randomNeighbours = new ArrayList<Node>();
        for (Node x : adjacencyList.get(n)) {
            randomNeighbours.add(x);
        }

        return randomNeighbours;
    }

    public void makeTransaction(Node n) {

        Transaction trans = new Transaction();
        String toSign = trans.toString();
        try {
            n.encrypt(n.getPrivateKey(), toSign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("User" + n.getLabel() + " signed transaction " + trans.getId() +
                " using his private key: " + n.getPrivateKey());
        n.transactions.add(trans);
        System.out.println("User" + n.getLabel() + " made a transaction " + trans + " of id " + trans.getId());
        callCheck.put(n, trans);

//        for (Node name: callCheck.keySet()){
//            String key = name.toString();
//            String value = callCheck.get(name).toString();
//            System.out.println(key + " " + value);
//        }

        Random rand = new Random();
        ArrayList<Node> givenList = getRandomNeighbors(n);
        HashSet<Node> nodes = new HashSet<>();
        int numberOfElements = getRandomNumberInRange(1, n.getNumOfInstances());

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(givenList.size());

                nodes.add(givenList.get(randomIndex));
        }

        for (Node node : nodes) {
            if (!(callCheck.get(node)== trans)) {
                propagateTransaction(node, trans);
                callCheck.put(node, trans);
            }
        }

        if (n.transactions.size() > transactionQuota) {
            createBlock(n);
        }

        for (Node temp : givenList) {
            if (temp.transactions.size() > transactionQuota) {
                createBlock(temp);
        }
    }
    }

    public void propagateTransaction(Node n, Transaction t) {

        System.out.println("User" + n.getLabel() + " announced a transaction " + t +
                " that he received of id " + t.getId());
        callCheck.put(n, t);

        Random rand = new Random();
        ArrayList<Node> givenList = getRandomNeighbors(n);
        HashSet<Node> nodes = new HashSet<>();
        int numberOfElements = 2;

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(givenList.size());
            if(!givenList.get(randomIndex).transactions.contains(t)) {
                nodes.add(givenList.get(randomIndex));
                givenList.remove(randomIndex).transactions.add(t);
            }
        }

        for (Node node : nodes) {
            if (!(callCheck.get(node)== t)) {
                propagateTransaction(node, t);
                callCheck.put(node, t);
            }
        }

    }

    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("Max must be greater than min.");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public synchronized void createBlock(Node n) {

        String hash;
        if (n.hasNValidTransactions(transactionQuota)) {
            if (n.blockChain.isEmpty()) {
                hash = "";
            }
            else {
                hash = n.blockChain.latestBlock().hash;
            }
                Block block = new Block(n.getNValidTransactions(transactionQuota), hash);

            for (Transaction transaction : n.getNValidTransactions(transactionQuota)) {
                //System.out.println(transaction);
                transaction.setInaBlock();
                if (!n.blocksCache.contains(transaction)) {
                    n.blocksCache.add(block);
                }
                if (!n.blockChain.contains(block)) {
                    n.blockChain.addBlock(block);
                }
            }
            //System.out.println(n.blocksCache);
            if (n.blockChain.isChainValid()) {
                System.out.println("User" + n.label + " created a block of " + transactionQuota +
                        " transactions" + " and its hash is " + block.hash);
                System.out.println("Number of blocks so far: " + block.getNumOfInstances());
                propagateBlock(n, block);
            }

        }

    }

    public void propagateBlock(Node n, Block b) {

        System.out.println("User" + n.getLabel() + " announced a block " + b);

        Random rand = new Random();
        ArrayList<Node> givenList = getRandomNeighbors(n);
        HashSet<Node> nodes = new HashSet<>();
        int numberOfElements = getRandomNumberInRange(1, n.getNumOfInstances());

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = rand.nextInt(givenList.size());
            if(!givenList.get(randomIndex).blocksCache.contains(b)) {
                nodes.add(givenList.get(randomIndex));
                givenList.get(randomIndex).blocksCache.add(b);
            }
            if (!givenList.get(randomIndex).blockChain.contains(b)) {
                givenList.get(randomIndex).blockChain.addBlock(b);
            }
        }

        for (Node node : nodes) {
            propagateBlock(node, b);
        }
    }

}

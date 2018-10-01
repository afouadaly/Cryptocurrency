import javax.crypto.Cipher;
import java.io.InputStream;
import java.security.*;
import java.util.Base64;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {

    public static void main(String[] args) {

//        System.out.println("Enter the number of nodes: ");
//        Scanner sc = new Scanner(System.in);
//        int nodesCount = sc.nextInt();
//        for (int count = 0; count < nodesCount; count++) {
//
//           G.addVertex(new Node(count));
//        }

        Node user1 = new Node(1);
        Node user2 = new Node(2);
        Node user3 = new Node(3);
        Node user4 = new Node(4);
        Node user5 = new Node(5);
        Node user6 = new Node(6);
        Node user7 = new Node(7);
        Node user8 = new Node(8);
        Node user9 = new Node(9);
        Node user10 = new Node(10);
        Graph G = new Graph();
        G.addVertex(user1);
        G.addVertex(user2);
        G.addVertex(user3);
        G.addVertex(user4);
        G.addVertex(user5);
        G.addVertex(user6);
        G.addVertex(user7);
        G.addVertex(user8);
        G.addVertex(user9);
        G.addVertex(user10);

        G.addEdge(user1, user2);
        G.addEdge(user1, user3);
        G.addEdge(user2, user3);
        G.addEdge(user2, user4);
        G.addEdge(user3, user4);
        G.addEdge(user3, user5);
        G.addEdge(user4, user5);
        G.addEdge(user4, user6);
        G.addEdge(user5, user6);
        G.addEdge(user5, user7);
        G.addEdge(user6, user7);
        G.addEdge(user6, user8);
        G.addEdge(user7, user8);
        G.addEdge(user7, user9);
        G.addEdge(user8, user9);
        G.addEdge(user8, user10);
        G.addEdge(user9, user10);

        G.makeTransaction(user1);
        G.makeTransaction(user2);
        G.makeTransaction(user3);
        G.makeTransaction(user4);
        G.makeTransaction(user2);
        G.makeTransaction(user2);
        G.makeTransaction(user2);
        G.makeTransaction(user2);

        System.out.println("User1's ids of transactions:" + user1.getTransactions());
        System.out.println("User2's ids of transactions:" + user2.getTransactions());
        System.out.println("User3's ids of transactions:" + user3.getTransactions());
        System.out.println("User4's ids of transactions:" + user4.getTransactions());
        System.out.println("User5's ids of transactions:" + user5.getTransactions());
        System.out.println("User6's ids of transactions:" + user6.getTransactions());
        System.out.println("User7's ids of transactions:" + user7.getTransactions());
        System.out.println("User8's ids of transactions:" + user8.getTransactions());
        System.out.println("User9's ids of transactions:" + user9.getTransactions());
        System.out.println("User10's ids of transactions:" + user10.getTransactions());

        System.out.println("User1's blockCache:" + user1.getBlocksCache());
        System.out.println("User2's blockCache:" + user2.getBlocksCache());
        System.out.println("User3's blockCache:" + user3.getBlocksCache());
        System.out.println("User4's blockCache:" + user4.getBlocksCache());
        System.out.println("User5's blockCache:" + user5.getBlocksCache());
        System.out.println("User6's blockCache:" + user6.getBlocksCache());
        System.out.println("User7's blockCache:" + user7.getBlocksCache());
        System.out.println("User8's blockCache:" + user8.getBlocksCache());
        System.out.println("User9's blockCache:" + user9.getBlocksCache());
        System.out.println("User10's blockCache:" + user10.getBlocksCache());


        System.out.print("User1's blockChain:");
        user1.blockChain.printBlocks();
        System.out.print("User2's blockChain:");
        user2.blockChain.printBlocks();
        System.out.print("User3's blockChain:");
        user3.blockChain.printBlocks();
        System.out.print("User4's blockChain:");
        user4.blockChain.printBlocks();
        System.out.print("User5's blockChain:");
        user5.blockChain.printBlocks();
        System.out.print("User6's blockChain:");
        user6.blockChain.printBlocks();
        System.out.print("User7's blockChain:");
        user7.blockChain.printBlocks();
        System.out.print("User8's blockChain:");
        user8.blockChain.printBlocks();
        System.out.print("User9's blockChain:");
        user9.blockChain.printBlocks();
        System.out.print("User10's blockChain:");
        user10.blockChain.printBlocks();


    }
}




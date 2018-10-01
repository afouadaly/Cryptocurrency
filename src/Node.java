import java.util.*;
import javax.crypto.Cipher;
import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.NoSuchAlgorithmException;

public class Node {

    public int label;
    public ArrayList<Transaction> transactions;
    public Set<Block> blocksCache;
    private static int nodeCounter;
    private PrivateKey privateKey;
    private PublicKey pubKey;
    public BlockChain blockChain;

    public Node(int l) {
        this.label = l;
        transactions = new ArrayList<Transaction>();
        blocksCache = new HashSet<Block>();
        nodeCounter++;
        KeyPair keyPair = null;
        try {
            keyPair = buildKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        this.pubKey = keyPair.getPublic();
        this.privateKey = keyPair.getPrivate();
        this.blockChain = new BlockChain();
    }

    public int getLabel() {
        return label;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public ArrayList<String> getTransactions() {

        ArrayList<String> ids = new ArrayList<String>();
        for (Transaction temp : transactions) {
            ids.add((temp.getId() + ""));
            }
        return ids;
    }

    public boolean hasNValidTransactions(int n) {

        //System.out.println(transactions);
        List<Transaction> validTransactions = new ArrayList<Transaction>();
        for (Transaction transaction : transactions) {
            if (transaction.isInaBlock() == false) {
                validTransactions.add(transaction);
                //System.out.println(transaction);
            }
        }
        if (validTransactions.size() >= n) {
            return true;
        }
        return false;
    }

    public List<Transaction> getNValidTransactions(int n) {

        List<Transaction> validTransactions = new ArrayList<Transaction>();
        for (Transaction transaction : transactions) {
            if (transaction.isInaBlock() == false) {
                validTransactions.add(transaction);
            }
        }
        return validTransactions.subList(0, n);
    }

    public static int getNumOfInstances() {
        return nodeCounter;
    }

    public String getTransactionIds() {
        String transactionIds = "";
        for (Transaction transaction : transactions) {
            transactionIds += transaction.getId();
        }
        return transactionIds;
    }

    public Set<Block> getBlocksCache() {
        return blocksCache;
    }

    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);
        return keyPairGenerator.genKeyPair();
    }

    public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(message.getBytes());
    }

    public static byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(encrypted);
    }

    }



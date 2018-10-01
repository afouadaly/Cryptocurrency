import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Block {

    public String hash;
    public String previousHash;
    //private String [] transactions;
    List<Transaction> transactions;
    private int nonce;
    private static int blockCounter;
    public Set<Integer> nonceValues;

    public Block(List<Transaction> transactions, String previousHash){

        this.transactions = transactions;
        this.previousHash = previousHash;
        this.hash = calculateHash();
        this.nonceValues = new HashSet<Integer>();
        blockCounter++;
    }

    public String calculateHash() {
        // calculate hash, then increment nonce until hash starts with 2 0's
        hash = StringUtil.applySha256(previousHash + transactions + Integer.toString(nonce));
        String target = new String(new char[2]).replace('\0', '0');
        while (!hash.substring(0, 2).equals(target)) {
            nonce++;
            hash = StringUtil.applySha256(previousHash + transactions + Integer.toString(nonce));

        }
            return hash;
    }

    public static int getNumOfInstances() {
        return blockCounter;
    }

    public String getHash() {
        return hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }
}

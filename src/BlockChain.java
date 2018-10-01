import java.util.LinkedList;

public class BlockChain {

    public static LinkedList<Block> blocks;

    public BlockChain() {

        blocks = new LinkedList<>();
    }

    public Block latestBlock() {

        return blocks.get(blocks.size() - 1);
    }

    public void addBlock(Block b) {
        if (b != null) {
            blocks.add(b);
        }
    }

    public boolean contains(Block block){

        for (int i = 0; i < blocks.size(); i++){
            if (blocks.get(i) == block)
                return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return  (blocks.size() == 0);
    }

    public void printBlocks() {
        for (Block block : blocks) {
            System.out.println(block + "");
            }
    }

    public static Boolean isChainValid() {

        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[2]).replace('\0', '0');

        // loop through block chain to check hashes
        for(int i = 1; i < blocks.size(); i++) {
            currentBlock = blocks.get(i);
            previousBlock = blocks.get(i - 1);
            //compare actual hash and calculated hash
            if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
                System.out.println("Current hashes are not equal");
                return false;
            }
            // compare previous hash and registered previous hash
            if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
                System.out.println("Previous hashes are not equal");
                return false;
            }
            // check if hash starts with 00
            if(!currentBlock.hash.substring(0, 2).equals(hashTarget)) {
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }

}

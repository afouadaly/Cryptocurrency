public class Transaction {

    private static int nextId = 0;
    int id;
    private boolean inaBlock;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transaction() {

        this.id = Transaction.nextId;
        Transaction.nextId++;
    }

    public boolean isInaBlock() {
        return inaBlock;
    }

    public void setInaBlock() {
        this.inaBlock = true;
    }
}

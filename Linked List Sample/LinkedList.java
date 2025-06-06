public class LinkedList {

    private int data;
    private LinkedList next;
    public LinkedList (int data) {
        this.data = data;
    }

    public void setNext (LinkedList new_node) {
        next = new_node;
    }

    public int getData () {
        return data;
    }

    public LinkedList getNext () {
        return next;
    }
}

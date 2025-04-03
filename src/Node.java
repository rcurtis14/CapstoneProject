public class Node {

    public String data;
    public Node next;
    public Node prev;

    public Node() {
        this.data = "";
        next = null;
        prev = null;
    }

    public Node(String data) {
        this.data = data;
        next = null;
        prev = null;
    }
}
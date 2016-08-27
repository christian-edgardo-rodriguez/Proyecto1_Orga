package main;

/**
 *
 * @author vinicio
 */
public class Node {

    private Object Data = null;
    private Node Next = null;

    public Node(Object Data) {
        this.Data = Data;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object Data) {
        this.Data = Data;
    }

    public Node getNext() {
        return Next;
    }

    public void setNext(Node Next) {
        this.Next = Next;
    }
}

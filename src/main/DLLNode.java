package main;

/**
 *
 * @author vinicio
 */
public class DLLNode {

    private Object Data = null;
    private DLLNode prev = null;
    private DLLNode next = null;

    public DLLNode(Object Data) {
        this.Data = Data;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object Data) {
        this.Data = Data;
    }

    public DLLNode getPrev() {
        return prev;
    }

    public void setPrev(DLLNode prev) {
        this.prev = prev;
    }

    public DLLNode getNext() {
        return next;
    }

    public void setNext(DLLNode next) {
        this.next = next;
    }
}

package main;

import java.io.Serializable;

/**
 *
 * @author vinicio
 */
public class Node1 implements Serializable {

    private Object Data = null;
    private Node1 Next = null;

    public Node1(Object Data) {
        this.Data = Data;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object Data) {
        this.Data = Data;
    }

    public Node1 getNext() {
        return Next;
    }

    public void setNext(Node1 Next) {
        this.Next = Next;
    }
}

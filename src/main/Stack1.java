package main;

import java.io.Serializable;

/**
 *
 * @author vinicio
 */
public class Stack1 implements Serializable {

    private Node1 top = null;
    private int size = 0;

    public Stack1() {
        top = null;
        size = 0;
    }

    public void push(Object data) {
        if (empty()) {
            top = new Node1(data);
        } else {
            Node1 tmp = new Node1(data);
            tmp.setNext(top);
            top = tmp;
        }
        size++;
    }

    public Object pop() {
        Object tmp = top.getData();
        top = top.getNext();
        size--;
        return tmp;
    }

    public Object peek() {
        return top.getData();
    }

    public boolean empty() {
        return size == 0;
    }
}

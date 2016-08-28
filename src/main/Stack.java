package main;

/**
 *
 * @author vinicio
 */
public class Stack {

    private Node top = null;
    private int size = 0;

    public Stack() {
        top = null;
        size = 0;
    }

    public void push(Object data) {
        if (isEmpty()) {
            top = new Node(data);
        } else {
            Node tmp = new Node(data);
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

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }else 
            return false;
    }
}
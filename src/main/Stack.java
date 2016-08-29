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

    public Node pop() {
        if (top == null) {
            return new Node();
        }
        Node tmp = top;
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
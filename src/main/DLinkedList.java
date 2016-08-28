package main;

import java.io.Serializable;

/**
 *
 * @author vinicio
 */
public class DLinkedList implements Serializable {

    private DLLNode head = null;
    private DLLNode last = null;
    private int size = 0;

    public DLinkedList() {
        head = last = null;
    }

    public void add(Object Data) {
        if (isEmpty()) {
            head = new DLLNode(Data);
            last = head;
        } else {
            DLLNode tmp = new DLLNode(Data);
            tmp.setPrev(last);
            last.setNext(tmp);
            last = tmp;
        }
        size++;
    }

    public void delete(int index) {
        DLLNode tmp = null;
        if (index == 0) {
            tmp = head.getNext();
            tmp.setPrev(null);
            head = tmp;
        } else if (index == size - 1) {
            tmp = last.getPrev();
            tmp.setNext(null);
            last = tmp;
        } else {
            tmp = head;
            for (int i = 0; i <= index; i++) {
                tmp = tmp.getNext();
            }
            tmp.getPrev().setNext(tmp.getNext());
            tmp.getNext().setPrev(tmp.getPrev());
        }
        size--;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

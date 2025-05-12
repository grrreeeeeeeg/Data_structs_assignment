

import java.io.PrintStream;
import java.util.NoSuchElementException;

public class DoubleQueueImpl<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private class Node<E> {
        E data;
        Node<E> next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    public DoubleQueueImpl() {
        head = null;
        tail = null;
        size = 0;
    }

    public void put(T item) {
        Node<T> newNode = new Node<>(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public T get() {
        if (head == null) {
            throw new NoSuchElementException("Queue is empty");
        }
        T value = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return value;
    }

    public T peek() {
        if (head == null) {
            throw new NoSuchElementException("Queue is empty");
        }
        return head.data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printQueue(PrintStream stream) {
        Node<T> current = head;
        stream.print("Queue: [");
        while (current != null) {
            stream.print(current.data);
            if (current.next != null) {
                stream.print(", ");
            }
            current = current.next;
        }
        stream.println("]");
    }

    public int size() {
        return size;
    }
}


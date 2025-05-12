import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl<T> implements StringStack<T> {

    private Node<T> top;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public StringStackImpl() {
        top = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void push(T item) {
        Node<T> newNode = new Node<>(item); // Δημιουργία νέου κόμβου
        newNode.next = top;
        top = newNode;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        T poppedItem = top.data;
        top = top.next;
        size--;
        return poppedItem;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return top.data;
    }

    @Override
    public void printStack(PrintStream stream) {
        Node<T> current = top;
        stream.println("Stack contents:");
        while (current != null) {
            stream.println(current.data);
            current = current.next;
        }
    }

    @Override
    public int size() {
        return size;
    }
}

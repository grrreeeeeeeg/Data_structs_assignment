

import java.util.NoSuchElementException; 

public class DoubleQueueImpl implements DoubleQueue {

    private double[] queueContents; // the array to hold the queue data

    private int size; // number of enqueued items

    private int front; // array position of the front of the queue
    private int back; // array position of the back of the queue

    private static final int DEFAULT_CAPACITY = 2; // the default queue capacity
    private static final int AUTOGROW_SIZE = 2; // the autogrow size after reaching full capacity

    public DoubleQueueImpl() {
        queueContents = new double[DEFAULT_CAPACITY];
        front = 0;
        back = -1;
        size = 0;
    }

    public void put(double item) {
        if (size == queueContents.length)
            growQueue();

        back = positionAfter(back);
        queueContents[back] = item;

        size += 1;
    }

    public double get() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();

        double element = queueContents[front];
        front = positionAfter(front);
        size -= 1;

        return element;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int positionAfter(int current) {
        return (current + 1) % queueContents.length;

        // Alternative
        // if(current + 1 == queueContents.length)
        //     return 0;
        // return current + 1;
    }

    private void growQueue() {
        double[] newContents = new double[queueContents.length + AUTOGROW_SIZE];

        int current = front;

        for (int i = 0; i < size; ++i) {
            newContents[i] = queueContents[current];
            current = positionAfter(current);
        }
        queueContents = newContents;
        front = 0;
        back = size - 1;
    }

    public double peek() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        return queueContents[front];
    }

    public void printQueue(PrintStream stream) {
       stream.print("Queue: [");
       if (!isEmpty()){
        int i = front;
        while (i != positionAfter(back)) {
            stream.print(queueContents[i]+", ");
            i = positionAfter(i);
        }
       }
       stream.print("]");

         
    }
    public int size() {
        if (isEmpty()) {
            return 0;
        }
        return size;
    }
}

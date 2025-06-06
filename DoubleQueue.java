import java.io.PrintStream;
import java.util.NoSuchElementException;

/**
 * Defines the methods for a FIFO queue that handles generic items
 */
public interface DoubleQueue<T> {

	/**
	 * @return true if the queue is empty
	 */
	public boolean isEmpty();

	/**
	 * Insert an item to the queue
	 */
	public void put(T item);

	/**
	 * Remove and return the oldest item of the queue
	 * @return oldest item of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public T get() throws NoSuchElementException;

	/**
	 * Return without removing the oldest item of the queue
	 * @return oldest item of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	public T peek() throws NoSuchElementException;

	/**
	 * Print the elements of the queue, starting from the oldest
	 * item, to the print stream given as argument. For example, to
	 * print the elements to the
	 * standard output, pass System.out as parameter. E.g.,
	 * printQueue(System.out);
	 */
	public void printQueue(PrintStream stream);

	/**
	 * Return the size of the queue, 0 if it is empty
	 * @return number of elements in the queue
	 */
	public int size();
}

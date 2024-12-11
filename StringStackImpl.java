import java.io.PrintStream;
import java.util.NoSuchElementException;

public class StringStackImpl implements StringStack {
    private String[] StackContents;
    private int size;
    private int top;

    private static final int DEFAULT_CAPACITY = 2; 
    private static final int AUTOGROW_SIZE = 2;
    
    public StringStackImpl() {
        StackContents = new String[DEFAULT_CAPACITY];
        size = 0;
        top = -1;
        
    }

    /**
	 * @return true if the stack is empty
	 */
	public boolean isEmpty() {
        return size == 0;
    }

    public void GrowStack() {
        String[] newContents = new String[StackContents.length + AUTOGROW_SIZE];

        for (int i = 0; i < size; ++i) {
            newContents[i] = StackContents[i];
        }
        StackContents = newContents;
    }

    public void push(String item) {
        if (size = StackContents.length)
            GrowStack();
        top++;
        StackContents[top] = item;
        size++;

    }
	/**
	 * remove and return the item on the top of the stack
	 * @return the item on the top of the stack
	 * @throws a NoSuchElementException if the stack is empty
	 */
	public String pop() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        String element = StackContents[top];
        top--;
        return element;
    }

    /**
	 * return without removing the item on the top of the stack
	 * @return the item on the top of the stack
	 * @throws a NoSuchElementException if the stack is empty
	 */
	public String peek() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        return StackContents[top];
    }

	public void printStack(PrintStream stream) {
        stream.print("STACK\n");
        for (int i = top; i > 0; --i) {
            stream.print(StackContents[i]);
            stream.print("\n");
        }
    }

 	/**
     * return the size of the stack, 0 if it is empty
	 * @return the number of items currently in the stack
	 */
	public int size() {
        return size;
    }

}
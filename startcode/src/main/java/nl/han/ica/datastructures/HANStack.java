package nl.han.ica.datastructures;

import java.util.LinkedList;

public class HANStack<T> implements IHANStack<T> {

    private LinkedList<T> stack = new LinkedList<T>();

    @Override
    public void push(T value) {
        stack.addFirst(value);
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.removeFirst();
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.getFirst();
    }

    // Om te checken dat de stack leeg is
    private boolean isEmpty() {
        return stack.isEmpty();
    }
}

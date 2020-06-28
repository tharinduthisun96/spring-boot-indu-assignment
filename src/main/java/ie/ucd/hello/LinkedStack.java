package ie.ucd.hello;

public class LinkedStack<T> {
    private class Node {
        T element;
        Node next;

        public Node(T element) {
            this.element = element;
        }
    }

    Node front = null;
    Node rear = null;
    int size = 0;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T element) {
        Node node = new Node(element);
        if (front == null) {
            front = rear = node;
        } else {
            rear.next = node;
        }
        size++;
    }

    public T head() {
        if (isEmpty()) throw new StackEmptyException();
        return front.element;
    }
    
    public T pop() {
        if (isEmpty()) throw new StackEmptyException();
        T element = front.element;
        front = front.next;
        size--;
        return element;
    }
    
}
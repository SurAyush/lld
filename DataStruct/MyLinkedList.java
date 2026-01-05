package DataStruct;

public class MyLinkedList<T> implements MyList<T> {

    private class Node {
        T data;
        Node next;
        
        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    private Node head;
    private Node tail;
    private int list_size;

    public MyLinkedList() {
        this.head = null;
        this.tail = null;
        this.list_size = 0;
    }

    public MyLinkedList(T[] array) {
        this();
        for (T element : array) {
            add(element);
        }
    }
	
	@Override
	public void add(T element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        this.list_size++;
	}
	
	@Override
	public T get(int index) {
        if (index < 0 || index >= list_size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + list_size);
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
	}
	
	@Override
	public void put(int index, T element) {
        if (index < 0 || index >= list_size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + list_size);
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = element;
	}
	
	@Override
	public T remove(int index) {
        if (index < 0 || index >= list_size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + list_size);
        }
        T removedData;
        if (index == 0) {
            removedData = head.data;
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removedData = current.next.data;
            current.next = current.next.next;
            if (current.next == null) {
                tail = current;
            }
        }
        this.list_size--;
        return removedData;
	}
	
	@Override
	public int size() {
        return this.list_size;
	}
	
	@Override
	public void clear() {
        head = null;
        tail = null;
        list_size = 0;
	}
}

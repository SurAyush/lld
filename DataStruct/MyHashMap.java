package DataStruct;
// Linked List based HashMap implementation
public class MyHashMap<K,V> implements Map<K,V> {
    private final int CAPACITY = 32;
    private int size = 0;
    private Node<K,V>[] table;

    static class Node<K,V> {
        K key;
        V value;
        Node<K,V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public MyHashMap() {
        table = new Node[CAPACITY];
    }

    @Override
    public void put(K key, V value){
        int index = Math.abs(key.hashCode()) % CAPACITY;
        Node<K,V> newNode = new Node<>(key, value);

        if (table[index] == null) {
            table[index] = newNode;
        } 
        else {
            Node<K,V> current = table[index];
            Node<K,V> prev = null;

            while (current != null) {
                // update value if key already exists
                if (current.key.equals(key)) {
                    current.value = value; 
                    return;
                }
                prev = current;
                current = current.next;
            }
            prev.next = newNode; // Add new node at the end
        }
        size++;
    }

    @Override
    public V get(K key){ 
        int index = Math.abs(key.hashCode()) % CAPACITY;
        if(table[index] == null){ 
            return null;
        }
        else{
            Node<K,V> current = table[index];
            while(current != null){
                if(current.key.equals(key)){
                    return current.value;
                }
                current = current.next;
            }
            return null; 
        }
    }

    @Override
    public void remove(K key){
        int index = Math.abs(key.hashCode()) % CAPACITY;
        if(table[index] == null){ 
            return;
        }
        else{
            Node<K,V> current = table[index];
            Node<K,V> prev = null;

            while(current != null){
                if(current.key.equals(key)){
                    if(prev == null){
                        table[index] = current.next; // Remove head
                    } else {
                        prev.next = current.next; // Bypass the current node
                    }
                    size--;
                    return;
                }
                prev = current;
                current = current.next;
            }
        }
    }

    @Override
    public boolean containsKey(K key){ 
        int index = Math.abs(key.hashCode()) % CAPACITY;
        if(table[index] == null){ 
            return false;
        }
        else{
            Node<K,V> current = table[index];
            while(current != null){
                if(current.key.equals(key)){
                    return true;
                }
                current = current.next;
            }
            return false; 
        }
    }

    @Override
    public int size(){ 
        return size; 
    }
    
}

package DataStruct;

public interface MyList<T> {
    void add(T element);
    T get(int index);
    int size();
    void clear();
    void put(int index, T element);
    T remove(int index);
}
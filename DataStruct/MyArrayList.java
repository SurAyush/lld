package DataStruct;

import java.util.Arrays;

public final class MyArrayList<T> implements MyList<T> {
    private T arr[];
    private static final int DEFAULT_SIZE=10;
    private int list_size;
    private int current_index=0;
    
    public MyArrayList(){
        arr = (T[])(new Object[DEFAULT_SIZE]);
        list_size = DEFAULT_SIZE;
    }

    public MyArrayList(int size){
        list_size = size;
        arr = (T[])(new Object[list_size]);
    }

    public MyArrayList(T[] array){
        int sz = array.length;
        arr = (T[])(new Object[sz]);
        list_size = sz;
        for(int i=0;i<sz;i++){
            arr[i] = array[i];
            current_index++;
        }
    }

    private void resize(){
        int new_size = list_size * 2;
        T new_arr[] = (T[])(new Object[new_size]);
        for(int i=0;i<list_size;i++){
            new_arr[i] = arr[i];
        }
        arr = new_arr;
        list_size = new_size;
    }

    public void add(T element){
        // resize if needed
        if(current_index == list_size){
            resize();
        }
        arr[current_index++] = element;
    }

    public T get(int index){
        if(index<0 || index>=current_index){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+current_index);
        }
        return arr[index];
    }

    public int size(){
        return current_index;
    }

    public void clear(){
        arr = (T[])(new Object[DEFAULT_SIZE]);
        list_size = DEFAULT_SIZE;
        current_index = 0;
    }

    public void put(int index, T element){
        if(index<0 || index>=current_index){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+current_index);
        }
        arr[index] = element;
    }

    public boolean remove(int index){
        if(index<0 || index>=current_index){
            throw new IndexOutOfBoundsException("Index: "+index+", Size: "+current_index);
        }
        for(int i=index;i<current_index-1;i++){
            arr[i] = arr[i+1];
        }
        current_index--;
        return true;
    }

    public String toString(){
        return Arrays.toString(arr);
    }
}
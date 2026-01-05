package DataStruct;

class Main {
    public static void main(String args[]){
        MyList<Integer> list = new MyArrayList<>();
        for(int i=0;i<5;i++){
            list.add(i*10);
        }
        System.out.println("List Size: "+list.size());
        list.put(2, 99);
        list.remove(3);
        System.out.println("List: "+list);

        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        System.out.println("Value for 'Two': "+map.get("Two"));
        System.out.println("Map Size: "+map.size());
        map.put("Two", 22);
        System.out.println("Updated Value for 'Two': "+map.get("Two"));
        map.remove("One");
        System.out.println("Map Size after removal: "+map.size());
    }
}
package org.example.list;

public class Main {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
//        list.add(5);
//        list.add(7);
//        list.add(9);
//        list.add(0);
//        list.add(1);
//        list.add(3,3);
        System.out.println(list.remove(0));
        System.out.println(list.toString());

        System.out.println(list.contains(3));
        System.out.println(list.size());
        System.out.println(list.isEmpty());
//        ArrayList <Integer> list2= new ArrayList<Integer>();
//        System.out.println(list2.toString());
    }
}

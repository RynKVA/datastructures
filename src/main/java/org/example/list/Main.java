package org.example.list;

import org.example.list.exceptions.IndexOutOfListExceptin;
import org.example.list.exceptions.ListIsEmptyException;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IndexOutOfListExceptin, ListIsEmptyException {
        ArrayList list = new ArrayList();
        list.add(5);
        list.add(7);
        list.add(9);
        list.add(4);
        list.add(1);
        list.add(5);
        list.add(7);
        list.add(9);
        list.add(4);
        list.add(1);
        list.add(5);
        list.add(7);
        list.add(9);
        list.add(4);
        list.add(1);
        list.add(5);
        list.add(7);
        list.add(9);
        list.add(4);
        list.add(1);
        list.add(3,0);
//        System.out.println(list.remove(5));
        System.out.println(Arrays.toString(list.getArray()));

//        System.out.println(list.contains(3));
//        System.out.println(list.size());
//        System.out.println(list.isEmpty());
//        ArrayList <Integer> list2= new ArrayList<Integer>();
//        System.out.println(list2.toString());

    }
}

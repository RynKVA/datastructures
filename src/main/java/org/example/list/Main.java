package org.example.list;


import org.example.search.QuickSearch;

public class Main {
    public static void main(String[] args) {

        // read about lombok ++
        // read about <scope>provided</scope>, <scope>test</scope>
        // <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        // https://leetcode.com/problems/valid-parentheses/description/    +
        // create Stack implementation +
        // create Queue implementation +
        // create Iterator in ArrayList +



/*        LinkedList list = new LinkedList();
        list.add(5);
        list.add(3);
        list.add(9);
        list.add(8, 2);
        list.add(155);
        list.add(177, 2);
        list.add(45, 2);
        System.out.println(list.remove(5));
        System.out.println(list.remove(8));
        System.out.println(list.remove(33));
        System.out.println(list.remove(177));
        System.out.println(list.get(0));
        System.out.println(list.toString());
        System.out.println(list.size());
        System.out.println(list.contains(0));
        System.out.println(list.indexOf(177));*/
//        System.out.println(list.get());
       /* ArrayList<Integer> list= new ArrayList<>();
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        Iterator<Integer>iterator= list.iterator();
        System.out.println(iterator.next());
        System.out.println();
        iterator.remove();
        iterator.next();
        iterator.remove();

        System.out.println(list);*/
int [] array= new int[]{0,3,7,45,99,151,198,229};
        System.out.println(QuickSearch.quickSearch(array, 151));
        System.out.println(QuickSearch.quickSearch(array, 229));
        System.out.println(QuickSearch.quickSearch(array, 152));
    String s="ssss";
        System.out.println(s.isBlank());
    }
}



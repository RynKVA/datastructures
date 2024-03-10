package org.example.list;

public class Main {
    public static void main(String[] args) {

        // Generics -> List<String> or List<Integer> or List<CustomObject> +
        // <? extends AnyObject>, <? super AnyObject> (AnyObject from hierarchy) +
        // https://www.youtube.com/watch?v=MniNZsyjH9E&list=PL6jg6AGdCNaX1yIJpX4sgALBTmTVc_uOJ +
        // move to AbstractList common part (indexOf() shift on this class)
        // suppressed exception - READ
        // create new implementation stack
        // read about lombok
        // read about <scope>provided</scope>, <scope>test</scope>
        // <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
        // https://leetcode.com/problems/valid-parentheses/description/
        // ()[{}] - true
        // ([}) - false


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
        ArrayList<Integer> list= new ArrayList<>();
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        System.out.println(list);
        System.out.println(list.indexOf(3));
    }
}

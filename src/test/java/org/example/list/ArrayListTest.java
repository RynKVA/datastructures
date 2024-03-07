package org.example.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest  extends ListTest {

    // Rework tests +
    // Array list - toString +
    // move to AbstractList common part +
    // clean code
    // Generics -> List<String> or List<Integer> or List<CustomObject>
    // <? extends AnyObject>, <? super AnyObject> (AnyObject from hierarchy)
    // https://www.youtube.com/watch?v=MniNZsyjH9E&list=PL6jg6AGdCNaX1yIJpX4sgALBTmTVc_uOJ
//    suppressed exception - READ
//    try {} catch
//    try with resources
    private final List <Integer> list=getList();
    @Override
    List<Integer> getList() {
        return new ArrayList<>();
    }
}
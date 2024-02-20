package org.example.list;

import org.example.list.exceptions.IndexOutOfListExceptin;
import org.example.list.exceptions.ListIsEmptyException;

public interface List {

    int get(int index) throws IndexOutOfListExceptin;

    int indexOf(int value);

    void add(int value) throws IndexOutOfListExceptin;

    void add(int value, int index) throws IndexOutOfListExceptin;

    boolean contains(int value);

    int remove(int value) throws ListIsEmptyException;

    int size();

    boolean isEmpty();

    // git init
    // git add
    // git commit
    // git push
    // git pull
    // git status
    // git checkout
    // git branch

    // git log


    // link your github acc with idea

}

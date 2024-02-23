package org.example.list;

import org.example.list.exceptions.IndexOutOfListExceptin;
import org.example.list.exceptions.ListIsEmptyException;

public interface List {

    int get(int index) throws IndexOutOfListExceptin, ListIsEmptyException;

    int indexOf(int value) throws IndexOutOfListExceptin, ListIsEmptyException;

    void add(int value) throws IndexOutOfListExceptin;

    void add(int value, int index) throws IndexOutOfListExceptin;

    boolean contains(int value) throws IndexOutOfListExceptin, ListIsEmptyException;

    int remove(int value) throws ListIsEmptyException, IndexOutOfListExceptin;

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

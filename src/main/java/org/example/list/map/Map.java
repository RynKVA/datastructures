package org.example.list.map;

import java.util.Iterator;

public interface Map<K, V> extends Iterable<Map.Entry<K, V>> {
    V put(K key, V value);

    V get(K key);

    V remove(K key);

    int size();

    boolean containsKey(K key);

    boolean isEmpty();

    interface Entry<K, V> {
        K getKey();

        V getValue();

        void setValue(V value);
    }

}

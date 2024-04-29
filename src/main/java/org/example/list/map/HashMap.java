package org.example.list.map;

import org.example.list.ArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class HashMap<K, V> implements Map<K, V> {
    private final static int DEFAULT_CAPACITY = 16;
    private final static double LOAD_FACTOR = 0.75;
    private final static int GROW_fACTOR = 2;
    private ArrayList<Entry<K, V>>[] buckets;
    private int size;

@SuppressWarnings("unchecked")
    public HashMap() {
        buckets = new ArrayList[DEFAULT_CAPACITY];
    }

    @Override
    public V put(K key, V value) {
        growBuckets();
        int bucketIndex = getIndex(key);
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new ArrayList<>(1);
            buckets[bucketIndex].add(new Entry<>(key, value));
            size++;
            return null;
        }
        Entry<K, V> entry = getEntry(key);
        if (entry != null) {
            V oldValue = entry.value;
            entry.value = value;
            return oldValue;
        }
        buckets[bucketIndex].add(new Entry<>(key, value));
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        return getEntry(key) == null ? null : Objects.requireNonNull(getEntry(key)).value;
    }

    @Override
    public V remove(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) {
            return null;
        }
        V removedValue = entry.value;
        int bucketIndex = getIndex(key);
        ArrayList<Entry<K, V>> bucket = buckets[bucketIndex];
        bucket.remove(entry);
        size--;
        return removedValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "{", "}");
        for (Map.Entry<K, V> entry : this){
                StringBuilder builder = new StringBuilder();
                builder.append(entry.getKey()).append("=").append(entry.getValue());
                joiner.add(builder);
            }
        return joiner.toString();
    }
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator<>();
    }

    private Entry<K, V> getEntry(K key) {
        for (Map.Entry<K, V> entry : this) {
            if (Objects.equals(entry.getKey(), key)) {
                return (Entry<K, V>) entry;
            }
        }
        return null;
    }

    private int getIndex(K key) {
        if (key != null) {
            int hash = Math.abs(key.hashCode());
            int positiveHash = 0;
            if (hash < 0){
                positiveHash = (hash + 1) * -1;
            }
            return positiveHash % buckets.length;
        }
        return 0;
    }

//    private ArrayList<Entry<K, V>> getBucket(K key) {
//        int bucketIndex = getIndex(key);
//        return buckets[bucketIndex];
//    }

    @SuppressWarnings("unchecked")
    private void growBuckets() {
        if (size > buckets.length * LOAD_FACTOR) {
            ArrayList<Entry<K, V>>[] newBuckets = new ArrayList[buckets.length * GROW_fACTOR];
            ArrayList<Entry<K, V>> putEntries = new ArrayList<>();
            for (Map.Entry<K, V> entry : this) {
                putEntries.add((Entry<K, V>) entry);
            }
            buckets = newBuckets;
            size = 0;
            for (Entry<K, V> entry : putEntries) {
                put(entry.key, entry.value);
            }
        }
    }

    static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;

        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    @SuppressWarnings("unchecked")
    private class HashMapIterator<Entry> implements Iterator<Map.Entry<K, V>> {
        private int countEntries;
        private int countBuckets;
        private Iterator<Entry> bucketIterator;
        private boolean isNextUsed;

        @Override
        public boolean hasNext() {
            return countEntries != size;
        }

        @Override
        public Map.Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next element.");
            }
            while (true) {
                if (buckets[countBuckets] == null){
                    bucketIterator = null;
                    countBuckets++;
                    continue;
                } else if (bucketIterator == null) {
                  bucketIterator = (Iterator<Entry>) buckets[countBuckets].iterator();
                }
                if (bucketIterator.hasNext()) {
                    Map.Entry<K, V> targetEntry = (Map.Entry<K, V>) bucketIterator.next();
                    countEntries++;
                    isNextUsed = true;
                    return (Map.Entry<K, V>) targetEntry;
                }
                countBuckets++;
                bucketIterator = (Iterator<Entry>) buckets[countBuckets].iterator();
            }
        }


        @Override
        public void remove() {
            if (isNextUsed) {
                bucketIterator.remove();
                size--;
                countEntries--;
                isNextUsed = false;
            } else {
                throw new IllegalStateException("Method next() not used.");
            }
        }
    }
}

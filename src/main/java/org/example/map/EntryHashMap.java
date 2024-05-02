package org.example.map;

import org.example.list.ArrayList;
import org.example.list.List;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class EntryHashMap<K, V> implements Map<K, V> {
    private final static int DEFAULT_CAPACITY = 16;
    private final static double LOAD_FACTOR = 0.75;
    private final static int GROW_fACTOR = 2;
    private Entry<K, V>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public EntryHashMap() {
        buckets = new Entry[DEFAULT_CAPACITY];
    }

    @Override
    public V put(K key, V value) {
        growBuckets();
        int bucketIndex = getIndex(key, buckets.length);
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new Entry<>(key, value);
            size++;
            return null;
        }
        V oldValue = buckets[bucketIndex].value;
        buckets[bucketIndex].value = value;
        return oldValue;
    }

    @Override
    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        return entry == null ? null : Objects.requireNonNull(entry).value;
    }

    @Override
    public V remove(K key) {
        Entry<K, V> entry = getEntry(key);
        if (entry == null) {
            return null;
        }
        V removedValue = entry.value;
        int bucketIndex = getIndex(key, buckets.length);
        buckets[bucketIndex] = null;
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
        for (Map.Entry<K, V> entry : this) {
            StringBuilder builder = new StringBuilder();
            builder.append(entry.getKey()).append("=").append(entry.getValue());
            joiner.add(builder);
        }
        return joiner.toString();
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    private Entry<K, V> getEntry(K key) {
        int indexBuket = getIndex(key, buckets.length);
        Entry<K, V> entry = buckets[indexBuket];
        if (entry == null) {
            return null;
        }else if (Objects.equals(key, entry.key)) {
            return entry;
        }
        return null;
    }

    int getIndex(K key, int bucketLength) {
        if (key != null) {
            int hash = key.hashCode();
            int positiveHash = Math.abs(hash);
            if (hash == Integer.MIN_VALUE) {
                positiveHash = Integer.MAX_VALUE;
            }
            return positiveHash % bucketLength;
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    void growBuckets() {
        if (size > buckets.length * LOAD_FACTOR) {
            Entry<K, V>[] newBuckets = new Entry[buckets.length * GROW_fACTOR];
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
            StringJoiner joiner = new StringJoiner("=", "{", "}");
            joiner.add(key.toString()).add(value.toString());
            return joiner.toString();
        }
    }

    private class HashMapIterator implements Iterator<Map.Entry<K, V>> {
        private int countEntries;
        private int countBuckets;
        private boolean isNextUsed;
        private Entry<K, V> targetEntry;

        @Override
        public boolean hasNext() {
            return countEntries != size;
        }

        @Override
        public Map.Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next element.");
            }
            while (hasNext()) {
                if (buckets[countBuckets] == null) {
                    countBuckets++;
                } else {
                    targetEntry = buckets[countBuckets];
                    countEntries++;
                    countBuckets++;
                    isNextUsed = true;
                    return targetEntry;
                }
            }

            return null;
        }


        @Override
        public void remove() {
            if (isNextUsed) {
                EntryHashMap.this.remove(targetEntry.key);
                countEntries--;
                isNextUsed = false;
            } else {
                throw new IllegalStateException("Method next() not used.");
            }
        }
    }

}

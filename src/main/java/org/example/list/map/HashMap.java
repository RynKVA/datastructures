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


    public HashMap() {
        buckets = new ArrayList[DEFAULT_CAPACITY];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    @Override
    public V put(K key, V value) {
        growBuckets();
        ArrayList<Entry<K, V>> bucket = getBucket(key);
        Entry<K, V> entry = getEntry(key);
        if (entry != null) {
            V oldValue = entry.value;
            entry.value = value;
            return oldValue;
        }
        bucket.add(new Entry<>(key, value));
        size++;
        return null;
    }

    @Override
    public V get(K key) {
        return getEntry(key) == null ? null : getEntry(key).value;
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
        for (ArrayList<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                StringBuilder builder = new StringBuilder();
                builder.append(entry.key).append("=").append(entry.value);
                joiner.add(builder);
            }
        }
        return joiner.toString();
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
            return hash % buckets.length;
        }
        return 0;
    }

    private ArrayList<Entry<K, V>> getBucket(K key) {
        int bucketIndex = getIndex(key);
        return buckets[bucketIndex];
    }

    @SuppressWarnings("unchecked")
    private void growBuckets() {
        if (size > buckets.length * LOAD_FACTOR) {
            ArrayList<Entry<K, V>>[] newBuckets = new ArrayList[buckets.length * GROW_fACTOR];
            for (int i = 0; i < newBuckets.length; i++) {
                newBuckets[i] = new ArrayList<>();
            }
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
        K key;
        V value;

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

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }

        public void setValue(V value) {
            this.value = value;

        }
    }
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator<>();
    }

    @SuppressWarnings("unchecked")
    private class HashMapIterator<Entry> implements Iterator<Entry> {
        private int countEntries;
        private int countBuckets;
        private Map.Entry targetEntry;
        private Iterator<Entry> bucketIterator = (Iterator<Entry>) buckets[countBuckets].iterator();
        private boolean isNextUsed;
        private int removeUsed;

        @Override
        public boolean hasNext() {
            return countEntries != size;
        }

        @Override
        public Entry next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next element.");
            }
            do {
                if (!buckets[countBuckets].isEmpty() && bucketIterator.hasNext()) {
                    countEntries++;
                    targetEntry = (Map.Entry) bucketIterator.next();
                    isNextUsed = true;
                    return (Entry) targetEntry;
                }
                countBuckets++;
                bucketIterator = (Iterator<Entry>) buckets[countBuckets].iterator();
            } while (true);
        }


        @Override
        public void remove() {
            if (isNextUsed) {
                HashMap.this.remove((K) targetEntry.getKey());
                removeUsed++;
                countEntries--;
                isNextUsed = false;
            } else {
                throw new IllegalStateException("Method next() not used.");
            }
        }

    }
}

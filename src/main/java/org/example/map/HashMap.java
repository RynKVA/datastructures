package org.example.map;

import org.example.list.ArrayList;
import org.example.list.List;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class HashMap<K, V> implements Map<K, V> {
    private final static int DEFAULT_CAPACITY = 16;
    private final static double LOAD_FACTOR = 0.75;
    private final static int GROW_fACTOR = 2;
    private List<Entry<K, V>>[] buckets;
    private int size;

    @SuppressWarnings("unchecked")
    public HashMap() {
        buckets = new ArrayList[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public HashMap(int bucketsCapacity) {
        buckets = new ArrayList[bucketsCapacity];
    }

    @Override
    public V put(K key, V value) {
        growBuckets();
        int bucketIndex = getBucketIndex(key, buckets.length);
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
        int bucketIndex = getBucketIndex(key, buckets.length);
        List<Entry<K, V>> bucket = buckets[bucketIndex];
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
        for (Map.Entry<K, V> entry : this) {
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
        int indexBuket = getBucketIndex(key, buckets.length);
        List<Entry<K, V>> bucket = buckets[indexBuket];
        if (bucket == null) {
            return null;
        }
        for (Entry<K, V> targetEntry : bucket) {
            if (Objects.equals(key, targetEntry.key)) {
                return targetEntry;
            }
        }
        return null;
    }

    int getBucketIndex(K key, int bucketLength) {
        if (key == null || bucketLength == 0) {
            return 0;
        }
        int hash = Math.abs(key.hashCode());
        if (hash == Integer.MIN_VALUE) {
            hash = Integer.MAX_VALUE;
        }
        return hash % bucketLength;
    }

    @SuppressWarnings("unchecked")
    void growBuckets() {
        if (size >= buckets.length * LOAD_FACTOR) {
            List<Entry<K, V>>[] newBuckets = new ArrayList[buckets.length * GROW_fACTOR + 1];
            for (Map.Entry<K, V> entry : this) {
                refillBuckets((Entry<K, V>) entry, newBuckets);
            }
            buckets = newBuckets;
        }
    }

    private void refillBuckets(Entry<K, V> entry, List<Entry<K, V>>[] buckets) {
        int bucketIndex = getBucketIndex(entry.key, buckets.length);
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new ArrayList<>(1);
            buckets[bucketIndex].add(entry);
        } else {
            buckets[bucketIndex].add(entry);
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
            return "{" + key + "=" + value + "}";
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
                if (buckets[countBuckets] == null) {
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
                    return targetEntry;
                }
                countBuckets++;
                bucketIterator = null;
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

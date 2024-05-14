package org.example.map;

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

    @SuppressWarnings("unchecked")
    public EntryHashMap(int bucketsCapacity) {
        buckets = new Entry[bucketsCapacity];
    }

    @Override
    public V put(K key, V value) {
        growBuckets();
        int hashCode = hash(key);
        int bucketIndex = getBucketIndex(key, buckets.length);
        Entry<K, V> entry = buckets[bucketIndex];
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new Entry<>(hashCode, key, value);
            size++;
            return null;
        }
        while (true) {
            if (entry.hashCode == hashCode && Objects.equals(key, entry.key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
            if (entry.next == null) {
                entry.next = new Entry<>(hashCode, key, value);
                size++;
                return null;
            }
            entry = entry.next;
        }
    }

    @Override
    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        return entry == null ? null : Objects.requireNonNull(entry).value;
    }

    @Override
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key, buckets.length);
        Entry<K, V> entry = buckets[bucketIndex];
        Entry<K, V> entryPrev = buckets[bucketIndex];
        int hashCode = hash(key);
        if (entry == null) {
            return null;
        } else if (hashCode == entry.hashCode && Objects.equals(key, entry.key)) {
            buckets[bucketIndex] = buckets[bucketIndex].next;
        } else {
            do {
                entryPrev = entry;
                if (entry.next == null) {
                    return null;
                }
                entry = entry.next;
            } while (hashCode != entry.hashCode || !Objects.equals(entry.key, key));
            entryPrev.next = entry.next;
        }
        size--;
        return entry.value;
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
        int bucketIndex = getBucketIndex(key, buckets.length);
        Entry<K, V> entry = buckets[bucketIndex];
        if (entry == null) {
            return null;
        } else {
            while (true) {
                if (hash(key) == entry.hashCode && Objects.equals(key, entry.key)) {
                    return entry;
                }
                if (entry.next == null) {
                    return null;
                }
                entry = entry.next;
            }
        }
    }

    int getBucketIndex(K key, int bucketLength) {
        if (key != null) {
            int hash = Math.abs(key.hashCode());
            if (hash == Integer.MIN_VALUE) {
                hash = Integer.MAX_VALUE;
            }
            return hash % bucketLength;
        }
        return 0;
    }

    private int hash(K key) {
        return key == null ? 0 : key.hashCode();
    }

    @SuppressWarnings("unchecked")
    void growBuckets() {
        if (size >= buckets.length * LOAD_FACTOR) {
            Entry<K, V>[] newBuckets = new Entry[buckets.length * GROW_fACTOR + 1];
            for (Map.Entry<K, V> entry : this) {
                refillBuckets((Entry<K, V>) entry, newBuckets);
            }
            buckets = newBuckets;
        }
    }

    private void refillBuckets(Entry<K, V> entry, Entry<K, V>[] buckets) {
        int bucketIndex = getBucketIndex(entry.key, buckets.length);
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = entry;
        } else {
            Entry<K, V> targetEntry = buckets[bucketIndex];
            while (targetEntry.next != null) {
                targetEntry = targetEntry.next;
            }
            targetEntry.next = entry;
        }
    }

    static class Entry<K, V> implements Map.Entry<K, V> {
        private final int hashCode;
        private final K key;
        private V value;
        private Entry<K, V> next;

        private Entry(int hashCode, K key, V value) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
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

    private class HashMapIterator implements Iterator<Map.Entry<K, V>> {
        private int countEntries;
        private int countBuckets;
        private boolean isNextUsed;
        private Entry<K, V> targetEntry;
        private Entry<K, V> entry;

        @Override
        public boolean hasNext() {
            return countEntries != size;
        }

        @Override
        public Map.Entry<K, V> next() {
            while (hasNext()) {
                if (entry == null) {
                    entry = buckets[countBuckets];
                }
                if (buckets[countBuckets] == null) {
                    countBuckets++;
                    entry = buckets[countBuckets];
                } else {
                    if (entry.next == null) {
                        targetEntry = entry;
                        countEntries++;
                        if (countBuckets < buckets.length - 1) {
                            countBuckets++;
                        }
                        isNextUsed = true;
                        entry = buckets[countBuckets];
                        return targetEntry;
                    }
                    targetEntry = entry;
                    entry = targetEntry.next;
                    countEntries++;
                    isNextUsed = true;
                    return targetEntry;
                }
            }
            throw new NoSuchElementException("No next element.");
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

package com.rynkovoy.map;

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
            buckets[bucketIndex] = new Entry<>(hashCode, key, value, bucketIndex);
        } else {
            for (Entry<K, V> targetEntry : entry) {
                if (targetEntry.hashCode == hashCode && Objects.equals(key, targetEntry.key)) {
                    V oldValue = targetEntry.value;
                    targetEntry.value = value;
                    return oldValue;
                }
                if (targetEntry.next == null) {
                    targetEntry.next = new Entry<>(hashCode, key, value, bucketIndex);
                }
            }
        }
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
        int bucketIndex = getBucketIndex(key, buckets.length);
        Entry<K, V> entry = buckets[bucketIndex];
        int hashCode = hash(key);
        if (entry != null) {
            Iterator<Entry<K, V>> iterator = entry.iterator(buckets);
            while (iterator.hasNext()) {
                Entry<K, V> nextEntry = iterator.next();
                if (hashCode == nextEntry.hashCode && Objects.equals(key, nextEntry.key)) {
                    iterator.remove();
                    size--;
                    return nextEntry.value;
                }
            }
        }
        return null;
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

    Entry<K, V> getEntry(K key) {
        int bucketIndex = getBucketIndex(key, buckets.length);
        Entry<K, V> entry = buckets[bucketIndex];
        if (entry != null) {
            for (Entry<K, V> targetEntry : entry) {
                if (hash(key) == targetEntry.hashCode && Objects.equals(key, targetEntry.key)) {
                    return targetEntry;
                }
            }
        }
        return null;
    }

    int getBucketIndex(K key, int bucketLength) {
        int hash = Math.abs(hash(key));
        if (hash == Integer.MIN_VALUE) {
            hash = Integer.MAX_VALUE;
        }
        return hash % bucketLength;
    }

    private int hash(K key) {
        return key == null ? 0 : key.hashCode();
    }

    @SuppressWarnings("unchecked")
    private void growBuckets() {
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
        entry.index = bucketIndex;
    }

    static class Entry<K, V> implements Map.Entry<K, V>, Iterable<Entry<K, V>> {
        private final int hashCode;
        private final K key;
        private V value;
        private Entry<K, V> next;
        private int index;

        private Entry(int hashCode, K key, V value, int index) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
            this.index = index;
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

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        public Iterator<Entry<K, V>> iterator(Entry<K, V>[] buckets) {
            return new EntryIterator(buckets);
        }

        class EntryIterator implements Iterator<Entry<K, V>> {
            private Entry<K, V> currentEntry = Entry.this;
            private boolean isNextUsed;
            private Entry<K, V> targetEntry;
            private Entry<K, V> targetPrev;
            private Entry<K, V>[] buckets;

            public EntryIterator() {
            }

            public EntryIterator(Entry<K, V>[] buckets) {
                this.buckets = buckets;
            }

            @Override
            public boolean hasNext() {
                return currentEntry != null;
            }

            @Override
            public Entry<K, V> next() {
                targetPrev = targetEntry;
                targetEntry = currentEntry;
                currentEntry = currentEntry.next;
                isNextUsed = true;
                return targetEntry;
            }

            @Override
            public void remove() {
                if (!isNextUsed) {
                    throw new IllegalStateException("Method next() not used.");
                }
                if (targetEntry.next == null) {
                    targetEntry = null;
                } else if (targetPrev == null) {
                    buckets[index] = targetEntry.next;
                } else {
                    targetPrev.next = targetEntry.next;
                }
                isNextUsed = false;
            }
        }
    }

    private class HashMapIterator implements Iterator<Map.Entry<K, V>> {
        private int counter;
        private int bucketIndex;
        private boolean isNextUsed;
        private Iterator<Entry<K, V>> bucketIterator;

        @Override
        public boolean hasNext() {
            return counter != size;
        }

        @Override
        public Map.Entry<K, V> next() {
            while (hasNext()) {
                if (buckets[bucketIndex] == null) {
                    bucketIterator = null;
                    bucketIndex++;
                    continue;
                } else if (bucketIterator == null) {
                    bucketIterator = buckets[bucketIndex].iterator(buckets);
                }
                if (bucketIterator.hasNext()) {
                    Entry<K, V> targetEntry = bucketIterator.next();
                    counter++;
                    isNextUsed = true;
                    return targetEntry;
                }
                bucketIndex++;
                bucketIterator = null;
            }
            throw new NoSuchElementException("No next element.");
        }

        @Override
        public void remove() {
            if (!isNextUsed) {
                throw new IllegalStateException("Method next() not used.");
            }
            bucketIterator.remove();
            size--;
            counter--;
        }
    }
}

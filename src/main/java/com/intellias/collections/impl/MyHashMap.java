package com.intellias.collections.impl;

import com.intellias.collections.MyMap;

import java.lang.reflect.Array;
import java.util.Arrays;


public class MyHashMap<K, V> implements MyMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75F;
    private Entry<K, V>[] table;

    private int size;

    public MyHashMap() {
        table = (Entry<K, V>[]) Array.newInstance(Entry.class, INITIAL_CAPACITY);
        size = 0;
    }

    private static class Entry<K, V> {
        int hash;
        K key;
        V value;
        Entry<K, V> next;

        public Entry(int hash, K key, V value, Entry<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    public V put(K key, V value) {
        if (key == null) {
            return null;
        }

        if (size > table.length * LOAD_FACTOR) {
            rebuild();
        }

        int index = getKeyBucketIndex(key.hashCode(), table.length);

        Entry<K, V> entry = table[index];

        if (entry == null) {
            table[index] = new Entry<>(key.hashCode(), key, value, null);
            size++;
            return value;
        }

        Entry<K, V> currentEntry = entry;

        while (entry != null) {
            if (entry.hash == key.hashCode()) {
                entry.value = value;
                return value;
            }
            currentEntry = entry;
            entry = entry.next;
        }

        currentEntry.next = new Entry<>(key.hashCode(), key, value, null);

        size++;

        return value;
    }

    public int size() {
        return size;
    }

    public void clear() {
        table = (Entry<K, V>[]) Array.newInstance(Entry.class, INITIAL_CAPACITY);
        size = 0;
    }

    public V get(Object key) {
        int index = getKeyBucketIndex(key.hashCode(), table.length);
        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    public V remove(Object key) {
        int index = getKeyBucketIndex(key.hashCode(), table.length);
        Entry<K, V> entry = table[index];
        Entry<K, V> currentEntry = entry;

        if (entry.key.equals(key)) {
            table[index] = entry.next;
            size--;
            return entry.value;
        }

        while (entry != null) {
            if (entry.key.equals(key)) {
                currentEntry.next = entry.next;
                entry.next = null;
                size--;

                return entry.value;
            }
            currentEntry = entry;
            entry = entry.next;
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Arrays.stream(table)
                .forEach(entry -> {
                    while (entry != null) {
                        builder.append(entry).append(",");
                        entry = entry.next;
                    }
                });

        return builder.append("]").toString();
    }

    private void rebuild() {
        int newCapacity = table.length * 2;
        Entry<K, V>[] newTable = (Entry<K, V>[]) Array.newInstance(Entry.class, newCapacity);
        for (Entry<K, V> entry : table) {
            while (entry != null) {
                Entry<K, V> next = entry.next;
                int i = getKeyBucketIndex(entry.hash, newCapacity);
                entry.next = newTable[i];
                newTable[i] = entry;
                entry = next;
            }
        }
    }

    private int getKeyBucketIndex(int hash, int n) {
        return hash & (n - 1);
    }

}

package com.intellias.collections;

public interface MyMap<K, V> {
    V put(K key, V value);

    V remove(K key);

    V get(K key);

    void clear();

    int size();
}

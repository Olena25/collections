package com.intellias.collections;

public interface Queue<E> {
    void add(E e);

    boolean remove(E e);

    void clear();

    int size();

    E poll();

    E peek();

}

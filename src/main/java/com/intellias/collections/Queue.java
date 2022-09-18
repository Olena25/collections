package com.intellias.collections;

public interface Queue<E> {
    boolean add(E e);

    E remove(E e);

    void clear();

    int size();

    E poll();

    E peek();

}

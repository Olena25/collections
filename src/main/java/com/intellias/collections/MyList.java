package com.intellias.collections;

public interface MyList<E> {
    E get(int index);

    boolean add(E e);

    E remove(int index);

    void clear();

    int size();
}

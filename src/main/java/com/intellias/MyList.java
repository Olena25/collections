package com.intellias;

public interface MyList<E> {
    void add(E e);
    void remove(int index);
    E get(int index);

    void clear();
    int size();
}

package com.intellias.collections.impl;

import com.intellias.collections.MyList;

import java.util.Arrays;

public class MyArrayList<E> implements MyList<E> {

    private static final int INITIAL_CAPACITY = 10;
    private static final double LOAD_FACTOR = 0.75f;

    private int currentSize;

    private int threshold;
    private E[] array;

    MyArrayList() {
        array = (E[]) new Object[INITIAL_CAPACITY];
        currentSize = 0;
        threshold = (int) (INITIAL_CAPACITY * LOAD_FACTOR);
    }

    public void clear() {
        array = (E[]) new Object[INITIAL_CAPACITY];
        currentSize = 0;
        threshold = (int) (INITIAL_CAPACITY * LOAD_FACTOR);
    }

    public int size() {
        return currentSize;
    }

    public E get(int index) {
        checkIndex(index);
        return array[index];
    }

    public boolean add(E e) {
        array[currentSize] = e;
        currentSize++;


        if (currentSize == threshold) {
            rebuild();
        }
        return true;
    }

    public E remove(int index) {
        checkIndex(index);
        E[] newArray = (E[]) new Object[array.length - 1];

        E removedElement = array[index];

        for (int i = 0; i < newArray.length; i++) {
            if (i >= index) {
                newArray[i] = array[i + 1];
            } else {
                newArray[i] = array[i];
            }
        }

        array = newArray;
        currentSize--;

        return removedElement;
    }

    private void rebuild() {
        int newCapacity = (array.length * 3) / 2 + 1;
        threshold = (int) (newCapacity * LOAD_FACTOR);

        E[] newArray = (E[]) new Object[newCapacity];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be negative");
        }
        if (index > currentSize) {
            throw new IndexOutOfBoundsException("Current list size is " + currentSize);
        }

    }
}

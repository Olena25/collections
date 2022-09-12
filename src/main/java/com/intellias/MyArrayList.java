package com.intellias;

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
        Arrays.fill(array, null);
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

    public void add(E e) {
        array[currentSize] = e;
        currentSize++;

        if (currentSize == threshold) {
            rebuild();
        }
    }

    public void remove(int index) {
        checkIndex(index);
        E[] newArray = (E[]) new Object[array.length - 1];

        for (int i = 0; i < newArray.length; i++) {
            if (i >= index) {
                newArray[i] = array[i + 1];
            } else {
                newArray[i] = array[i];
            }
        }

        array = newArray;
        currentSize--;
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

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Index cannot be negative");
        }
        if (index > currentSize) {
            throw new IndexOutOfBoundsException("Current list size is " + currentSize);
        }
    }

    public static void main(String[] args) {
        MyList<String> stringMyList = new MyArrayList<>();
        stringMyList.add("k");
        stringMyList.add("m");
        stringMyList.add("c");
        System.out.println(stringMyList.get(1));
        System.out.println(stringMyList.size());
        stringMyList.clear();
        System.out.println(stringMyList.get(0));

    }
}

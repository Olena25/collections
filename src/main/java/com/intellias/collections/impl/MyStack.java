package com.intellias.collections.impl;

public class MyStack<E> {

    private Node<E> header;
    private int size;

    MyStack() {
        size = 0;
    }

    private static class Node<E> {
        E element;
        Node<E> next;


        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;

        }
    }

    public void push(E e) {
        if (e == null) {
            throw new NullPointerException();
        }
        size++;

        header = new Node<>(e, header);
    }

    public void remove(int index) {
        verifyIndex(index);

        if (index == 0) {
            header = header.next;
        }

        Node<E> element = header;

        for (int i = 0; i < index - 1; i++) {
            element = element.next;
        }

        if (index == size - 1) {
            element.next = null;
            size--;
            return;
        }

        Node<E> nextNode = element.next;
        element.next = nextNode.next;
        size--;
    }

    public void clear() {
        header = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public E peek() {
        if (header == null) {
            return null;
        }
        return header.element;
    }

    public E pop() {
        if (header == null) {
            return null;
        }
        E element = header.element;
        header = header.next;
        size--;

        return element;
    }

    private void verifyIndex(int index) {
        if (size == 0 || size - 1 < index || index < 0) {
            throw new IndexOutOfBoundsException("Size of the list is " + size);
        }
    }
}



package com.intellias.collections.impl;

import com.intellias.collections.MyList;

public class MyLinkedList<E> implements MyList<E> {
    private Node<E> header;
    private int size;

    MyLinkedList() {
        size = 0;
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public boolean add(E e) {
        size++;
        if (header == null) {
            header = new Node<>(e, null, null);
            return true;
        }
        Node<E> element = header;
        while (element.next != null) {
            element = element.next;
        }
        element.next = new Node<>(e, null, element);
        return true;
    }

    public E remove(int index) {
        verifyIndex(index);

        if (index == 0) {
            E removedElement = header.element;
            header = header.next;
            return removedElement;
        }

        Node<E> element = header;

        for (int i = 0; i < index; i++) {
            element = element.next;
        }

        replacePointers(element);

        size--;

        return element.element;
    }

    @Override
    public E get(int index) {
        verifyIndex(index);
        Node<E> element = header;
        for (int i = 0; i < index; i++) {
            element = element.next;
        }
        return element.element;
    }

    @Override
    public void clear() {
        header = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node<E> element = header;
        if (header == null) {
            return "[]";
        }
        builder.append(element.element);
        while (element.next != null) {
            builder.append(", ");
            element = element.next;
            builder.append(element.element);
        }
        return builder.toString();
    }

    private void verifyIndex(int index) {
        if (size == 0 || size - 1 < index || index < 0) {
            throw new IndexOutOfBoundsException("Size of the list is " + size);
        }
    }

    private void replacePointers(Node<E> element) {
        if (element.next == null) {
            Node<E> prevNode = element.prev;
            prevNode.next = null;

            return;
        }

        Node<E> prevNode = element.prev;
        Node<E> nextNode = element.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

}

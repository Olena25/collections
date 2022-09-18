package com.intellias.collections.impl;

import com.intellias.collections.Queue;

public class MyQueue<E> implements Queue<E> {

    private Node<E> header;
    private int size;

    MyQueue() {
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

    @Override
    public boolean add(E e) {
        if (e == null) {
            return false;
        }
        size++;

        if (header == null) {
            header = new Node<>(e, null, null);
            return true;
        }

        Node<E> element = header.next;

        while (element.next != null) {
            element = element.next;
        }

        element.next = new Node<>(e, null, element);

        return true;
    }

    public E remove(E e) {
        if (e == null) {
            return null;
        }

        Node<E> element = header;

        if (header.element.equals(e)) {
            header = header.next;

            return e;
        }

        while (element != null) {
            if (element.element.equals(e)) {
                replacePointers(element);
                size--;

                return e;
            }
            element = element.next;
        }

        return null;
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

    public E poll() {
        if (header == null) {
            return null;
        }
        E element = header.element;
        header = header.next;
        size--;

        return element;
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

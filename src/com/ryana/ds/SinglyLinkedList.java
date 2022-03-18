package com.ryana.ds;

import java.util.Objects;
import java.util.stream.Stream;

public class SinglyLinkedList<T> {

    private int size;
    private Node<T> head;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clearAll() {
        head = null;
        size = 0;
    }

    public void addHead(T data) {
        head = new Node<>(data, head);
        size++;
    }

    public void addTail(T data) {
        if (isEmpty()) {
            addHead(data);
            return; 
        }
        Stream.iterate(head, Objects::nonNull, Node::getNext)
              .skip(size-1)
              .findFirst()
              .ifPresent(node -> {
                  node.setNext(new Node(data));
                  size++;
                });
    }

    public void reverse() {
        if (isEmpty()) {
            throw new RuntimeException("Empty!!!!");
        }
        Node<T> next = null;
        Node<T> current = head;
        Node<T> prev = null;

        while (Objects.nonNull(current)) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        head = prev;

    }

    public void printAll() {
        if(isEmpty()) {
            throw new RuntimeException("Empty!!!!");
        }

        Stream.iterate(head, Objects::nonNull, Node::getNext)
              .limit(size)
              .map(Node::getData)
              .forEach(System.out::println);
    }

    private static final class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(final T data) {
            this.data = data;
        }

        public Node(final T data, final Node<T> next) {
            this(data);
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(final Node<T> next) {
            this.next = next;
        }

    }

}

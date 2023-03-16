package com.ryana.ds;

import java.util.EmptyStackException;
import java.util.Objects;
import java.util.stream.Stream;

public class DoublyLinkedList<E> {
    private int size;
    private Node<E> head;
    private Node<E> tail;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clearAll() {
        head = tail = null;
        size = 0;
    }

    public void addHead(final E element) {
        head = new Node<E>(element, head);
        if (isEmpty()) {
            tail = head;
        }
        size++;
    }

    public void addTail(final E element) {
        tail = new Node<E>(tail, element);
        if (isEmpty()) {
            head = tail;
        }
        size++;
    }

    public E getFirst() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return head.getElement();
    }

    public E getLast() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return tail.getElement();
    }

    public E removeFirst() {
        final var element = getFirst();
        head = head.getNext();
        head.setPrev(null);
        size--;
        return element;
    }

    public E removeLast() {
        final var element = getLast();
        tail = tail.getPrev();
        tail.setNext(null);
        size--;
        return element;
    }

    public void printForward() {
        Stream.iterate(head, Objects::nonNull, Node::getNext)
                .map(Node::getElement)
                .forEach(System.out::println);
    }

    public void printBackward() {
        Stream.iterate(tail, Objects::nonNull, Node::getPrev)
                .map(Node::getElement)
                .forEach(System.out::println);
    }

    public void reverse() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Stream.iterate(head, Objects::nonNull, Node::getPrev)
                .forEach(Node::swap);

        final var node = head;
        head = tail;
        tail = node;
    }

    private static final class Node<E> {

        private Node<E> prev;
        private final E element;
        private Node<E> next;

        private Node(final E element) {
            this.element = element;
        }

        public Node(final E element, final Node<E> next) {
            this(element);
            this.next = next;
            if (Objects.nonNull(next)) {
                next.setPrev(this);
            }
        }

        public Node(final Node<E> prev, final E element) {
            this(element);
            this.prev = prev;
            if (Objects.nonNull(prev)) {
                prev.setNext(this);
            }
        }

        public void swap() {
            final var temp = prev;
            prev = next;
            next = temp;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(final Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(final Node<E> next) {
            this.next = next;
        }

    }

}

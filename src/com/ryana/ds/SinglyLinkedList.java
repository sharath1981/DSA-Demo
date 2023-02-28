package com.ryana.ds;

import java.util.Objects;
import java.util.Stack;
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
                .skip(size - 1)
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

    public void reverse1() {
        if (isEmpty()) {
            throw new RuntimeException("Empty!!!!");
        }
        Stream.iterate(head, Objects::nonNull, Node::getNext)
                .limit(size)
                .forEach(x -> {
                    final var next = x.getNext();
                    x.setNext(null);
                });

    }

    public boolean isPalindrome() {
        if (isEmpty()) {
            throw new RuntimeException("Empty!!!!");
        }
        final var stack = new Stack<T>();
        Stream.iterate(head, Objects::nonNull, Node::getNext)
                .limit(size)
                .map(Node::getData)
                .forEach(stack::push);

        return Stream.iterate(head, Objects::nonNull, Node::getNext)
                .limit(size)
                .map(Node::getData)
                .allMatch(e -> e.equals(stack.pop()));

    }

    public Node<T> midPoint() {
        if (isEmpty()) {
            throw new RuntimeException("Empty!!!!");
        }
        Node<T> slow = head, fast = head;
        while (Objects.nonNull(fast) && Objects.nonNull(fast.getNext())) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    public void printAll() {
        if (isEmpty()) {
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

    public static void main(String[] args) throws Exception {
        final var list = new SinglyLinkedList<String>();
        list.addHead("A");
        list.addHead("B");
        list.addHead("C");
        list.addHead("D");
        list.addHead("E");

        list.printAll();
        list.reverse();
        list.printAll();
        System.out.println("mid point => " + list.midPoint().getData());
        System.out.println("isPalindrome => " + list.isPalindrome());

    }

}

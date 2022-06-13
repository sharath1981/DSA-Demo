package com.ryana.lru;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class LRUCache<K, V> {

    private final int capacity;
    private Node<K, V> head;
    private Node<K, V> tail;
    private Map<K, Node<K, V>> cache;

    public LRUCache(final int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
    }

    public boolean isFull() {
        return capacity == cache.size();
    }

    public int size() {
        return cache.size();
    }

    private Map<K, Node<K, V>> getCache() {
        return cache;
    }

    public void put(final K key, final V value) {
        if (isFull()) {
            cache.remove(tail.getKey());
            deleteNode(tail);
        }
        deleteNode(cache.get(key));
        cache.put(key, addFirst(key, value));
    }

    public V get(final K key) {
        return Optional.ofNullable(cache.get(key))
                .map(this::deleteNode)
                .map(this::addFirst)
                .map(Node::getValue)
                .orElse(null);
    }

    private Node<K, V> deleteNode(final Node<K, V> node) {
        if (Objects.nonNull(node)) {
            final var prev = node.getPrevious();
            final var next = node.getNext();
            if (Objects.nonNull(prev)) {
                prev.setNext(next);
            } else {
                head = next;
            }
            if (Objects.nonNull(next)) {
                next.setPrevious(prev);
            } else {
                tail = prev;
            }
        }
        return node;
    }

    private Node<K, V> addFirst(final Node<K, V> node) {
        return addFirst(node.getKey(), node.getValue());
    }

    private Node<K, V> addFirst(final K key, final V value) {
        head = new Node<>(key, value, head);
        if (Objects.isNull(tail)) {
            tail = head;
        }
        return head;
    }

    private static final class Node<K, V> {
        private Node<K, V> previous;
        private final K key;
        private final V value;
        private Node<K, V> next;

        public Node(final K key, final V value) {
            this.key = key;
            this.value = value;
        }

        public Node(final K key, final V value, final Node<K, V> next) {
            this(key, value);
            this.next = next;
            if (Objects.nonNull(next)) {
                next.setPrevious(this);
            }
        }

        public Node<K, V> getPrevious() {
            return previous;
        }

        public void setPrevious(final Node<K, V> previous) {
            this.previous = previous;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(final Node<K, V> next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        final var lrucache = new LRUCache<Integer, Integer>(5);
        lrucache.put(1, 1);
        lrucache.put(2, 2);
        lrucache.put(3, 3);
        lrucache.put(4, 4);
        lrucache.put(5, 5);
        lrucache.put(6, 6);
        System.out.println(lrucache.getCache());
        System.out.println(lrucache.get(2));
        lrucache.put(7, 7);
        System.out.println(lrucache.getCache());

    }
}

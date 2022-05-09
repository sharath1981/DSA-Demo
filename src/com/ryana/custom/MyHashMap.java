package com.ryana.custom;

import java.util.Objects;
import java.util.stream.Stream;

public class MyHashMap<K, V> {

    private final Entry<K, V> bucket[];
    private int initialCapacity = 16;
    private int size;

    public MyHashMap() {
        bucket = new Entry[initialCapacity];
    }

    public MyHashMap(final int initialCapacity) {
        this.initialCapacity = initialCapacity;
        bucket = new Entry[initialCapacity];
    }

    public int size() {
        return size;
    }

    public void put(final K key, final V value) {
        final int index = indexOf(key);
        final var entry = bucket[index];
        if (Objects.isNull(entry)) {
            bucket[index] = new Entry<>(key, value);
            size++;
        } else {
            for (var node = entry; Objects.nonNull(node); node = node.getNext()) {
                if (Objects.equals(node.getKey(), key)) {
                    node.setValue(value);
                    return;
                } else if (Objects.isNull(node.getNext())) {
                    node.setNext(new Entry<>(key, value));
                    size++;
                    return;
                }
            }
        }
    }

    public V get(final K key) {
        final var entry = bucket[indexOf(key)];
        return Stream.iterate(entry, Objects::nonNull, Entry::getNext)
                .filter(node -> Objects.equals(key, node.getKey()))
                .findFirst()
                .map(Entry::getValue)
                .orElse(null);
    }

    public V remove(final K key) {
        final int index = indexOf(key);
        final var entry = bucket[index];
        if (Objects.isNull(entry)) {
            return null;
        } else if (Objects.equals(entry.getKey(), key)) {
            bucket[index] = entry.getNext();
            size--;
            return entry.getValue();
        } else {
            for (var node = entry; Objects.nonNull(node.getNext()); node = node.getNext()) {
                if (Objects.equals(node.getNext().getKey(), key)) {
                    final var value = node.getNext().getValue();
                    node.setNext(node.getNext().getNext());
                    size--;
                    return value;
                }
            }
            return null;
        }

    }

    private int indexOf(final K key) {
        return Objects.hashCode(key) % initialCapacity;
    }

    static final class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        public Entry(final K key, final V value) {
            this.key = key;
            this.value = value;
        }

        public Entry(final K key, final V value, final Entry<K, V> next) {
            this(key, value);
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(final V value) {
            this.value = value;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setNext(final Entry<K, V> next) {
            this.next = next;
        }
    }
}

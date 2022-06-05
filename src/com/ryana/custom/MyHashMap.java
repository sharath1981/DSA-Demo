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

    public boolean isEmpty() {
        return size == 0;
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
        final int index = indexOf(key);
        final var entry = bucket[index];
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

    public void printAll() {
        if (isEmpty()) {
            throw new RuntimeException(" Empty Hashmap...");
        }
        for (var entry : bucket) {
            while (Objects.nonNull(entry)) {
                System.out.println(entry);
                entry = entry.getNext();
            }
        }
    }

    private int indexOf(final K key) {
        return Objects.hashCode(key) % initialCapacity;
    }

    private static final class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        public Entry(final K key, final V value) {
            this.key = key;
            this.value = value;
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

        @Override
        public String toString() {
            return "Entry [key=" + key + ", value=" + value + "]";
        }

    }
}

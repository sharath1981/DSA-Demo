package com.ryana.lru;

import java.util.LinkedHashMap;
import java.util.Optional;

public class LRUCache1<K, V> {
    private int capacity;
    private LinkedHashMap<K, V> cache;

    public LRUCache1(int capacity) {
        this.capacity = capacity;
        cache = new LinkedHashMap<>();
    }

    public V get(final K key) {
        return Optional.ofNullable(cache.get(key))
                .map(value -> put(key, value))
                .orElse(null);
    }

    public V put(final K key, final V value) {
        if (capacity == cache.size()) {
            final var it = cache.keySet().iterator();
            it.next();
            it.remove();
        }
        cache.remove(key);
        return cache.put(key, value);
    }

    private LinkedHashMap<K, V> getCache() {
        return cache;
    }

    public static void main(String[] args) {
        final var lru = new LRUCache1<Integer, Integer>(3);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.get(1);
        lru.put(4, 4);
        lru.get(2);
        lru.put(5, 5);

        System.out.println(lru.getCache());

    }

}

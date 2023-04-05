package com.ryana.lru;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache2<K, V> {
    private final Map<K, V> cache;

    public Map<K, V> getCache() {
        return cache;
    }

    public LRUCache2(int capacity) {
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > capacity;
            }
        };
    }

    public synchronized V get(K key) {
        return cache.get(key);
    }

    public synchronized void put(K key, V value) {
        cache.put(key, value);
    }

    public synchronized void clear() {
        cache.clear();
    }

    public synchronized int size() {
        return cache.size();
    }

    public static void main(String[] args) {
        final var lru = new LRUCache2<Integer, Integer>(3);
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

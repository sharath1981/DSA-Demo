package com.ryana.custom;

public class App {
    public static void main(String[] args) {
        final var map = new MyHashMap<String, Integer>();
        map.put(null, null);
        map.put("AaAa", 1);
        map.put("BBBB", 2);
        map.put("AaBB", 3);
        map.put("BBAa", 4);
        map.put("D", 5);

        map.put(null, 7);
        map.put("E", null);

        System.out.println(map.size());
        System.out.println(map.get("AaAa"));
        System.out.println(map.get("BBBB"));
        System.out.println(map.get("AaBB"));
        System.out.println(map.get("BBAa"));
        System.out.println(map.get(null));
        System.out.println(map.get("xx"));
        System.out.println(map.remove("AaAa"));
        System.out.println(map.remove("D"));
        System.out.println(map.size());

    }
}

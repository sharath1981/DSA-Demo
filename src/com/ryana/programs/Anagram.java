package com.ryana.programs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Collectors;

public class Anagram {

    public static void main(final String[] args) {
        final var str1 = "basiparachromatin";
        final var str2 = "marsipobranchiata";
        System.out.println(anagram1(str1, str2));
        System.out.println(anagram2(str1, str2));
        System.out.println(anagram3(str1, str2));
        System.out.println(anagram4(str1, str2));
        System.out.println(anagram5(str1, str2));
    }

    private static boolean anagram1(final String str1, final String str2) {
        if (isNotValid(str1, str2)) {
            return false;
        }
        final var charArray1 = str1.toLowerCase().toCharArray();
        final var charArray2 = str2.toLowerCase().toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);
        return Arrays.equals(charArray1, charArray2);
    }

    private static boolean isNotValid(final String str1, final String str2) {
        return Objects.isNull(str1) || Objects.isNull(str2) || str1.length() != str2.length();
    }

    private static boolean anagram2(String str1, String str2) {
        if (isNotValid(str1, str2)) {
            return false;
        }
        final var set = new HashSet<Character>();
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        for (int i = 0; i < str1.length(); i++) {
            set.add(str1.charAt(i));
        }
        for (int i = 0; i < str2.length(); i++) {
            if (set.add(str2.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean anagram3(String str1, String str2) {
        if (isNotValid(str1, str2)) {
            return false;
        }
        final var set = str1.toLowerCase()
                .chars()
                .boxed()
                .collect(Collectors.toSet());

        return str2.toLowerCase()
                .chars()
                .boxed()
                .noneMatch(set::add);
    }

    private static boolean anagram4(String str1, String str2) {
        if (isNotValid(str1, str2)) {
            return false;
        }
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        final var counts = new int[256];
        for (int i = 0; i < str1.length(); i++) {
            counts[str1.charAt(i)]++;
            counts[str2.charAt(i)]--;
        }
        return Arrays.stream(counts).allMatch(count -> count == 0);
    }

    private static boolean anagram5(String str1, String str2) {
        if (isNotValid(str1, str2)) {
            return false;
        }
        final var counts = new int[256];
        str1.toLowerCase().chars().forEach(i -> ++counts[i]);
        str2.toLowerCase().chars().forEach(i -> --counts[i]);

        return Arrays.stream(counts).allMatch(count -> count == 0);
    }
}

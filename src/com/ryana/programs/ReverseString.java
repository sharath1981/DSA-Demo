package com.ryana.programs;

import java.util.Objects;

public class ReverseString {

    public static void main(final String[] args) {
        final var original = "original";
        System.out.println(reverse(original));
        System.out.println(reverse1(original));
        System.out.println(reverse2(original));
    }

    private static String reverse(final String original) {
        if (!isValid(original)) {
            return original;
        }
        final var chars = original.toCharArray();
        var reversed = "";
        for (final char ch : chars) {
            reversed = ch + reversed;
        }
        return reversed;
    }

    private static String reverse1(final String original) {
        if (!isValid(original)) {
            return original;
        }
        final var reversed = new char[original.length()];
        for (int i = 0; i <= original.length() / 2; i++) {
            reversed[i] = original.charAt(original.length() - 1 - i);
            reversed[original.length() - 1 - i] = original.charAt(i);
        }
        return String.valueOf(reversed);
    }

    private static String reverse2(final String original) {
        if (!isValid(original)) {
            return original;
        }
        return original.charAt(original.length() - 1)
                + reverse2(original.substring(0, original.length() - 1));

    }

    private static boolean isValid(final String original) {
        return Objects.nonNull(original) && !original.isBlank();
    }
}

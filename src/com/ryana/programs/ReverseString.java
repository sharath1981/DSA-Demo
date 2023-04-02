package com.ryana.programs;

import java.util.Objects;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReverseString {

    public static void main(final String[] args) {
        final var original = "original";
        if (isValid(original)) {
            System.out.println(reverse(original));
            System.out.println(reverse1(original));
            System.out.println(reverse2(original));
            System.out.println(reverse3(original));
            System.out.println(reverse4(original));
            System.out.println(reverse5(original));
            System.out.println(reverse6(original));
        } else {
            System.out.println("Invalid String...");
        }
    }

    private static String reverse(final String original) {
        final var chars = original.toCharArray();
        var reversed = "";
        for (final char ch : chars) {
            reversed = ch + reversed;
        }
        return reversed;
    }

    private static String reverse1(final String original) {
        final var reversed = new char[original.length()];
        for (int i = 0; i <= original.length() / 2; i++) {
            reversed[i] = original.charAt(original.length() - 1 - i);
            reversed[original.length() - 1 - i] = original.charAt(i);
        }
        return String.valueOf(reversed);
    }

    private static String reverse2(final String original) {
        if (original.isBlank()) {
            return original;
        }
        return original.charAt(original.length() - 1)
                + reverse2(original.substring(0, original.length() - 1));
    }

    private static String reverse3(final String original) {
        final var reversed = original.toCharArray();
        for (int i = 0; i <= reversed.length / 2; i++) {
            final var temp = reversed[i];
            reversed[i] = reversed[reversed.length - 1 - i];
            reversed[reversed.length - 1 - i] = temp;
        }
        return String.valueOf(reversed);
    }

    private static String reverse4(final String original) {
        final var stack = new Stack<Character>();
        IntStream.range(0, original.length())
                .mapToObj(original::charAt)
                .forEach(stack::push);
        return Stream.generate(stack::pop).limit(original.length()).map(String::valueOf)
                .collect(Collectors.joining());
    }

    private static String reverse5(final String original) {
        return IntStream.range(0, original.length())
                .map(original::charAt)
                .mapToObj(Character::toString)
                .reduce("", (a, b) -> b + a);
    }

    private static String reverse6(final String original) {
        return original.chars() // original.codePoints()
                .mapToObj(Character::toString)
                .reduce("", (a, b) -> b + a);
    }

    private static boolean isValid(final String original) {
        return Objects.nonNull(original) && !original.isBlank();
    }
}

package com.ryana.programs;

import java.util.function.Function;
import java.util.stream.Collectors;

public class StringCharacterOccurrences {
    public static void main(final String[] args) {
        final var str = "asdfdfksdfvlsdkjfflsdfdfervvvvdsf";
        findCharacterOccurrences(str);
    }

    private static void findCharacterOccurrences(final String str) {
        str.chars()
                .mapToObj(Character::toString)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((k, v) -> System.out.println(k + " : " + v));
    }
}

package com.ryana.custom;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class Bracket {
    private static final Map<Character, Character> bracketMap;
    static {
        bracketMap = new HashMap<>();
        bracketMap.put('{', '}');
        bracketMap.put('(', ')');
        bracketMap.put('[', ']');
    }

    public static void main(final String[] args) {
        final var str = "{[{}()[]]}";
        System.out.println(isBalanced(str));
    }

    private static boolean isBalanced(final String str) {
        if (!isValid(str)) {
            return false;
        }
        final var stack = new Stack<Character>();
        final var characters = str.toCharArray();
        for (final var ch : characters) {
            final var close = bracketMap.get(ch);
            if (Objects.nonNull(close)) {
                stack.push(close);
            } else if (ch == stack.peek()) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    private static boolean isValid(final String str) {
        return !(Objects.isNull(str) || str.isBlank() || str.length() % 2 != 0);
    }

}

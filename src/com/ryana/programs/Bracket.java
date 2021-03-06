package com.ryana.programs;

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
        return isValid(str) && isBalancedUsingStack(str);
    }

    private static boolean isBalancedUsingStack(final String str) {
        final var stack = new Stack<Character>();
        final var characters = str.toCharArray();
        for (final var ch : characters) {
            if (bracketMap.containsKey(ch)) {
                stack.push(ch);
            } else if (stack.isEmpty() || ch != bracketMap.get(stack.pop())) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    private static boolean isValid(final String str) {
        return !(Objects.isNull(str) || str.isBlank() || str.length() % 2 != 0);
    }

}

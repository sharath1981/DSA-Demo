package com.ryana.custom;

import java.util.Objects;
import java.util.Stack;

public class CheckBalancedParentheses {

    public static void main(final String[] args) {
        final var str = "{[{([{}[]()])}]}";

        System.out.println(isBalanced1(str));
        System.out.println(isBalanced2(str));

    }

    private static boolean isBalanced1(String str) {
        if (!isValid(str)) {
            return false;
        }
        while (str.contains("()") || str.contains("{}") || str.contains("[]")) {
            str = str.replaceAll("\\(\\)", "")
                    .replaceAll("\\{\\}", "")
                    .replaceAll("\\[\\]", "");
        }
        return str.isBlank();
    }

    private static boolean isValid(final String str) {
        return !(Objects.isNull(str) || str.isBlank() || str.length() % 2 != 0);
    }

    private static boolean isBalanced2(final String str) {
        if (!isValid(str)) {
            return false;
        }
        final var stack = new Stack<Character>();
        for (final var ch : str.toCharArray()) {
            if (ch == '{' || ch == '[' || ch == '(') {
                stack.push(ch);
            } else if (!stack.isEmpty()
                    && ((stack.peek() == '{' && ch == '}') || (stack.peek() == '[' && ch == ']')
                            || (stack.peek() == '(' && ch == ')'))) {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

}

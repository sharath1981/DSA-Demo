package com.ryana.custom;

import java.util.Objects;
import java.util.Stack;

public class CheckBalancedParentheses {

    public static void main(final String[] args) {
        final var str = "{[{([{}[]()])}]}";

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
        if (Objects.nonNull(str) && !str.isBlank() && str.length() % 2 == 0) {
            for (final var ch : str.toCharArray()) {
                if (!(ch == '(' || ch == ')' || ch == '{' || ch == '}' || ch == '[' || ch == ']')) {
                    return false;
                }
            }
            return true;
        }
        return false;
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

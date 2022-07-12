package com.ryana.programs;

import java.util.Arrays;
import java.util.Objects;

public class QuickSort {

    public static void main(String[] args) {

        final int[] input = { 1, 11, 2, 10, 3, 9, 8, 4, 5, 7, 6 };
        sort(input);
        System.out.println(Arrays.toString(input));
    }

    private static void sort(int[] input) {
        if (Objects.isNull(input) || input.length < 2) {
            return;
        }
        sort(input, 0, input.length - 1);
    }

    private static void sort(int[] input, int start, int end) {
        if (input.length == 0 || start >= end) {
            return;
        }

        int left = start;
        int right = end;
        int mid = start + (end - start) / 2;

        while (left <= right) {
            while (input[left] < input[mid]) {
                left++;
            }
            while (input[right] > input[mid]) {
                right--;
            }
            if (left <= right) {
                swap(input, left++, right--);
            }
        }
        if (start < right) {
            sort(input, start, right);
        }
        if (left < end) {
            sort(input, left, end);
        }
    }

    private static void swap(int[] input, int i, int j) {
        int temp = input[i];
        input[i] = input[j];
        input[j] = temp;
    }

}

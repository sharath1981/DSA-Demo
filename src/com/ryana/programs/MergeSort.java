package com.ryana.programs;

import java.util.Arrays;
import java.util.Objects;

public class MergeSort {

    public static void main(final String[] args) {
        final int[] original = { 9, 5, 7, 3, 8, 10, 2, 1, 6, 4, 11 };
        sort(original);
        System.out.println(Arrays.toString(original));
    }

    private static void sort(final int[] original) {
        if (Objects.isNull(original) || original.length < 2) {
            return;
        }
        final var left = Arrays.copyOfRange(original, 0, original.length / 2);
        final var right = Arrays.copyOfRange(original, original.length / 2, original.length);
        sort(left);
        sort(right);
        merge(original, left, right);
    }

    private static void merge(final int[] original, final int[] left, final int[] right) {
        int k = 0, i = 0, j = 0;
        while (i < left.length && j < right.length) {
            original[k++] = left[i] <= right[j] ? left[i++] : right[j++];
        }
        while (i < left.length) {
            original[k++] = left[i++];
        }
        while (j < right.length) {
            original[k++] = right[j++];
        }
    }
}

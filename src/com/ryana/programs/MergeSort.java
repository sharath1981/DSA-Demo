package com.ryana.programs;

import java.util.Arrays;
import java.util.Objects;

public class MergeSort {

    public static void main(final String[] args) {
        final int original[] = { 9, 1, 2, 8, 14, 11, 3, 1, 7, 4, 3, 10, 14, 5, 12, 6 };
        sort(original);
        System.out.println(Arrays.toString(original));

    }

    private static void sort(final int[] original) {
        if (Objects.isNull(original) || original.length < 2) {
            return;
        }
        final var left = Arrays.copyOf(original, original.length / 2);
        final var right = Arrays.copyOfRange(original, original.length / 2, original.length);
        sort(left);
        sort(right);
        merge1(original, left, right);
    }

    private static void merge(final int[] original, final int[] left, final int[] right) {
        int i = 0, j = 0, k = 0;
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

    private static void merge1(final int[] original, final int[] left, final int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            original[k++] = left[i] <= right[j] ? left[i++] : right[j++];
        }
        System.arraycopy(left, i, original, k, left.length - i);
        System.arraycopy(right, j, original, k, right.length - j);
    }

    private static void merge2(final int[] original, final int[] left, final int[] right) {
        int i = 0, j = 0, k = 0;
        while (k < original.length) {
            if (i < left.length && j < right.length) {
                original[k++] = left[i] <= right[j] ? left[i++] : right[j++];
            } else if (i < left.length) {
                original[k++] = left[i++];
            } else if (j < right.length) {
                original[k++] = right[j++];
            }
        }
    }
}

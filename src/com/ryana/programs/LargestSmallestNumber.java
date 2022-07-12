package com.ryana.programs;

public class LargestSmallestNumber {

    public static void main(String[] args) {
        final int[] numbers = { 1, 19, 12, 15, 5, 8, 2, 4, 9, 6, 18, 18, 19 };
        System.out.println(findLargest(numbers));
        System.out.println(findSecondLargest(numbers));
    }

    private static int findSecondLargest(int[] numbers) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = largest;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > largest) {
                largest = numbers[i];
            } else if (numbers[i] > secondLargest && numbers[i] < largest) {
                secondLargest = numbers[i];
            }
        }
        return secondLargest;
    }

    private static int findLargest(int[] numbers) {
        int largest = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > largest) {
                largest = numbers[i];
            }
        }
        return largest;
    }

}

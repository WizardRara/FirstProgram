package org.example;

public class AnagramRevers {
    public String reversedString (String inputString) {
        String[] arrayOfInputString = inputString.split(" ");
        StringBuilder result = new StringBuilder();
        for (String stringToReverse: arrayOfInputString) {
            String reversedWord = reverseWord(stringToReverse);
            result.append(reversedWord).append(" ");
        }
        return result.toString();
    }
    private String reverseWord(String stringToReverse) {
        char[] arrayToReverse = stringToReverse.toCharArray();
        int left = 0;
        int right = arrayToReverse.length - 1;
        while (left < right) {
            if (!Character.isLetter(arrayToReverse[right])) {
                right--;
            } else if (!Character.isLetter(arrayToReverse[left])) {
                left++;
            } else {
                swap(arrayToReverse,left,right);
                left++;
                right--;
            }
        }
        return new String(arrayToReverse);
    }
    private void swap(char[] array, int left, int right) {
        char temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }
}

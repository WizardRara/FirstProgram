package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String inputString = inputScanner.nextLine();
        AnagramRevers anagramRevers = new AnagramRevers();
        String reversedString = anagramRevers.reversedString(inputString);
        System.out.println("Ваша перевернутая строка : " + reversedString);
    }
}
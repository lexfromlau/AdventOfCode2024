package org.example;

import org.example.FirstDoor.FirstDoor;
import org.example.SecondDoor.SecondDoor;

public class Main {
    public static void main(String[] args) {
        String filePath = "\\SecondDoor\\data\\data.txt";

        var firstDoor = new FirstDoor(filePath);
        firstDoor.calculateDistanceAndTotalSimilarityScore();

        var secondDoor = new SecondDoor(filePath);

        System.out.println("safe reports: " + secondDoor.analyzeSafeReports());
    }
}
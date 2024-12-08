package org.example;

import org.example.FirstDoor.FirstDoor;

public class Main {
    public static void main(String[] args) {
        String filePath = "input/data/from/file-path/data.txt";

        var firstDoor = new FirstDoor(filePath);
        firstDoor.calculateDistanceAndTotalSimilarityScore();
    }
}
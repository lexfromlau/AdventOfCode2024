package org.example.FirstDoor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record FirstDoor(String filePath) {

    public void calculateDistance(ArrayList<List<Integer>> inputDataSorted) {
        List<Integer> firstDataSorted = inputDataSorted.get(0);
        List<Integer> secondDataSorted = inputDataSorted.get(1);

        int sum = 0;

        for (int i = 0; i < firstDataSorted.size(); i++) {
            sum += Math.abs(firstDataSorted.get(i) - secondDataSorted.get(i));
        }

        System.out.println("The sum is: " + sum);
    }


    public ArrayList<List<Integer>> processInputData(String filePath) {
        List<Integer> array1 = new ArrayList<>();
        List<Integer> array2 = new ArrayList<>();

        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");

                if (parts.length == 2) {
                    array1.add(Integer.parseInt(parts[0]));
                    array2.add(Integer.parseInt(parts[1]));
                }
            }
        } catch (
                IOException ioexc) {
            ioexc.printStackTrace();
        }

        List<Integer> firstSortedList = new ArrayList<>(array1);
        firstSortedList.sort(null);
        List<Integer> secondSortedList = new ArrayList<>(array2);
        secondSortedList.sort(null);

        ArrayList<List<Integer>> list = new ArrayList<>();
        list.add(firstSortedList);
        list.add(secondSortedList);

        return list;
    }

    public void calculateSimilarityScore(ArrayList<List<Integer>> inputDataSorted) {
        var firstListSorted = inputDataSorted.get(0);
        var secondListSorted = inputDataSorted.get(1);

        int amountOfDuplicate = 0;
        int score = 0;

        for (int i = 0; i < firstListSorted.size(); i++) {
            for (int j = 0; j < firstListSorted.size(); j++) {
                if (Objects.equals(firstListSorted.get(i), secondListSorted.get(j))) {
                    amountOfDuplicate++;
                }
                if (amountOfDuplicate > 0) {
                    score += firstListSorted.get(i) * amountOfDuplicate;
                    amountOfDuplicate = 0;
                }
            }
        }
        System.out.println("The similarity score is: " + score);
    }

    public void calculateDistanceAndTotalSimilarityScore() {
        var data = processInputData(filePath);
        calculateDistance(data);
        calculateSimilarityScore(data);
    }
}
package org.example.SecondDoor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public record SecondDoor(String filePath) {

    public ArrayList<List<Integer>> processInputData() {
        ArrayList<List<Integer>> numbers = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.trim().split("\\s");
                List<Integer> numbersParsed = new ArrayList<>();

                for (String part : parts) {
                    numbersParsed.add(Integer.parseInt(part));
                }
                numbers.add(numbersParsed);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return numbers;
    }

    public int analyzeSafeReports() {
        int safeReportsCount = 0;
        var reports = processInputData();

        for (List<Integer> report : reports) {
            if (isSafe(report) || canBeMadeSafe(report)) {
                safeReportsCount++;
            }
        }

        return safeReportsCount;
    }

    private boolean isSafe(List<Integer> report) {
        if (report.size() < 2) {
            return true;
        }

        boolean increasing = report.get(1) > report.get(0);
        boolean isValid = true;

        for (int i = 0; i < report.size() - 1; i++) {
            int current = report.get(i);
            int next = report.get(i + 1);
            int diff = next - current;

            if (Math.abs(diff) < 1 || Math.abs(diff) > 3) {
                isValid = false;
                break;
            }

            if ((increasing && next < current) || (!increasing && next > current)) {
                isValid = false;
                break;
            }
        }

        return isValid;
    }

    private boolean canBeMadeSafe(List<Integer> report) {
        for (int i = 0; i < report.size(); i++) {
            List<Integer> modifiedReport = new ArrayList<>(report);
            modifiedReport.remove(i);
            if (isSafe(modifiedReport)) {
                return true;
            }
        }
        return false;
    }
}

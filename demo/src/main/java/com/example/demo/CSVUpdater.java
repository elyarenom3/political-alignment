package com.example.demo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSVUpdater {

    public static void updateCSVWithNewData(String filePath, Map<String, Float> newBillData, String newColumnName) {
        // LinkedHashMap to preserve the insertion order
        Map<String, List<String>> dataWithNewColumn = new LinkedHashMap<>();
        List<String> headers = new ArrayList<>();

        // Read the existing CSV file
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            headers.addAll(records.iterator().next().toMap().keySet()); // Copy existing headers
            headers.add(newColumnName); // Add the new column header

            // Read each record and add the new column data
            for (CSVRecord record : records) {
                List<String> rowValues = new ArrayList<>(record.size() + 1);
                record.forEach(rowValues::add); // Add existing row values
                String key = record.get(0); // Assuming the first column contains a unique identifier
                // Add the new column value
                Float newValue = newBillData.getOrDefault(key, null);
                rowValues.add(newValue != null ? newValue.toString() : "N/A"); // Use "N/A" for missing data
                dataWithNewColumn.put(key, rowValues);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Write the data with the new column back to the CSV file
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headers.toArray(new String[0])))
        ) {
            for (List<String> rowValues : dataWithNewColumn.values()) {
                csvPrinter.printRecord(rowValues);
            }
            csvPrinter.flush();
            System.out.println("CSV file updated with new column.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String filePath = "bills_and_summaries.csv"; // Path to your CSV file
        Map<String, Float> newBillData = new HashMap<>();
        newBillData.put("Bill A", 123.45f); // Example data
        newBillData.put("Bill B", 67.89f); // Assuming these bills exist in your CSV

        updateCSVWithNewData(filePath, newBillData, "New Column Name");
    }
}
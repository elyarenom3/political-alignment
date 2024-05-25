package com.example.demo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVFileMaker {


    public static void writeBillsToCSV(Map<String, String> bills, String filePath) {
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath+"DataSet.csv"));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("Bill Name", "Summary"))
        ) {
            for (Map.Entry<String, String> entry : bills.entrySet()) {
                csvPrinter.printRecord(entry.getKey(), XMLParser.parseXmlFile(filePath+entry.getKey()+".xml"));
            }
            csvPrinter.flush(); // Flush the printer to ensure all data is written to the file
            System.out.println("CSV file was created successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the CSV file.");
            e.printStackTrace();
        }
    }
}
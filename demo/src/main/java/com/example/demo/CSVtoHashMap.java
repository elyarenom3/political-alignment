package com.example.demo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class CSVtoHashMap {
    public static void main(String[] args){
        Map<String, String> csvData = new HashMap<>();
        csvData = getVotes("/Users/alexmiller/bill.csv", "Bill Number", "Vote");
        csvData.forEach((key, value) -> System.out.println(key + ": " + value));
    }


    public static HashMap<String, String> getVotes(String nandp, String col1, String col2 ) {
        String filePath = nandp;
        Map<String, String> csvData = new HashMap<>();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                // Assuming column names are 'Key' and 'Value'
                if(!csvRecord.get(col1).equals(null)){
                    String key = csvRecord.get(col1);
                    String value = csvRecord.get(col2);
                    csvData.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Example to print the map
        return (HashMap<String, String>) csvData;
    }
}


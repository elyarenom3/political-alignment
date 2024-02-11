import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVUpdater {

    public static void updateCSVWithNewData(String filePath, Map<String, Float> newBillData, String newColumnName) {
        // Temporary storage for the merged data
        Map<String, List<String>> mergedData = new HashMap<>();

        // Read existing CSV and merge with new data
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .parse(reader);
            for (CSVRecord record : records) {
                String billName = record.get("Bill Name");
                String summary = record.get("Summary");
                Float newValue = newBillData.getOrDefault(billName, null);
                mergedData.put(billName, List.of(summary, newValue != null ? newValue.toString() : ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Write the merged data back to the CSV, including the new column
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Bill Name", "Summary", newColumnName))
        ) {
            for (Map.Entry<String, List<String>> entry : mergedData.entrySet()) {
                // Print each record with the new column value
                csvPrinter.printRecord(entry.getKey(), entry.getValue().get(0), entry.getValue().get(1));
            }
            csvPrinter.flush();
            System.out.println("CSV file updated successfully.");
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
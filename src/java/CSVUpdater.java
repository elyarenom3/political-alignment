import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVUpdater {

    public static void updateCSVWithNewData(String filePath, Map<String, Float> newBillData, String newColumnName) {
        // Temporary storage for the merged data
        Map<String, List<String>> mergedData = new HashMap<>();
        List<String> headers = new ArrayList<>();

        // Read existing CSV and merge with new data
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            headers.addAll(format.getHeaderNames()); // Get existing headers
            if (!headers.contains(newColumnName)) {
                headers.add(newColumnName); // Add new column name if not already present
            }

            for (CSVRecord record : format) {
                String billName = record.get("Bill Name");
                List<String> rowValues = new ArrayList<>();
                for (String header : format.getHeaderNames()) {
                    rowValues.add(record.get(header));
                }
                // Add new value or empty string if not present
                Float newValue = newBillData.getOrDefault(billName, null);
                if (headers.indexOf(newColumnName) >= rowValues.size()) {
                    rowValues.add(newValue != null ? newValue.toString() : "");
                } else {
                    rowValues.set(headers.indexOf(newColumnName), newValue != null ? newValue.toString() : "");
                }
                mergedData.put(billName, rowValues);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Write the merged data back to the CSV, including the new column
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headers.toArray(new String[0])))
        ) {
            for (Map.Entry<String, List<String>> entry : mergedData.entrySet()) {
                csvPrinter.printRecord(entry.getValue());
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

        //updateCSVWithNewData(filePath, newBillData, "New Column Name");
    }
}
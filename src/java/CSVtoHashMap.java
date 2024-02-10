
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
        csvData = getBill("/Users/alexmiller/MemberVotes-2.csv");
        csvData.forEach((key, value) -> System.out.println(key + ": " + value));

    }


    public static HashMap<String, String> getVotes(String nandp) {
        String filePath = nandp;
        Map<String, String> csvData = new HashMap<>();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                    // Assuming column names are 'Key' and 'Value'
                    String key = csvRecord.get("Vote Number");
                    String value = csvRecord.get("Vote");
                    csvData.put(key, value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Example to print the map
            return (HashMap<String, String>) csvData;
        }

    public static HashMap<String, String> getBill(String nandp) {
        String filePath = nandp;
        Map<String, String> csvData = new HashMap<>();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
            for (CSVRecord csvRecord : csvParser) {
                // Assuming column names are 'Key' and 'Value'
                String key = csvRecord.get("Vote Number");
                String value = csvRecord.get("Vote Subject");
                csvData.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Example to print the map
        return (HashMap<String, String>) csvData;
    }
}


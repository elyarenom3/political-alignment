
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
public class Main {
    public static void main(String[] args){
        String filelocation = "/Users/alexmiller/HackStuff/";
        String csvlocation = WebScraper.alltogether("H2X2G1", filelocation);
        Map<String, String> csvData = CSVtoHashMap.getVotes(csvlocation);
        for(String k: csvData.keySet()){
            XmlDownloader.download(k, filelocation);
        }
        for
    }
}

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
public class Main {
    public static void main(String[] args){
        Algorythm test =new Algorythm("H2X2G1","/Users/alexmiller/HackStuff/","DataSet copy.csv");
        test.addDemo("Income", 2);
        test.addDemo("Groups", 1);
        test.addDemo("Gender", 0);
        test.addImportance("Immigration", 5);
        test.addImportance("Finances", 4);
        test.addImportance("Housisng", 3);
        test.addImportance("LGBT", 4);
        test.addImportance("First Nations Rights", 4);
        for(int k: test.Importance){
            System.out.println(k);
        }
        for(int k: test.Effects){
            System.out.println(k);
        }
        System.out.println(test.TOTALSCORE());

    }
}
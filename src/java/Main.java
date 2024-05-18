
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.Reader;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
public class Main {

    public static void main(String[] args){
        String postal ="H2X 2G1";
        WebScraper.scrapeMemberInfo(WebScraper.accessWebsite(WebScraper.buildSearchUrl(postal)));
        System.out.println(WebScraper.accessWebsite(WebScraper.buildSearchUrl(postal)));
        Algorythm test =new Algorythm(postal,"/Users/alexmiller/HackStuff/","DataSet copy.csv");
        HashMap<String, String> votes = (HashMap<String, String>) test.getVotes();
        /*for(String s: votes.keySet()){
            System.out.println(s + votes.get(s));
        }*/
        test.addDemo("Income", 1);
        test.addDemo("Groups", 1);
        test.addDemo("Gender", 1);
        test.addImportance("Immigration", 3);
        test.addImportance("Finances", 2);
        test.addImportance("Housing", 4);
        test.addImportance("LGBT", 4);
        test.addImportance("First Nations Rights", 4);
       /* for(int k: test.Importance){
            System.out.println(k);
        }
        for(int k: test.Effects){
            System.out.println(k);
        }*/

        System.out.println(test.Issuemath(0));
        System.out.println(test.TOTALSCORE());


    }

}
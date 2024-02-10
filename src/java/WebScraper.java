import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebScraper {
    public static void main(String[] args) {
        try {
            // The URL to scrape
            String url = "https://www.ourcommons.ca/Members/en/constituencies/north-vancouver(918)/votes";

            // Fetch the document from the URL
            Document doc = Jsoup.connect("https://www.ourcommons.ca/Members/en/jonathan-wilkinson(89300)/votes").get();

            // Parse the document to find the required information
            String constituencyName = doc.select("h1").first().text();
            String votesFile = doc.select("a.download-csv-link").first().attr("href");
            //String partyAffiliation = doc.select("p.affiliation").first().text();

            // Print the extracted information
            System.out.println("Constituency Name: " + constituencyName);
            System.out.println("Vote CSV " + votesFile);
            //System.out.println("Party Affiliation: " + partyAffiliation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
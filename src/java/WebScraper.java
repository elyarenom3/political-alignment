import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class WebScraper {
    /*public static void main(String[] args) {
        try {
            System.out.print(csvDownloader(
                            accessWebsite(
                                    buildSearchUrl("H2X2G1")
                            )
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public static String alltogether (String pCode, String place) {
        try {
            return csvDownloader(accessWebsite(buildSearchUrl(pCode)), place);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }

    public static String buildSearchUrl(String input) {
        if (input.length() < 6) {
            throw new IllegalArgumentException("Input string must be at least 6 characters long.");
        }

        String baseUrl = "https://www.ourcommons.ca/Members/en/search?parliament=all&searchText=";
        String firstThreeLetters = input.substring(0, 3);
        String lastThreeLetters = input.substring(input.length() - 3);

        return baseUrl + firstThreeLetters + "%20" + lastThreeLetters;
    }
    public static String accessWebsite (String urlInput){
        try {
            Document doc = Jsoup.connect(urlInput).get();

            // Assume the link to the parliamentarian member can be uniquely identified.
            // You need to replace ".member-link" with the actual CSS selector that matches the link in the search results.
            Element memberLink = doc.select("a.ce-mip-mp-tile").first();

            if (memberLink != null) {
                String memberUrl = memberLink.absUrl("href");
                return memberUrl;
            } else {
                return "Postal Code Not Found: " + urlInput;
            }
        } catch (Exception e) {
            return "Error Somewhere else";
        }
    }

    public static String csvDownloader (String csvUrlI, String file) {
        String csvUrl = csvUrlI + "/votes/csv";
        String saveFilePath = file + "bill.csv";
        try (InputStream in = new URL(csvUrl).openStream();
             BufferedInputStream bis = new BufferedInputStream(in);
             FileOutputStream fos = new FileOutputStream(saveFilePath)) {

            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(dataBuffer, 0, 1024)) != -1) {
                fos.write(dataBuffer, 0, bytesRead);
            }
            return saveFilePath;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error Somewhere";
        }
    }

    public static void scrapeMemberInfo(String url) {
        try {
            // Fetch the document over HTTP
            Document doc = Jsoup.connect(url).get();

            // Select the elements containing the name, party affiliation, and image URL
            String name = doc.select("element-selector-for-name").first().text();
            String partyAffiliation = doc.select("element-selector-for-party-affiliation").first().text();
            String imageUrl = doc.select("element-selector-for-image").first().attr("src");

            // Output the data
            System.out.println("Name: " + name);
            System.out.println("Party Affiliation: " + partyAffiliation);
            System.out.println("Image URL: " + imageUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
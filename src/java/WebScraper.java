import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class WebScraper {
    public static void main(String[] args) {
        try {
            System.out.print(performWebSearch("H2X2G1"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String performWebSearch(String searchQuery) {
        String responseContent = "";
        try {
            String baseUrl = "https://www.ourcommons.ca/Members/en/search"; // Update with the actual search URL
            String encodedQuery = URLEncoder.encode(searchQuery, "UTF-8");
            String fullUrl = baseUrl + "?query=" + encodedQuery; // Update parameter name if necessary

            URL url = new URL(fullUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            responseContent = response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            responseContent = "Error performing web search";
        }
        return responseContent;
    }
}
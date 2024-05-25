package com.example.demo;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class XmlDownloader {

    public static void downloadXmlFile(String fileUrl, String savePath) {
        try {
            // Create URL object
            URL url = new URL(fileUrl);
            // Open stream to the URL
            try (InputStream in = url.openStream()) {
                // Copy the content from the URL stream to a file
                Files.copy(in, Paths.get(savePath), StandardCopyOption.REPLACE_EXISTING);
                //System.out.println("Download completed successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("Error downloading the file.");
        }
    }

    public static String urlmaker(String bill){
        String billUrl = "https://www.parl.ca/Content/Bills/441/Private/"
                + bill
                +"/"
                +bill
                +"_1"
                +"/"
                +bill
                +"_E.xml";
        return billUrl;
    }

    public static void main(String[] args) {
        String fileUrl = "C-19"; // URL to the XML file
        String savePath = "/Users/alexmiller/aaa1.xml"; // Path to save the downloaded file
        String fileUrl2 = "S-235";

        download(fileUrl, savePath);
        download(fileUrl2, savePath);
    }
    public static void download(String bill, String file) {
        for(int i=9; i>0; i--){
            String fileUrl = "https://www.parl.ca/Content/Bills/441/private/"
                    +bill
                    +"/"
                    +bill
                    +"_"
                    +Integer.toString(i)
                    +"/"
                    +bill
                    +"_E.xml"; // URL to the XML file
            String fileUrl2 = "https://www.parl.ca/Content/Bills/441/Government/"
                    +bill
                    +"/"
                    +bill
                    +"_"
                    +Integer.toString(i)
                    +"/"
                    +bill
                    +"_E.xml";
            String savePath = file + bill +".xml"; // Path to save the downloaded file
            if(urlExists(fileUrl)){
                downloadXmlFile(fileUrl, savePath);
                break;
            } else if (urlExists(fileUrl2)) {
                downloadXmlFile(fileUrl2, savePath);
            } else{
                //System.out.println("Error checking URL: " + fileUrl2);
            }
        }
    }

    public static boolean urlExists(String urlString) {
        try {
            // Create a URL object from the string
            URL url = new URL(urlString);

            // Open a connection to the URL
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            // Set the request method to HEAD
            httpURLConnection.setRequestMethod("HEAD");

            // Set a user agent to avoid receiving a HTTP 403 error from some websites
            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0");

            // Get the response code
            int responseCode = httpURLConnection.getResponseCode();

            // Check if the response code indicates success (HTTP 200 OK)
            return (responseCode >= 200 && responseCode < 400);
        } catch (Exception e) {
            System.out.println("Error checking URL: " + urlString);
            return false;
        }
    }
}

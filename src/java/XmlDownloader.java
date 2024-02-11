import java.io.InputStream;
import java.net.URL;
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
                System.out.println("Download completed successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error downloading the file.");
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
        String fileUrl = urlmaker("S-235"); // URL to the XML file
        String savePath = "/Users/alexmiller/example1.xml"; // Path to save the downloaded file

        downloadXmlFile(fileUrl, savePath);
    }
    public static void download(String bill, String file) {
        String fileUrl = urlmaker(bill); // URL to the XML file
        String savePath = file + bill +".xml"; // Path to save the downloaded file

        downloadXmlFile(fileUrl, savePath);
    }
}

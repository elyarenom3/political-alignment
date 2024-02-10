import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class CSVDownloader {
    public static void main(String[] args) {
        String csvUrl = "https://www.ourcommons.ca/Members/en/jonathan-wilkinson(89300)/votes/csv"; // Replace with the actual CSV URL
        String saveFilePath = "path/to/save/bills.csv"; // Replace with your desired path

        try (InputStream in = new URL(csvUrl).openStream();
             BufferedInputStream bis = new BufferedInputStream(in);
             FileOutputStream fos = new FileOutputStream(saveFilePath)) {

            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(dataBuffer, 0, 1024)) != -1) {
                fos.write(dataBuffer, 0, bytesRead);
            }
            System.out.println("CSV file has been downloaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
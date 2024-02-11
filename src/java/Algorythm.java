import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algorythm {
    public String filelocation;
    public String Postal;
    public int[] Demos ={0,0,0};
    public int[] Effects;
    public int[] Importance={0,0,0,0,0,0};

    public Map<String, String> votes = new HashMap<>();
    public String votecsvlocation;

    public Algorythm (String postal){
        this.Postal = postal;
        this.votecsvlocation = WebScraper.alltogether(postal, filelocation);
        this.votes = CSVtoHashMap.getVotes(votecsvlocation, "Bill Number", "Vote");
    }

    public void addDemo (String type, int score){
        switch(type){
            case "Gender":
                Demos[0]=score;
                break;
            case "Income":
                Demos[1]=score;
                break;
            case "Gorups":
                Demos[2]=score;
                break;
        }
    }

    public void addImportance (String type, int score){
        switch(type){
            case "LGBT":
                Importance[0]=score;
                break;
            case "First Nations":
                Importance[1]=score;
                break;
            case "Imigration":
                Importance[2]=score;
                break;
            case "Finances":
                Importance[3]=score;
                break;
            case "Housing":
                Importance[4]=score;
                break;
            case "HealthCare":
                Importance[5]=score;
                break;
            case "DataPrivacy":
                Importance[6]=score;
        }
    }

    public int total(int[] list){
        int total= 0;
        for(int i: list){
            total=total+i;
        }
        return total;
    }
    public float Issuemath (int p) {
        if (Importance[p] ==0){return 0;}
        float imp = (Importance[p]) / total(Importance);
        float effect = (Effects[p]) / total(Effects);
        String s = "";
        switch (p) {
            case (0):
                s = "LGBT";
            case (1):
                s = "First Nations";
            case (2):
                s = "Imigration";
            case (3):
                s = "Finances";
            case (4):
                s = "Housing";
            case (5):
                s = "Healthcare";
            case (6):
                s = "Data Privacy";
        }
        Map<String, String> bill_data = CSVtoHashMap.getVotes(filelocation, "Bill Name", s);
        Map<String, Float> floats = new HashMap<>();
        for (String r : bill_data.keySet()) {
            if (Float.valueOf(String.valueOf(bill_data.get(r))) != 0.0) {
                floats.put(r, Float.valueOf(String.valueOf(bill_data.get(r))));
            }
        }
        float sum=0;
        float n=0;
        float score =0;
        for (String r : floats.keySet()) {
            if (votes.get(r) != null) {
                switch (votes.get(r)) {
                    case("Yea"): sum =sum+floats.get(r);
                    n++;
                    case("Nay"): sum=sum-floats.get(r);
                    n++;

                }
            }



        }

        if(n!=0){
            score = sum/n;
        }
        return score * imp * effect;

    }

    public float TOTALSCORE (){
        float num = 0;
        float dem =0;
        float totale = total(Effects);
        float totali=total(Importance);
        for(int i=0; i<7; i++){
            num=num+Issuemath(i);
        }
        for(int i=0; i<7; i++){
            dem=dem+Importance[i]/totali*Effects[i]*totale;
        }
        return 100*num/dem;
    }










}

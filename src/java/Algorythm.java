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

    public String fileName;
    public String Postal;
    public int[] Demos ={0,0,0};
    public int[] Effects={2,2,2,2,2,2,2};
    public int[] Importance={0,0,0,0,0,0,0};

    public Map<String, String> votes = new HashMap<>();
    public String votecsvlocation;

    public Algorythm (String postal, String filelocation, String filename){
        this.fileName=filename;
        this.filelocation=filelocation;
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
                switch(score){
                    case(1):
                    Effects[4] =4;
                    Effects[5]=4;
                    break;
                    case(2):
                        Effects[4] =3;
                        Effects[5]=3;
                        break;
                    case(3):
                        Effects[4] =2;
                        Effects[5]=2;
                        break;
                    case(4):
                        Effects[4] =1;
                        Effects[5]=1;
                        break;
                }
                break;
            case "Gorups":
                switch(score){
                    case(1): Effects[0]=4;
                    break;
                    case(2): Effects[1]=4;
                    break;
                    case(3): Effects[1]=4;
                    Effects[0]=4;
                    break;
        }
    }}

    public void addImportance (String type, int score){
        switch(type){
            case "LGBT":
                Importance[0]=score;
                break;
            case "First Nations Rights":
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
        if (Importance[p] == 0){return 0;}
        float it = total(Importance);
        float ik = Importance[p];
        float et = total(Effects);
        float ek = Effects[p];
        float imp = it/ik;
        float effect = et/ek;
        String s = "";
        switch (p) {
            case (0):
                s = "LGBT";
                break;
            case (1):
                s = "First Nations Rights";
                break;
            case (2):
                s = "Imigration";
                break;
            case (3):
                s = "Finances";
                break;
            case (4):
                s = "Housing";
                break;
            case (5):
                s = "Healthcare";
                break;
            case (6):
                s = "Data Privacy";
                break;
        }
        System.out.println(s+"impscore: "+imp);
        System.out.println(s+"effectscore: "+imp);
        Map<String, String> bill_data;
        try {
            bill_data = CSVtoHashMap.getVotes(filelocation + fileName, "Bill Name", s);
        } catch (Exception e) { // Catch the specific exception if known, e.g., ColumnNotFoundException
            // If the column doesn't exist, just skip this category
            return 1*imp*effect;
        }
        Map<String, Float> floats = new HashMap<>();
        for (String r : bill_data.keySet()) {
            if (Float.valueOf(String.valueOf(bill_data.get(r))) != 0.0) {
                floats.put(r, Float.valueOf(String.valueOf(bill_data.get(r))));
            }
        }
        float sum=0;
        float n=0;
        float score = 0;
        for (String r : floats.keySet()) {
            if (votes.get(r) != null) {
                switch (votes.get(r)) {
                    case ("Yea"):
                        sum = sum + floats.get(r);
                        n++;
                    case ("Nay"):
                        sum = sum - floats.get(r);
                        n++;

                }
            }


        }

        if(n!=0){
            score = sum/n;
            return score * imp * effect;
        }else{
            return 1*imp*effect;
        }


    }

    public float TOTALSCORE (){
        float num = 0;
        float dem =0;
        float totale = total(Effects);
        float totali=total(Importance);
        for(int i=0; i<7; i++){
            num=num+Issuemath(i);
        }
        return num;
    }










}

import java.util.HashMap;

public class Catagorizer {

    public static void main(String[] args) {
        String file = "/Users/alexmiller/HackStuff/DataSet copy.csv";
        envir(file);
        lgbt(file);
        Economy(file);
        Housing(file);
        FN(file);
        HealthCare(file);

    }

    public static void Issue(String filepath, String[] Buzzwords,
                             String[] Promodifiers,
                             String[] AntiModifiers,
                             String IssueName) {
        HashMap<String, String> Summeries = CSVtoHashMap.getVotes(filepath, "Bill Name", "Summary");
        HashMap<String, Float> issueScore = new HashMap<String, Float>();
        String keys[] = Summeries.keySet().toArray(new String[0]);

        for (int i = 0; i < Summeries.size(); i++) {

            float pro =0;
            float anti=0;
            String lowerSummary;
            lowerSummary = Summeries.get(keys[i]).toLowerCase();
            String noPunctuation = lowerSummary.replaceAll("[,.]", "");
            String[] words = lowerSummary.split("");
            int n = 0;
            for (String w : Buzzwords) {
                if (lowerSummary.contains(w)) {
                    for (String p : Promodifiers) {
                        for(String example: words){
                            if(p.equals(example)){pro++;}
                        }
                    }
                    for (String a : AntiModifiers) {
                        for(String example: words){
                            if(a.equals(example)){anti++;}
                        }
                    }
                    if(pro>anti){
                        issueScore.put(keys[i], (float) (pro)/(pro+anti));
                    }else if(anti>pro){
                        issueScore.put(keys[i], (float) -(anti)/(pro+anti));
                    }else{
                        issueScore.put(keys[i], (float) 0);
                    }

                    break;
                }
            }
            if(issueScore.get(keys[i])==null){
                issueScore.put(keys[i], (float) 0);
            }
        }
        CSVUpdater.updateCSVWithNewData(filepath, issueScore, IssueName);

    }

    public static void envir(String filepath) {
        String[] Buzzwords = {"sustainability", "renewable energy", "carbon footprint", "climate change", " oil", "carbon", "greenhouse gas",
        "environment" };
        String[] Pro= {"sustainabile", "green", "renewable", "wind","solar","footprint", "protect", "environment","carbon"};
        String [] Anti ={"deregulate", "deregulation", "production", "fossil", "prices" };
        String Name = "Environment";
        Issue(filepath, Buzzwords, Pro, Anti, Name);
    }

    public static void Housing(String filepath) {
        String[] Buzzwords = {"house", "housing", "cost of living", "climate change", "home", "carbon", "greenhouse gas",
                "environment", "homeowner" };
        String[] Pro= {"sustainabil", "price", "protect", "homeowner","canadian", "protect", "foreign",};
        String [] Anti ={"buisness", "profit", "deregulate", "investment" };
        String Name = "Housing";
        Issue(filepath, Buzzwords, Pro, Anti, Name);
    }

    public static void HealthCare(String filepath) {
        String[] Buzzwords = {"Health Care", "Doctor", "Hospital","Care","Medicine","Perscription" };
        String[] Pro= {"wait", "price", "candian", "homeowner","canadian","free", "protect","tax","drug"};
        String [] Anti ={"buisness", "profit", "deregulate" };
        String Name = "Health Care";
        Issue(filepath, Buzzwords, Pro, Anti, Name);
    }

    public static void FN(String filepath) {
        String[] Buzzwords = {"first nations", "native americans", "tribe","american indian","reservation","first peoples" };
        String[] Pro= {"pipeline", "rights", "healthcare", "help","acknowlegement","aid"};
        String [] Anti ={"buisness", "profit", "deregulate" };
        String Name = "First Nations Rights";
        Issue(filepath, Buzzwords, Pro, Anti, Name);
    }

    public static void Economy(String filepath) {
        String[] Buzzwords = {"inflation", "cost of living", "minimum wage","economy","tax","first peoples" };
        String[] Pro= {"pipeline", "rights", "healthcare", "help","acknowlegement","aid"};
        String [] Anti ={"buisness", "profit", "deregulate" };
        String Name = "First Nations Rights";
        Issue(filepath, Buzzwords, Pro, Anti, Name);
    }
    public static void lgbt(String filepath) {
        String[] Buzzwords = {"sexual orientation", "gender", "lgbt", "twin spirit"};
        String[] Pro= {"rights", };
        String [] Anti ={"religion", "christian", };
        String Name = "LGBTQ";
        Issue(filepath, Buzzwords, Pro, Anti, Name);
    }



}
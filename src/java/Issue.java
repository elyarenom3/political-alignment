public abstract class Issue {

     int Importance;
     int Effect;
     int Score;

    public int getEffect() {
        return Effect;
    }

    public void setEffect(int effect) {
        Effect = effect;
    }

    public int getImportance() {
        return Importance;
    }

    public void setImportance(int importance) {
        Importance = importance;
    }

    public int getScore(){
        return Score;
    }
    public void setScore(int score){
        Score = score;
    }

    public abstract int computeScoreBool (boolean input);

    public abstract int  computeScore (int ... inputs);


}


package pongGame;

public class DifficultySettings {
    private final Difficulty[] difficulties = new Difficulty[3];

    public DifficultySettings() {
        Difficulty easy = new Difficulty("Easy", 7);
        Difficulty medium = new Difficulty("Medium", 5);
        Difficulty hard = new Difficulty("Hard", 3);
        difficulties[0] = easy;
        difficulties[1] = medium;
        difficulties[2] = hard;
    }

    public int getDifficultySettings(String dif){
        for(Difficulty difficulty: difficulties){
            if(difficulty.getDifficultyName().equalsIgnoreCase(dif)) {
                return difficulty.getSleepTime();
            }
        }
        return difficulties[0].getSleepTime();
    }
}

class Difficulty {
    private String difficulty;
    private int threadSleepTime;

    Difficulty(String dif, int threadSleepTime) {
        this.difficulty = dif;
        this.threadSleepTime = threadSleepTime;
    }

    public String getDifficultyName(){
        return this.difficulty;
    }

    public int getSleepTime() {
        return this.threadSleepTime;
    }
}
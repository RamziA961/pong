package pongGame;

import javax.sound.sampled.*;
import java.io.*;

public class SoundEffects {
    private static Clip audioCollision;
    private static Clip audioScore;
    private static Clip themeSong;

    public static void init() {
        try {
            File fileCollision = new File("./SoundLib/padOneCollision.wav");
            AudioInputStream audionIn = AudioSystem.getAudioInputStream(fileCollision);
            audioCollision = AudioSystem.getClip();
            audioCollision.open(audionIn);

            File fileScore = new File("./SoundLib/score.wav");
            AudioInputStream audioIn2 = AudioSystem.getAudioInputStream(fileScore);
            audioScore = AudioSystem.getClip();
            audioScore.open(audioIn2);

            File fileTheme = new File("./SoundLib/totallyOriginalThemeSong.wav");
            AudioInputStream audionIn3 = AudioSystem.getAudioInputStream(fileTheme);
            themeSong = AudioSystem.getClip();
            themeSong.open(audionIn3);
            FloatControl floatControl = (FloatControl) themeSong.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(-20.0f);

        } catch (Exception exception) {
           exception.printStackTrace();
           System.err.println(exception.getMessage());
        }
    }

    public static void playCollision() {
        if(audioCollision.isRunning()) {
            audioCollision.stop();
        }
        audioCollision.setFramePosition(0);
        audioCollision.start();
    }

    public static void playThemeSong() {
        if(themeSong.isRunning()) {
            themeSong.stop();
        }
        themeSong.setFramePosition(0);
        themeSong.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void playScore() {
        if(audioScore.isRunning()) {
            audioScore.stop();
        }
        audioScore.setFramePosition(0);
        audioScore.start();
    }

    public static void close(){
        audioCollision.close();
        audioScore.close();
        themeSong.close();
    }
}

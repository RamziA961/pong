package pongGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class PongGame extends JFrame {
    private static final int width = 800;
    private static final int height = 800;
    private static int difficulty;

    private Image image;
    private Graphics gameGraphics;

    private static Ball pongBall;

    PongGame(){
        this.setTitle("Ramzi's Pong");
        this.setSize(width, height);
        this.setResizable(false);
        this.setVisible(true);
        this.setBackground(Color.BLACK);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.addKeyListener(new KeyboardListener());
        new Difficulty("Easy", 7);

        this.startGame();
    }

    public static void main(String[] args){
        SoundEffects.init();
        setDifficulty(1);
        new PongGame();
        // ButtonListener buttonListener = new ButtonListener();
        // buttonListener.promptUser();
    }

    public void startGame() {
        pongBall = new Ball(width/2, height/2);
        Thread ballThread = new Thread(pongBall);
        ballThread.start();

        Thread pad1 = new Thread(pongBall.getPad1());
        Thread pad2 = new Thread(pongBall.getPad2());

        pad1.start();
        pad2.start();
        SoundEffects.playThemeSong();
    }

    @Override
    public void paint(Graphics graphics){
        image = createImage(getWindowWidth(), getWindowHeight());
        gameGraphics = image.getGraphics();
        draw(gameGraphics);
        graphics.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics graphics){
//        if (difficulty != 0) {
            pongBall.draw(graphics);
            pongBall.getPad1().draw(graphics);
            pongBall.getPad2().draw(graphics);

            graphics.setFont(new Font("Consolas", Font.PLAIN, 12));
            graphics.setColor(Color.green);
            graphics.drawString("Score: " + pongBall.getPadScore(pongBall.getPad1().getPadID()), 10, 42);
            graphics.drawString("Score: " + pongBall.getPadScore(pongBall.getPad2().getPadID()),width - 70, 42);
            repaint();
//        }
    }

    public static int getWindowHeight(){
        return height;
    }

    public static int getWindowWidth(){
        return width;
    }

    public static Ball getPongBall() {
        return pongBall;
    }

    public static void setDifficulty(int dif) {
        difficulty = dif;
    }

    public static int getDifficulty() {
        return difficulty;
    }
}

class KeyboardListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent event){
        PongGame.getPongBall().getPad1().keyPressed(event);
        PongGame.getPongBall().getPad2().keyPressed(event);
    }

    @Override
    public void keyReleased(KeyEvent event){
        PongGame.getPongBall().getPad1().keyReleased(event);
        PongGame.getPongBall().getPad2().keyReleased(event);
    }
}

// class ButtonListener implements ActionListener {
//     JDialog jDialog;

//     public void promptUser() {
//         JPanel panel = new JPanel();
//         panel.setLayout(new BorderLayout());
//         panel.add(new JLabel("Select a difficulty mode."), BorderLayout.PAGE_START);

//         JButton easy = new JButton("Easy");
//         easy.addActionListener(this);
//         panel.add(easy, BorderLayout.WEST);

//         JButton medium = new JButton("Medium");
//         medium.addActionListener(this);
//         panel.add(medium, BorderLayout.CENTER);

//         JButton hard = new JButton("Hard");
//         hard.addActionListener(this);
//         panel.add(hard, BorderLayout.EAST);

//         jDialog = new JDialog();
//         jDialog.setTitle("Difficulty Mode");
//         jDialog.setSize(250, 100);
//         jDialog.add(panel);
//         jDialog.setVisible(true);
//         jDialog.setLocationRelativeTo(null);
//         jDialog.setModal(true);
//         jDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//     }

    // @Override
    // public void actionPerformed(ActionEvent event) {
    //     DifficultySettings difficultySettings = new DifficultySettings();
    //     String difficulty = event.getActionCommand();
    //     PongGame.setDifficulty(difficultySettings.getDifficultySettings(difficulty));
    //     jDialog.dispose();
    //     
    // }
// }

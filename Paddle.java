package pongGame;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle implements Runnable {

    private int x, y, yDir, padID;
    private Rectangle paddle;
    private static int padW = 10;
    private static int padH = 50;

    Paddle(int x, int y, int padID){
        this.x = x;
        this.y = y;
        this.padID = padID;
        paddle = new Rectangle(x, y, padW, padH);
    }

    public void keyPressed(KeyEvent event){
        if(padID == 1) {
            if (event.getKeyCode() == KeyEvent.VK_W) {
                yDir = -1;
            } else if (event.getKeyCode() == KeyEvent.VK_S){
                yDir = 1;
            }
        } else if(padID == 2){
            if(event.getKeyCode() == KeyEvent.VK_UP){
                yDir = -1;
            } else if(event.getKeyCode() == KeyEvent.VK_DOWN){
                yDir = 1;
            }
        }
    }

    public void keyReleased(KeyEvent event) {
        if(padID == 1) {
            if (event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S ) {
                yDir = 0;
            }
        } else if(padID == 2) {
            if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
                yDir = 0;
            }
        }
    }

    public void move() {
        this.y += this.yDir;
        paddle.y = this.y;
        if(this.y <= padH - 20) {
            this.y = 30;
        } else if(this.y >= PongGame.getWindowHeight() - padH - 10){
          this.y = PongGame.getWindowHeight() - padH - 10;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(this.x, this.y, padW, padH);
    }

    public static int getPadH(){
        return padH;
    }

    public static int getPadW(){
        return padW;
    }

    public int getY(){
        return this.y;
    }

    public Rectangle getPaddle() {
        return paddle;
    }

    public int getPadID() {
        return padID;
    }

    @Override
    public void run() {
        try {
            while(true) {
                move();
                Thread.sleep(PongGame.getDifficulty());
            }
        } catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

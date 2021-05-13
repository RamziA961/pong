package pongGame;

import java.awt.*;
import java.util.Random;

public class Ball implements Runnable {
    private int x, y, xDir, yDir;
    private int scoreP1, scoreP2;
    private int ballH = 15, ballW = 15;

    private Rectangle pongBall;
    private Paddle pad1 = new Paddle(20, PongGame.getWindowHeight()/2 - Paddle.getPadH()/2, 1);
    private Paddle pad2 = new Paddle(PongGame.getWindowWidth() - 30, PongGame.getWindowHeight()/2 - Paddle.getPadH()/2, 2);

    Ball(int x, int y){
        scoreP1 = scoreP2 = 0;
        this.x = x;
        this.y = y;

        Random random = new Random();
        this.xDir = random.nextInt(2);
        if(this.xDir == 0) {
            this.xDir--;
        }

        this.yDir = random.nextInt(2);
        if(this.yDir == 0) {
            this.yDir--;
        }

        pongBall = new Rectangle(this.x, this.y, ballW, ballH);
    }

    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect(this.x, this.y, this.ballW, this.ballH);
    }

    int getPadScore(int id) {
        if(id == 1){
            return scoreP1;
        }
        return scoreP2;
    }

    public void collision() {
        if(pongBall.intersects(pad1.getPaddle())) {
            this.xDir = 1;
            SoundEffects.playCollision();
            checkCollisionLocation(pad1);
        } else if(pongBall.intersects(pad2.getPaddle())) {
            this.xDir = -1;
            SoundEffects.playCollision();
            checkCollisionLocation(pad2);
        }
    }

    public void checkCollisionLocation(Paddle pad) {
        int paddleThird = Paddle.getPadH()/3;
        int padYPos = pad.getY();

        if(this.y < padYPos + paddleThird) {
            this.yDir = -1;
        } else if (this.y < padYPos + paddleThird*2) {
            this.yDir = 0;
        } else {
            this.yDir = 1;
        }
    }

    public void move() {
        collision();
        this.x += this.xDir;
        this.y += this.yDir;
        pongBall.x = this.x;
        pongBall.y = this.y;

        if(this.x <= 5) {
            this.xDir = 1;
            scoreP2++;
            SoundEffects.playScore();
        }
        if(this.x >= PongGame.getWindowWidth() - 20) {
            this.xDir = -1;
            scoreP1++;
            SoundEffects.playScore();
        }
        if(this.y <= 25){
            this.yDir = 1;
        }
        if(this.y >= PongGame.getWindowHeight() - 20) {
            this.yDir = -1;
        }
    }

    Paddle getPad1() {
        return pad1;
    }

    Paddle getPad2() {
        return pad2;
    }

    @Override
    public void run() {
        try {
            while(true) {
                move();
                Thread.sleep(PongGame.getDifficulty());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}

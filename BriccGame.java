
package briccbraccer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class BriccGame extends JPanel implements KeyListener, ActionListener {

    private boolean play = true;
    private Graphics g;
    private int playerX = 330;
    private final Timer timer;
    private int ballXpos = 350, ballYpos = 320;
    private int ballXdirection = -3, ballYdirection = -5;
    private Bricks brick;
    private int i = 13;
    private final Color color = Color.red;
    private int score = 0;

    public BriccGame() {

        brick = new Bricks(25,15,30,50);
        addKeyListener(this);
        setFocusable(true);
        timer = new Timer(8, this);
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.g = g;

        //background
        g.setColor(Color.black);
        g.fillRect(1, 1, 995, 600);

        //bounds
        g.setColor(Color.red);
        g.fillRect(2, 2, 995, 5);
        g.fillRect(2, 2, 5, 600);
        g.fillRect(995, 2, 5, 600);

        //ball
        g.setColor(Color.green);
        g.fillOval(ballXpos, ballYpos, 15, 15);

        //player
        g.setColor(Color.blue);
        g.fillRect(playerX, 500, 100, 7);

        Font currentFont = g.getFont();
        g.setFont(new Font("Arial", Font.PLAIN, 25));
        g.drawString("Score = " + score, 850, 40);
        if (ballYpos >= 570) {
            g.setColor(Color.red);
            currentFont = g.getFont();

            Font newFont = currentFont.deriveFont(currentFont.getSize() * 1F);
            g.setFont(newFont);
            g.drawString("GAME OVER", 400, 300);
            g.drawString("PRESS SPACE TO PLAY AGAIN", 300, 350);

        }

        if(brick.bricks.size()==1){
            g.setColor(color);
            g.setFont(new Font("Arial",Font.PLAIN, 25));
            g.drawString("YOU WON", 415, 150);
            g.drawString("PRESS SPACE TO PLAY AGAIN", 300, 200);

        }
        
        update(); // the game function

        g.dispose();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 895) {
                playerX = 895;
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX <= 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }

        if (play == false) {

            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                brick.bricks.clear();

                restart();
                play = true;

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (new Rectangle(ballXpos, ballYpos, 15, 5).intersects(new Rectangle(playerX, 500, 110, 1))) {
            ballYdirection = -ballYdirection;
        }

        ballXpos = ballXpos + -(ballXdirection);
        ballYpos = ballYpos + -(ballYdirection);

        if (ballXpos < 0) {
            ballXdirection = -ballXdirection;
        }

        if (ballYpos < 0) {
            ballYdirection = -ballYdirection;
        }

        if (ballXpos >= 995) {
            ballXdirection = -ballXdirection;
        }

        if (ballYpos >= 570) {
            play = false;
            timer.stop();
        }
        
        if(brick.bricks.size()==1){
            play = false;
            timer.stop();
        }
        
        repaint();

    }

    void moveRight() {
        playerX += 35;

    }

    void moveLeft() {
        playerX -= 35;

    }

    //public boolean x = true;
    public void update() {
        
        for (i = brick.bricks.size() -1 ; i > 0; i--) {

            Rectangle obj = brick.bricks.get(i);
            
            if (obj != null) {
                g.setColor(color);
                g.fillRect(obj.x, obj.y, obj.width, obj.height);
               
            }

            if (new Rectangle(ballXpos, ballYpos, 10, 15).intersects(obj)) {
                ballYdirection = -ballYdirection;

                brick.bricks.remove(i);

                score += 5;

            }

        }

    }

    void restart() {

        timer.start();
        ballYpos = 350;
        ballXpos = 320;

        ballXpos = ballXpos + -(ballXdirection);
        ballYpos = ballYpos + -(ballYdirection);

        if (ballXpos < 0) {
            ballXdirection = -ballXdirection;
        }

        if (ballYpos < 0) {
            ballYdirection = -ballYdirection;
        }

        for (int i = 33; i > 0; i--) {
            
            g.setColor(color);
            Rectangle obj = new Rectangle();
            Rectangle obj2 = new Rectangle();
            Rectangle obj3 = new Rectangle();
            Rectangle obj4 = new Rectangle();
            obj.setBounds(brick.bricksX * i * 1, brick.bricksY, brick.bricksWidth, brick.bricksHeight);
            obj2.setBounds(brick.bricksX * i * 1, brick.bricksY*2, brick.bricksWidth, brick.bricksHeight);
            obj3.setBounds(brick.bricksX * i * 1, brick.bricksY*3, brick.bricksWidth, brick.bricksHeight);
            obj4.setBounds(brick.bricksX * i * 1, brick.bricksY*4, brick.bricksWidth, brick.bricksHeight);
            g.fillRect(obj.x, obj.y, obj.width, obj.height);
            g.fillRect(obj2.x, obj2.y, obj2.width, obj2.height);
            
            brick.bricks.add(obj);
                if(i < 33)
                {
                    brick.bricks.add(obj2);
                    brick.bricks.add(obj3);
                    brick.bricks.add(obj4);
                }
                
            
        }
        
        
        score = 0;
    }

}

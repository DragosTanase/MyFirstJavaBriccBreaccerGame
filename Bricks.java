
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
import javax.swing.JPanel;
import javax.swing.Timer;

public class Bricks extends JPanel {

    public ArrayList<Rectangle> bricks = new ArrayList<Rectangle>();
    protected int bricksWidth;
    protected int bricksHeight;
    protected int bricksX;
    protected int bricksY;
    protected int j = 34;

    public Bricks(int bricksWidth, int bricksHeight, int bricksX, int bricksY) {
        this.bricksWidth = bricksWidth;
        this.bricksHeight = bricksHeight;
        this.bricksX = bricksX;
        this.bricksY = bricksY;
        for (int i = j - 1; i > 0; i--) {
            bricks.add(new Rectangle(bricksX * i * 1, bricksY, bricksWidth, bricksHeight));    
        }
        for (int i = j-2; i > 0; i--) {
            bricks.add(new Rectangle(bricksX * i * 1, bricksY*2, bricksWidth, bricksHeight));
            
        }
        for (int i = j-2; i > 0; i--) {
            bricks.add(new Rectangle(bricksX * i * 1, bricksY*3, bricksWidth, bricksHeight));
            
        }
        for (int i = j-2; i > 0; i--) {
            bricks.add(new Rectangle(bricksX * i * 1, bricksY*4, bricksWidth, bricksHeight));
            
        }

    }

    public void drawBrick(Graphics2D g) {

        for (int i = j - 1; i > 0; i--) {
            g.setColor(Color.red);
            g.fillRect(bricksX * i * 2, bricksY, bricksWidth, bricksHeight);
        }

    }

}


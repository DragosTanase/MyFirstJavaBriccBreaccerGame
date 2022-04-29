
package briccbraccer;

import java.util.ArrayList;
import javax.swing.JFrame;

public class BriccBraccer {

   
    public static void main(String[] args) {
        JFrame j = new JFrame("Brick Breaker");
        BriccGame game = new BriccGame();
        j.setBounds(10,10,1015,600);
        j.setSize(1015,600);
        j.setVisible(true);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.add(game);
        
       
       
       
    }
    
}

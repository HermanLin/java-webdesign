import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class ButtonGrid {
    
    // ROW * COL = total number of buttons
    // - adjust these values for diff. number of buttons
    static final int ROW = 4;
    static final int COL = 2;

    // grid-associated representation of the buttons
    RainbowButton[] buttons;

}

class RainbowButton {

    JButton button;
    Random rand;

    RainbowButton() {
        button = new JButton();
        rand = new Random();

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        randomizeColor();
    }

    private void randomizeColor() {
        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        button.setBackground(new Color(r, g, b));
        button.setText(button.getBackground().toString());
    }
}
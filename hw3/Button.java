import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Button extends JButton {

    Boolean status; // state for whether button is enabled/disabled
    float r, g, b; // color values for the button's background
    Random rand; // instance of Random for randomized values

    Button() {
        status = true; // true means button will change color
        rand = new Random();
        randomizeColor();

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // need to change the Button's status from the actionListener
                Button.this.status = !Button.this.status;
                // update the Button's text to match it's status
                setText(Button.this.status.toString());
            }
        });
        
        setText(status.toString());
    }
    
    public void randomizeColor() {
        r = rand.nextFloat();
        g = rand.nextFloat();
        b = rand.nextFloat();
        setBackground(new Color(r, g, b));
    }
}
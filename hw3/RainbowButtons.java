import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class RainbowButtons {

    static RainbowButtons rb;
    
    // ROW * COL = total number of buttons
    // - adjust these values for diff. number of buttons
    static final int ROW = 4;
    static final int COL = 2;

    // grid-associated representation of the buttons
    JButton[] buttons;

    // Default Constructor
    RainbowButtons() {
        buttons = new JButton[ROW*COL];

        JFrame jf = new JFrame("Rainbow Buttons");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(COL * 400, ROW * 200);
        jf.setLayout(new GridLayout(ROW, COL));

        createButtons(jf);

        jf.setVisible(true);
    }

    private void createButtons(JFrame jf) {
        for (int i = 0; i < ROW * COL; i++) {
            JButton newButton = new JButton();
            
            // initialize random background colors for the buttons
            // and label them with the color
            randomizeColor(newButton);

            // add an ActionListener to the button
            newButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton pressed = (JButton) e.getSource();
                    randomizeGridColors(pressed);
                }
            });
        
            buttons[i] = newButton;
            jf.add(newButton);
        }
    }

    private void randomizeColor(JButton jb) {
        Random rand = new Random();

        float r = rand.nextFloat();
        float g = rand.nextFloat();
        float b = rand.nextFloat();

        jb.setBackground(new Color(r, g, b));
        jb.setText(jb.getBackground().toString());
    }

    private void randomizeGridColors(JButton jb) {
        for (JButton button : buttons) {
            if (button != jb) {
                randomizeColor(button);
            }
        }
        /*  just to prove the button that was pressed 
            never changes color, uncomment below */
        //jb.setText(jb.getBackground().toString());
    }

    public static void main(String[] args) {
        rb = new RainbowButtons();   
    }
    
}
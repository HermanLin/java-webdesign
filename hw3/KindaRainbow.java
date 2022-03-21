import java.awt.*;
import javax.swing.*;

public class KindaRainbow {
    
    static KindaRainbow kr;

    // ROW * COL = total number of buttons
    // - adjust these values for diff. number of buttons
    static final int ROW = 4;
    static final int COL = 2;

    // array representation of grid of Buttons
    Button[] buttons;

    KindaRainbow() {
        // instantiate array of Buttons
        buttons = new Button[ROW * COL];

        JFrame jf = new JFrame("Kinda Rainbow Buttons");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(COL * 400, ROW * 200);
        jf.setLayout(new GridLayout(ROW, COL));

        createButtons(jf);

        jf.setVisible(true);
    }

    private void createButtons(JFrame  jf) {
        for (int i = 0; i < ROW * COL; i++) {
            Button newButton = new Button();
            newButton.randomizeColor();

            buttons[i] = newButton;
            jf.add(newButton);
        }
    }

    public static void main(String[] args) {
        kr = new KindaRainbow();

        ColorChanger cc = new ColorChanger(kr.buttons);
        cc.start();
    }
}

/* 
 * ColorChanger class used to change Button colors about every second
 */
class ColorChanger extends Thread {
    
    Button[] buttons;

    ColorChanger(Button[] newButtons) { buttons = newButtons; }

    public void run() {
        while (true) {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) { return; }

            for (Button b : buttons) {
                if (b.status) { b.randomizeColor(); }
            }
        }
    }
}
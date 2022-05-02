import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Clock extends JFrame {

    Clock() {
        // set default parameters for the window
        super("Analog Clock");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);

        // create a new clock face and add it to the frame
        Face jp = new Face();

        // an attempt to maintain "square-ness" of the clock in
        // order to keep the various clock dimensions consistent
        // NOTE: resizing is glitchy and should be done with 
        //       caution (do not use unless you are ready...)
        /*
        jp.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = getSize().width;
                int height = getSize().height;
                if (width >= height) { setSize(width, width); }
                else { setSize(height, height); }
                repaint();
            }
        });
        */
        add(jp);

        // start a thread to continually update the clock face
        new Thread() {
            public void run() {
                while(true) {
                    jp.repaint();
                }
            }
        }.start();

        // sometimes the panel isn't drawn when started; this
        // will re-evaluate all components up to the JFrame
        revalidate();
    }

    public static void main(String[] args) {
        Clock clock = new Clock();
    }
}

class Face extends JPanel {

    Face() { super(); }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getSize().width;
        int h = getSize().height;

        // Graphics2D allows us to set a stroke width when drawing lines
        Graphics2D g2d = (Graphics2D) g;
        
        // draw clock bezel
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLACK);
        g2d.drawOval(10, 10, w - 20, h - 20);
        g2d.fillOval(w/2, h/2, 5, 5);

        /*================= the monster math =================
          - polar to Cartesian coordinates:
            x = r * cos(t); y = r * sin(t)
            where r is the radius, t is the degrees from (r,0)
          
          - because the conversion begins from (r,0), we need
            adjust angles by negating to get values in clock-
            wise order and adjust by 90 degrees to have (0,r)
            as the starting point of our conversion.

          - we also need to adjust (x,y) values by dim/2 
            because the conversion assumes (0,0) is in the 
            center, not at the top left like our context's
            coordinate system.
        ====================================================*/

        g2d.setStroke(new BasicStroke(1));
        // draw numbers around the clock bezel
        int degrees = -60;
        for (int i = 1; i <= 12; i++) {
            double angle = Math.toRadians(degrees);
            int numX = (int)((w/2 - 20) * Math.cos(angle));
            int numY = (int)((h/2 - 20) * Math.sin(angle));
            g2d.drawString("" + i, numX + (w/2) - 5, numY + (h/2) + 5);
            degrees += 30;
        }
        
        // get the current time
        Calendar time = Calendar.getInstance();
        int hour = time.get(Calendar.HOUR);
        int minute = time.get(Calendar.MINUTE);
        int second = time.get(Calendar.SECOND);

        // every second, the second hand moves 6 degrees
        double secondAngle = Math.toRadians((6 * second) - 90);
        int secondX = (int)((w/2 - 40) * Math.cos(secondAngle));
        int secondY = (int)((h/2 - 40) * Math.sin(secondAngle));

        // every minute, the minute hand moves 6 degrees,
        // but every second, it moves 0.1 degrees
        double minuteAngle = 
            Math.toRadians((6 * minute) - 90 + (second * 0.1));
        int minuteX = (int)((w/2 - 20) * Math.cos(minuteAngle));
        int minuteY = (int)((h/2 - 20) * Math.sin(minuteAngle));

        // every hour, the hour hand moves 30 degrees,
        // but every minute, it moves 0.5 degrees
        double hourAngle = Math.toRadians((30 * hour) - 90 + (minute * 0.5));
        int hourX = (int)((w/2 - 70) * Math.cos(hourAngle));
        int hourY = (int)((h/2 - 70) * Math.sin(hourAngle));

        // draw hour hand
        g2d.setStroke(new BasicStroke(4));
        g2d.setColor(Color.RED);
        g2d.drawLine(w/2, h/2, hourX + (w/2), hourY + (h/2));
        // draw minute hand
        g2d.setStroke(new BasicStroke(3));
        g2d.setColor(Color.BLUE);
        g2d.drawLine(w/2, h/2, minuteX + (w/2), minuteY + (h/2));
        // draw second hand
        g2d.setStroke(new BasicStroke(1));
        g2d.setColor(Color.MAGENTA);
        g2d.drawLine(w/2, h/2, secondX + (w/2), secondY + (h/2));
    }
}
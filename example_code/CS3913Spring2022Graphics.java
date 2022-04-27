/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cs3913spring2022graphics;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author dkatz
 */
public class CS3913Spring2022Graphics {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame jf = new JFrame("My graphics window");
        jf.setSize(600,600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DrawPanel jp = new DrawPanel();
        /*new Thread(){
            public void run(){
                while(true){
                    try{
                        sleep(10);

                    } catch (InterruptedException ex) {}
                    jp.repaint();
                }
            }
        }.start();*/
        jp.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                jp.points.add(new Point(e.getX(), e.getY()));
                jp.repaint();
            }
        });
        jf.add(jp);
        jf.setVisible(true);
    }
    
}
class Point{
        int x;
        int y;
        Point(int newx, int newy){
            x=newx;
            y = newy;
        }
        Point(){this(0,0);}
    }

class DrawPanel extends JPanel{
    
    ArrayList<Point> points;
    DrawPanel(){
        points = new ArrayList();
    }
    @Override
    protected void paintComponent(Graphics g){
        for (Point p: points){
            g. fillOval(p.x-5, p.y-5, 10, 10);
        }
    }
}

class MyPanel extends JPanel{
    int dist;
    Color[] colorArr;
    int currentColor;
    MyPanel(){
        super();
        dist=0;
        colorArr= new Color[5];
        colorArr[0] = Color.BLACK;
        colorArr[1] = Color.BLUE;
        colorArr[2] = Color.MAGENTA;
        colorArr[3] = Color.PINK;
        colorArr[4] = Color.ORANGE;
        currentColor = 0;
    }
    @Override
    protected void paintComponent(Graphics g){
        dist++;
        super.paintComponent(g);
        int height = getSize().height;
        int width = getSize().width;
        if (dist>20){
            currentColor++;
            currentColor%=colorArr.length;
            dist=0;
        }
        for (int j = 0; j<width+height; j+=20){
            boolean horizontal=true;
            int i = j+dist;
            if (horizontal && i>width)
                horizontal = false;
            else if(i>(width+height)){
                horizontal = true;
                i=0;
            }
            g.setColor(colorArr[currentColor]);
            int startx;
            int starty;
            int endx;
            int endy;
            if (horizontal){
                startx = i;
                starty = 0;
                endx = width-(i%width);
                endy = height;
            }
            else{
                int overrun = i%width;
                startx = width;
                starty = overrun;
                endx = 0;
                endy = height-overrun;
            }
            g.drawLine(startx,starty,endx,endy);
        }
    }
}

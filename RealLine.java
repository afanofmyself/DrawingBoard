package drawBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class RealLine {
    public static int x1,y1,x2,y2,x3,y3;
    public void draw(MouseEvent e , Graphics g, ArrayList<Point> specialList, Color color, ArrayList<Shapes> shapeList){
        Color pre = g.getColor();
        g.setColor( new JButton().getBackground());
        if(x2 !=0 ){
            g.drawLine(x2,y2,x1,y1);
        }
        g.setColor(pre);
        x3 = e.getX();
        y3 = e.getY();
        g.drawLine(x3,y3,x1,y1);
        x2=x3;
        y2=y3;
        BufferedImage bufferedImage = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
        Graphics buffs = bufferedImage.getGraphics();
        for(Shapes shape : shapeList){
            shape.drawShape(buffs);
        }
        g.drawImage(bufferedImage,0,0,null);
    }
}
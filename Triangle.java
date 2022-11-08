package drawBoard;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Triangle {
    static int x1,y1,x2,y2,x3,y3;
    static int num;
    public void drawTriangle(MouseEvent e , Graphics g, ArrayList<Point> specialList, Color color, ArrayList<Shapes> shapeList){
        if(num == 0){
            specialList.clear();
            x1 = e.getX();
            y1 = e.getY();
            num++;
            specialList.add(new Point(x1,y1));
        }else if(num == 1){
            x2 = e.getX();
            y2 = e.getY();
            g.drawLine(x1,y1,x2,y2);
            num++;
            specialList.add(new Point(x2,y2));
        }else if(num == 2){
            x3 = e.getX();
            y3 = e.getY();
            g.drawLine(x3,y3,x2,y2);
            g.drawLine(x3,y3,x1,y1);
            num=0;
            specialList.add(new Point(x3,y3));
            SpecialShape specialShape = new SpecialShape("三角形", new Color(color.getRGB()), specialList);
            shapeList.add(specialShape);
        }
    }
}

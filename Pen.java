package drawBoard;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Pen {
    public static int x1,y1,x2,y2;
    public static int state = 1;
    public void draw(MouseEvent e , Graphics g, ArrayList<Point> specialList, Color color, ArrayList<Shapes> shapeList) {
        switch(state){
            case 1 :
                x1 = e.getX();
                y1 = e.getY();
                specialList.add(new Point(x1,y1));
                state = 2;
                break;
            case 2 :
                x2 = e.getX();
                y2 = e.getY();
                specialList.add(new Point(x2,y2));
                g.drawLine(x2,y2,x1,y1);
                state = 3;
                break;
            case 3 :
                x1 = e.getX();
                y1 = e.getY();
                specialList.add(new Point(x1,y1));
                g.drawLine(x2,y2,x1,y1);
                state = 2;
                break;
        }

    }
}


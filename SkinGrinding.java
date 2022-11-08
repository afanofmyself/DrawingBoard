package drawBoard;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SkinGrinding {
    public static int x1,y1,x2,y2;
    public static int state = 1;

    public void draw(MouseEvent e , Graphics2D g, ArrayList<Point> specialList, int[][] img, ArrayList<Shapes> shapeList,int w,int h) {
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
                g.setColor(new Color(img[x2-w][y2-h]));
                specialList.add(new Point(x2,y2));
                g.drawLine(x2,y2,x1,y1);
                state = 3;
                break;
            case 3 :
                x1 = e.getX();
                y1 = e.getY();
                specialList.add(new Point(x1,y1));
                g.setColor(new Color(img[x1-w][y1-h]));
                g.drawLine(x2,y2,x1,y1);
                state = 2;
                break;
        }

    }
}

package drawBoard;

import javax.swing.*;
import java.awt.*;


public class BasicShape extends Shapes {
    private int x1,y1,x2,y2;

    public BasicShape(String shapeName, Color color,int x1, int y1, int x2, int y2) {
        super(x1,y1,x2,y2);
        this.shapeName = shapeName;
        this.color = color;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    @Override
    public void drawShape (Graphics g){
        super.drawShape(g);
        switch (shapeName){
            case "直线":
                g.setColor(color);
                g.drawLine(x1,y1,x2,y2);
                break;
            case "矩形":
                g.drawRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(y2-y1));
                break;
            case "圆" :
//                g.drawOval(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(y2-y1));
                g.drawOval(Math.min(x1,x2),Math.min(y1,y2),
                        Math.max(Math.abs(x1-x2),Math.abs(y1-y2)),
                        Math.max(Math.abs(x1-x2),Math.abs(y1-y2)));
                break;
            case "椭圆" :
                System.out.println("test!");
                g.drawOval(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(y2-y1));
                break;
            case "谢尔宾斯基地毯" :
                Sierpinski(g,Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(y2-y1));
                break;

            case "实心矩形" :
                g.fillRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(y2-y1));
                break;

            case "实心圆" :
                g.fillOval(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(y2-y1));
                break;

            case "等腰三角形" :
                g.drawLine(x1,y2,x2,y2);
                g.drawLine(x1,y2,(x1+x2)/2,y1);
                g.drawLine(x2,y2,(x1+x2)/2,y1);
                break;
            case "立方体" :
                g.drawRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(y2-y1));
                g.drawLine(x1+(int)((x2-x1)*1.414/4),y1-(int)((y2-y1)*1.414/4),x1,y1);
                g.drawLine(x1+(int)((x2-x1)*1.414/4),y1-(int)((y2-y1)*1.414/4),x2+(int)((x2-x1)*1.414/4),y1-(int)((y2-y1)*1.414/4));
                g.drawLine(x2,y1,x2+(int)((x2-x1)*1.414/4),y1-(int)((y2-y1)*1.414/4));
                g.drawLine(x2+(int)((x2-x1)*1.414/4),y2-(int)((y2-y1)*1.414/4),x2+(int)((x2-x1)*1.414/4),y1-(int)((y2-y1)*1.414/4));
                g.drawLine(x2+(int)((x2-x1)*1.414/4),y2-(int)((y2-y1)*1.414/4),x2,y2);
                break;

            case "橡皮擦" :
                Color pre = g.getColor(); //记录之前的颜色 ，用完再换回去
                g.setColor( new JButton().getBackground());
                g.fillRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x2-x1),Math.abs(y2-y1));
                g.setColor(pre);
                break;

            default:
                break;
        }
    }


    public void Sierpinski(Graphics g,int x,int y,int w,int h){
        if(w>0&&h>0){
            g.fillRect(x+w/3,y+h/3,w/3,h/3);
            Sierpinski(g,x,y,w/3,h/3);
            Sierpinski(g,x+w/3,y,w/3,h/3);
            Sierpinski(g,x+2*w/3,y,w/3,h/3);
            Sierpinski(g,x,y+h/3,w/3,h/3);
            Sierpinski(g,x+2*w/3,y+h/3,w/3,h/3);
            Sierpinski(g,x,y+2*h/3,w/3,h/3);
            Sierpinski(g,x+w/3,y+2*h/3,w/3,h/3);
            Sierpinski(g,x+2*w/3,y+2*h/3,w/3,h/3);
        }

    }
    public void changeLocation(int xs,int ys,int xd,int yd){
         x1=x1 - xs+xd;
         x2 = x2 -xs + xd;
         y1 = y1-ys+yd;
         y2=y2-ys+yd;
    }
}

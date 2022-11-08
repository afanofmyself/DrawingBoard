package drawBoard;

import java.awt.Color;
import java.awt.Graphics;
public class Shapes {
    public String shapeName;
    public Color color;
    int x1,y1;
    int x2,y2;
    Shapes(){}
    Shapes(String name){
        this.shapeName = name;
    }
    Shapes(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    public void drawShape (Graphics g){
        g.setColor(color);

    }
    public void setLocation(int x1,int y1,int x2,int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 =y1;
        this.y2 = y2;
    }//保存该图形位置信息
    public boolean isIn(int a, int b){
        if(shapeName == "直线"){
            if(Math.abs(((a-x2)/(b-y2))-((x1-x2)/(y1-y2)))<0.1&&(Math.abs(a-x2)<Math.abs(x1-x2))&&(Math.abs(b-y2)<Math.abs(y1-y2)))
                return true;
        }
        return false;
    }//判断当前坐标是否在该图形

}



//ywb
package drawBoard;

import java.awt.*;
import java.util.ArrayList;

public class PolygonPro {
    //挑选x坐标最大的点作为基准点，计算其余点与基准点的正切值，根据正切值从大到小依次连接，得到一个多边形。
    public void drawPolygonPro(ArrayList<Point> list, Graphics g, ArrayList<Point> specialList, Color color, ArrayList<Shapes> shapeList){
        if(list.size() == 0||list.size() == 1||list.size() == 2) return;
        int right = findRight(list);
        System.out.println(right);
        Point rightPoint  = new Point(list.get(right).x, list.get(right).y);
        specialList.add(rightPoint);
        list.remove(right);
        double[] tan = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            tan[i] = ((double) rightPoint.y-list.get(i).y)/(rightPoint.x-list.get(i).x);
        }
        int pre;
        int cur = indexOfMax(tan);
        specialList.add(list.get(cur));
        g.drawLine(list.get(cur).x,list.get(cur).y, rightPoint.x, rightPoint.y);
        tan[cur] = Integer.MIN_VALUE;
        for (int i = 0; i < tan.length-1; i++) {
            pre = cur;
            cur = indexOfMax(tan);
            specialList.add(list.get(cur));
            g.drawLine(list.get(pre).x,list.get(pre).y, list.get(cur).x,list.get(cur).y);
            tan[cur] = Integer.MIN_VALUE;
        }
        g.drawLine(list.get(cur).x,list.get(cur).y, rightPoint.x, rightPoint.y);

    }

    private int findRight(ArrayList<Point> list) {
        int result = 0;
        for (int i = 1; i < list.size(); i++) {
            result = list.get(i).x>list.get(result).x?i:result;
        }
        return result ;
    }

    //返回数组中的最大值的下标
    private int indexOfMax(double[] tan){
        int v= 0 ;
        for(int i = 1 ; i < tan.length; i ++){
            v = tan[i]>tan[v]?i:v;
        }
        return v;
    }
}

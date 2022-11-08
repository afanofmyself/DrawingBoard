package drawBoard;

import java.awt.*;
import java.util.ArrayList;

public class SpecialShape extends Shapes {
    public ArrayList<Point> specialList = new ArrayList<>();
    private Point first;
    private Point pre;
    private Point cur;

    public SpecialShape(String shapeName, Color color, ArrayList<Point> specialList) {
        this.shapeName = shapeName;
        this.color = color;
        for (Point p : specialList) {
            this.specialList.add(p);
        }
    }

    @Override
    public void drawShape(Graphics g) {
        super.drawShape(g);
        switch (shapeName) {
            case "三角形":
            case "多边形":
            case "改进多边形":
                if (specialList.isEmpty()) break;
                int i = 0;
                first = specialList.get(i++);
                cur = first;
                while (i < specialList.size()) {
                    pre = cur;
                    cur = specialList.get(i++);
                    g.drawLine(pre.x, pre.y, cur.x, cur.y);
                }
                g.drawLine(first.x, first.y, cur.x, cur.y);
                break;
            case "签字笔":
            case "递归KLine":
            case "实时直线":
            case "任意线":
                if (specialList.isEmpty()) break;
                int j = 0;
                while (j < specialList.size()-1) {
                    g.drawLine(specialList.get(j).x, specialList.get(j).y, specialList.get(j+1).x, specialList.get(++j).y);
                }
                break;
        }
    }
}

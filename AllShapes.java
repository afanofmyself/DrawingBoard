package drawBoard;

import java.awt.*;

public class AllShapes extends DrawListener{
    public void withDraw(int index){
        if (!super.shapeList.isEmpty()) {
            super.shapeList.remove(super.shapeList.size()-1);
            super.drawJPanel.paint(super.g);
        }
        System.out.println("withDraw");
    }//撤回上一步的操作

    public void addNewShape (int a, int b){
        int i = 0;
        for(Shapes ashape:shapeList){
            if(ashape.isIn(x2,y2)){
                int i2 = shapeList.indexOf(ashape);
                System.out.println("BasicShape is chosen !!!");
                toMoveNum = i2;
                shapeName =ashape.shapeName;//得到选中的图形的名称
                BasicShape basicShape = new BasicShape(shapeName, new Color(color.getRGB()), x2, y2, x3, y3);

                withDraw(i2);

                basicShape.drawShape(g);
                shapeList.add(basicShape);
                //ywb
            }
            i++;
        }
        System.out.println("add New Shape!");
    }


}

//todo 列出所有图形
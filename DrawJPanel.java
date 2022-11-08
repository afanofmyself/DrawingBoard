package drawBoard;



import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class DrawJPanel extends JPanel {
    DrawListener dl ;

    @Override
    public void paint(Graphics g){
        super.paint(g);
        BufferedImage bufferedImage = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
        Graphics buffg = bufferedImage.getGraphics();
        for(Shapes shape : dl.shapeList){
            shape.drawShape(buffg);
        }
        g.drawImage(bufferedImage,0,0,null);
    }


    public void setDl(DrawListener dl) {
        this.dl = dl;
    }
}

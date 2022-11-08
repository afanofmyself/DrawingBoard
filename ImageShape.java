package drawBoard;



import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageShape extends Shapes {
    BufferedImage bufferedImage;
    @Override
    public void drawShape(Graphics g){
        g.drawImage(bufferedImage,0,0,null);
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }
}

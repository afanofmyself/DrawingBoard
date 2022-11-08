package drawBoard;

import java.awt.image.BufferedImage;

public class ColorArray{
    static final int TYPE_RED = 0;
    static final int TYPE_GREEN = 1;
    static final int TYPE_BLUE = 2;
    public int[][] array = {};
    ColorArray(BufferedImage img , int type){
        if(type == TYPE_RED){
            array = new int[img.getWidth()][img.getHeight()];
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    array[i][j] = (img.getRGB(i,j)>>16) & 0xff;
                }
            }
        }else if(type == TYPE_GREEN){
            array = new int[img.getWidth()][img.getHeight()];
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    array[i][j] = (img.getRGB(i,j)>>8) & 0xff;
                }
            }
        }else if(type == TYPE_BLUE){
            array = new int[img.getWidth()][img.getHeight()];
            for (int i = 0; i < img.getWidth(); i++) {
                for (int j = 0; j < img.getHeight(); j++) {
                    array[i][j] = img.getRGB(i,j) & 0xff;
                }
            }

        }
    }
}
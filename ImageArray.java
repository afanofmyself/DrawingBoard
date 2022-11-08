package drawBoard;

import java.awt.image.BufferedImage;
/**
 * 这是一个用来存储图片的动态数组类 /可以实现数组自动扩容
 * 存储的图片对象类型是： BufferedImage
 * 目前实现了：
 * add方法
 * get方法
 * remove方法
 * size方法
 */
public class ImageArray {

    private BufferedImage[] imgArray = {};

    /**
     * 数组默认初始化容量
     */
    private static final int defaultLength = 10;

    private int  size;

    /**
     * 数组当前的空间容量
     */
    private int length;

    // 每张存入进来图片的三 通道矩阵数组
    public ColorArray[] redArray = {};
    public ColorArray[] greenArray = {};
    public ColorArray[] blueArray = {};

    public int getSize(){
        return size;
    }

    //放大或缩小redArray的数值
    public int[][] multiple(int multiple , ColorArray colorArray){
        int w = colorArray.array.length;
        int h = colorArray.array[0].length;
        int[][] res = new int[w][h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                res[i][j] = Math.min(255,(int)(colorArray.array[i][j]*1.0*multiple/100));
            }
        }
        return res;
    }

    /**
     * 图片动态数组的初始化构造方法
     */
    public ImageArray(int initSize){
        if(initSize < defaultLength){
            length = defaultLength;
            imgArray = new BufferedImage[length];
            redArray = new ColorArray[length];
            greenArray = new ColorArray[length];
            blueArray = new ColorArray[length];
            size = 0;
        }else{
            length = initSize;
            imgArray = new BufferedImage[length];
            redArray = new ColorArray[length];
            greenArray = new ColorArray[length];
            blueArray = new ColorArray[length];
            size = 0;
        }
    }

    public void add(BufferedImage img){
        if(size >= length){
            int oldlength = length;
            length = oldlength + oldlength>>1;
            BufferedImage[] newArray = new BufferedImage[length];
            for (int i = 0; i < oldlength; i++) {
                newArray[i] = imgArray[i];
            }
            imgArray = newArray;
            newArray = null;
        }
        imgArray[size] = img ;
        redArray[size] = new  ColorArray(img,ColorArray.TYPE_RED);
        greenArray[size] = new  ColorArray(img,ColorArray.TYPE_GREEN);
        blueArray[size] = new  ColorArray(img,ColorArray.TYPE_BLUE);
        size++;
    }

    public void remove(int index) {
        imgArray[index] = null;
        size--;
    }
    //注意index的合法性
    public BufferedImage get(int index) {
        return imgArray[index] ;
    }

}

package drawBoard;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class DrawListener extends DrawListenerFather {
    protected Graphics g ;
    String shapeName = null ;
    String btn_action ;
    Color color=Color.black ;
    int x2,y2,x3,y3 ;
    int xs1,ys1,xs2,ys2;
    ArrayList<Point> list = new ArrayList<>() ;
    public ArrayList<Shapes> shapeList= new ArrayList<>() ;
    ArrayList<Point> specialList = new ArrayList<>() ;
    String fileName ;
    DrawJPanel drawJPanel ;
    int[][] img11 ;
    Graphics2D g2D ;
    ButtonUI buttonUI ;
    int multiple;
    int multipleRed = 100;
    int multipleGreen = 100;
    int multipleBlue = 100;
    static ImageArray imageArray = new ImageArray(10);
    Font aFont = new Font("1",Font.PLAIN,10);
    Color textColor = Color.black;
    static int change_flag = 0;//移动元素的标志
    BasicShape toMove;
    int toMoveNum = -1;

    public void setG(Graphics g) {
        this.g = g;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        btn_action = e.getActionCommand();
        if(btn_action.equals("选择颜色...")){
            color = JColorChooser.showDialog(drawJPanel, "选择颜色", Color.red);
            System.out.println(color.getRGB());
            g.setColor(color);
            return;
        }
        if(btn_action.equals("颜色")){
            color = JColorChooser.showDialog(drawJPanel, "选择颜色", Color.red);
            System.out.println(color.getRGB());
            g.setColor(color);
            return;
        }
        if(btn_action.equals("更多操作...")){
            buttonUI =  new ButtonUI();
            buttonUI.init();
            change_flag = 1; //ywb
//            new DrawUI().initUI();  ywb多开窗口
            return;
        }
        if(btn_action.equals("")){
            JButton btn = (JButton) e.getSource();
            color = btn.getBackground();
            g.setColor(color);
            return;
        }else {
            shapeName =  btn_action;
        }
        switch(shapeName) {
            case"新建":
                Main.addDrawBoard();
                break;
            case "撤回":
                if (!shapeList.isEmpty()) {
                    shapeList.remove(shapeList.size() - 1);
                    drawJPanel.paint(g);
                }
                break;
            case "打开" :
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter (
                        "JPG & GIF Images", "jpg", "gif");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    System.out.println("You chose to open this file: " +
                            chooser.getSelectedFile().getPath());
                    fileName = chooser.getSelectedFile().getPath();
                }
                BufferedImage bufferedImage = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
                Graphics buffg = bufferedImage.getGraphics();
                int[][] img = getImagePixel(fileName);
                drawImage(buffg,img);
                ImageShape imageShape = new ImageShape();
                imageShape.setBufferedImage(bufferedImage);
                g.drawImage(bufferedImage,0,0,null);
                shapeList.add(imageShape);

                File file = new File(fileName);
                BufferedImage bi = null;
                try {
                    bi = ImageIO.read(file);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                imageArray.add(bi);

                break;
            case "保存":
                JFileChooser chooser2 = new JFileChooser();
                FileNameExtensionFilter filter2 = new FileNameExtensionFilter(
                        "JPG & GIF Images", "jpg","gif"
                );
                chooser2.setFileFilter(filter2);
                int returnVal2 = chooser2.showSaveDialog(null);
                if(returnVal2 == JFileChooser.APPROVE_OPTION){
                    System.out.println("You choose to save this file:" +
                            chooser2.getSelectedFile().getPath());
                }
                BufferedImage bufferedImage2 = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
                Graphics buffg2 = bufferedImage2.getGraphics();
                for(Shapes shape : shapeList ){
                    shape.drawShape(buffg2);
                }
                File file2 = new File(chooser2.getSelectedFile().getPath());
                try {
                    ImageIO.write(bufferedImage2,"png",file2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case "清空" :
                shapeList.clear();
                drawJPanel.paint(g);
                break;
            case "文字" ://todo 文字
//                System.out.println("text released now");
//                JTextField textArea = new JTextField("please input!");
//
//                textArea.setBounds(x2,y2,150,20);//
//                shapeList.add(new Shapes("文字"));
//                drawJPanel.add(textArea);
                break;
            case "普通":
                aFont = new Font("1",Font.PLAIN,10);
                this.color = Color.BLACK;
                break;
            case "样式1" :
                aFont = new Font("2",Font.BOLD,12);
                this.textColor = Color.red;
                System.out.println(" 样式1");
                break;
            case "斜体" :
                aFont = new Font("3",Font.ITALIC,10);
                this.color = Color.BLACK;
                System.out.println("斜体");
                break;
            case "样式2" :
                aFont = new Font("4",Font.ITALIC,14);
                this.textColor =Color.yellow;
                System.out.println("样式2");
                break;
            case "原图":
                BufferedImage bufferedImage5 = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
                Graphics buffg5 = bufferedImage5.getGraphics();
                int[][] img5 = getImagePixel(fileName);
                drawImage(buffg5,img5);
                ImageShape imageShape5 = new ImageShape();
                imageShape5.setBufferedImage(bufferedImage5);
                g.drawImage(bufferedImage5,0,0,null);
                shapeList.add(imageShape5);
                break;
            case "马赛克":
                BufferedImage bufferedImage3 = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
                Graphics buffg3 = bufferedImage3.getGraphics();
                int[][] img3 = getImagePixel(fileName);
                drawImage_MSK(buffg3,img3);

                ImageShape imageShape3 = new ImageShape();
                imageShape3.setBufferedImage(bufferedImage3);
                g.drawImage(bufferedImage3,0,0,null);
                shapeList.add(imageShape3);
                break;
            case "灰度":
                BufferedImage bufferedImage6 = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
                Graphics buffg6 = bufferedImage6.getGraphics();
                int[][] img6 = getImagePixel(fileName);
                drawImage_gray(buffg6,img6);
                ImageShape imageShape6 = new ImageShape();
                imageShape6.setBufferedImage(bufferedImage6);
                g.drawImage(bufferedImage6,0,0,null);
                shapeList.add(imageShape6);
                break;
            case "二值化":
                BufferedImage bufferedImage7 = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
                Graphics buffg7 = bufferedImage7.getGraphics();
                int[][] img7 = getImagePixel(fileName);
                drawImage_binary(buffg7,img7);
                ImageShape imageShape7 = new ImageShape();
                imageShape7.setBufferedImage(bufferedImage7);
                g.drawImage(bufferedImage7,0,0,null);
                shapeList.add(imageShape7);
                break;
            case "背景替换":
                BufferedImage bufferedImage8 = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
                Graphics buffg8 = bufferedImage8.getGraphics();
                int[][] img8 = getImagePixel(fileName);
                int[][] background = getImagePixel("C:\\Users\\13630\\Desktop\\背景.jpg");
                drawImage_replaceBackground(buffg8,img8,background);
                ImageShape imageShape8 = new ImageShape();
                imageShape8.setBufferedImage(bufferedImage8);
                g.drawImage(bufferedImage8,0,0,null);
                shapeList.add(imageShape8);
                break;
            case "油画":
                BufferedImage bufferedImage9 = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
                Graphics buffg9 = bufferedImage9.getGraphics();
                int[][] img9 = getImagePixel(fileName);
                drawImage_OilPainting(buffg9,img9);
                ImageShape imageShape9 = new ImageShape();
                imageShape9.setBufferedImage(bufferedImage9);
                g.drawImage(bufferedImage9,0,0,null);
                shapeList.add(imageShape9);
                break;
            case "图片融合":
                BufferedImage bufferedImage10 = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
                Graphics buffg10 = bufferedImage10.getGraphics();
                int[][] img10 = getImagePixel(fileName);
                int[][] background2 = getImagePixel("C:\\Users\\13630\\Desktop\\背景.jpg");
                drawImage_fusion(buffg10,img10,background2);
                ImageShape imageShape10 = new ImageShape();
                imageShape10.setBufferedImage(bufferedImage10);
                g.drawImage(bufferedImage10,0,0,null);
                shapeList.add(imageShape10);
                break;
            case "磨皮":
                img11 = getImagePixel(fileName);
                break;
            case "向右旋转":
                BufferedImage bufferedImage15 = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
                int[][] img15 = getImagePixel(fileName);
                img15 = RotateRight(img15);
                Graphics buffg15 = bufferedImage15.getGraphics();
                drawImage(buffg15,img15);
                g.drawImage(bufferedImage15,0,0,null);
                ImageShape imageShape15 = new ImageShape();
                imageShape15.setBufferedImage(bufferedImage15);
                shapeList.add(imageShape15);
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x3 = e.getX();
        y3 = e.getY();
        if(change_flag == 1){
//            AllShapes allShapes = new AllShapes();
//            allShapes.addNewShape(x2,y2);

//            int n = shapeList.size();
//            allShapes.withDraw(n-1);


//            withDrawTest(n-1);
//            addNewShape(x2,y2);

            if(this.toMove != null) {
                toMove.changeLocation(x2, y2, x3, y3);
                if (toMoveNum != -1) {
//                shapeList.remove(toMoveNum);
                    toMoveNum = -1;
                }
                shapeList.add(toMove);
                drawJPanel.paint(g);
//            toMove.drawShape(g);
////            if (!shapeList.isEmpty()) {
////                shapeList.remove(shapeList.size() - 1);
////                drawJPanel.paint(g);
////            }
            }
            return;
        }
        if(shapeName == null) return;
        switch (shapeName) {
            case "直线", "矩形", "椭圆", "圆", "谢尔宾斯基地毯", "实心矩形", "实心圆", "等腰三角形", "立方体", "橡皮擦" -> {
                BasicShape basicShape = new BasicShape(shapeName, new Color(color.getRGB()), x2, y2, x3, y3);
                basicShape.drawShape(g);
                shapeList.add(basicShape);
            }
            case "递归KLine" -> {
                KLine(x2, y2, x3, y3, Math.abs(y3 - y2));
                specialList.add(new Point(x3, y3));
                SpecialShape specialShape = new SpecialShape(shapeName, new Color(color.getRGB()), specialList);
                shapeList.add(specialShape);
                specialList.clear();
            }
            case "签字笔", "任意线" -> {
                SpecialShape specialShape2 = new SpecialShape(shapeName, new Color(color.getRGB()), specialList);
                shapeList.add(specialShape2);
                specialList.clear();
                Pen.state = 1;
            }
            case "实时直线" -> {
                specialList.add(new Point(x3, y3));
                SpecialShape specialShape3 = new SpecialShape(shapeName, new Color(color.getRGB()), specialList);
                shapeList.add(specialShape3);
                specialList.clear();
            }
            case "磨皮" -> {
                SpecialShape specialShape4 = new SpecialShape(shapeName, new Color(color.getRGB()), specialList);
                shapeList.add(specialShape4);
                specialList.clear();
                SkinGrinding.state = 1;
            }
//            case "文字"://todo 文字
//                System.out.println("text released now");
//                JLabel textLabel = new JLabel("test!");
//                shapeList.add(new Shapes("文字"));
//                drawJPanel.add(textLabel);
//                break;
        }


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        if(change_flag == 1){
            int i = 0;
            for(Shapes ashape:shapeList){
                if(ashape.isIn(x2,y2)){
                    System.out.println("BasicShape is chosen !!!");
                    toMove = (BasicShape) ashape;
                    toMoveNum = i;
                    //ywb
                }
                i++;
            }
        }
        if(shapeName == null) return;
        switch(shapeName){
            case "三角形" :
                new Triangle().drawTriangle(e,g,specialList,color,shapeList);
                break;
            case "多边形":
                new Polygon().drawPolygon(e,g,specialList,color,shapeList);
                break;
            case "改进多边形":
                if(e.getButton()==3){

                    new PolygonPro().drawPolygonPro(list,g,specialList,color,shapeList);
                    SpecialShape specialShape = new SpecialShape("改进多边形",color,specialList);
                    shapeList.add(specialShape);
                    specialList.clear();
                    list.clear();
                    break;
                }else{
                    Point point = new Point(e.getX(),e.getY());
                    list.add(point);
                    break;
                }
            default:
                break;
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(change_flag == 1){
            return;
        }
        if(shapeName == null) return;
        switch (shapeName){
            case "实时直线":
                new RealLine().draw(e,g,specialList,color,shapeList);
                break;
            case "签字笔":
            case "任意线":
                new Pen().draw(e,g,specialList,color,shapeList);
                break;

            case "磨皮":
                int w = (drawJPanel.getWidth()- img11.length)/2;
                int h = (drawJPanel.getHeight()- img11[0].length)/2;
                new SkinGrinding().draw(e,g2D,specialList,img11,shapeList,w,h);
                break;
            case "文字"://todo 文字放在这
//                System.out.println("text released now");
                JTextField textArea = new JTextField("please input!");
                textArea.setBounds(x3,y3,150,20);//
                textArea.setFont(this.aFont);  //添加文字样式
                textArea.setForeground(this.textColor);
//                shapeList.remove(shapeList.size() - 1);
//                shapeList.add(new Shapes("文字"));

                drawJPanel.removeAll();//只能添加一个  todo
                drawJPanel.add(textArea);
                drawJPanel.paint(g);
                break;
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        getsourceLocaiton();
        x2 = e.getX();
        y2 = e.getY();
        if(change_flag == 1){
            return;
        }
        if(shapeName == null) return;
        switch (shapeName){
            case "实时直线":
                specialList.add(new Point(x2,y2));
                RealLine.x1 = e.getX();
                RealLine.y1 = e.getY();
                RealLine.x2 = 0;
                break;
            case "磨皮":
                g2D = (Graphics2D)g;
                g2D.setStroke (new BasicStroke (3));
                specialList.add(new Point(x2,y2));
                break;
        }

        if(change_flag == 1){
            int i = 0;
            for(Shapes ashape:shapeList){
                if(ashape.isIn(x2,y2)){
                    System.out.println("BasicShape is chosen !!!");
                    toMove = (BasicShape) ashape;
                    toMoveNum = i;
                    //ywb
                    break;//只选一个
                }
                i++;
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        JSlider jSlider = (JSlider)e.getSource();
        String s = jSlider.getToolTipText();
        switch (s){
            case "缩放比例":
                multiple =  jSlider.getValue();
                int[][] img = getImagePixel(fileName);
                BufferedImage bufferedImage = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
                Graphics buffg = bufferedImage.getGraphics();
                drawImage_multiple(buffg,img);
                g.drawImage(bufferedImage,0,0,null);
                break;
            case "红色":
                multipleRed =  jSlider.getValue();
                BufferedImage bufferedImage1 = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
                Graphics buffg1 = bufferedImage1.getGraphics();
                drawImage_multiple_color(buffg1,imageArray);

                g.drawImage(bufferedImage1,0,0,null);
                break;
            case "绿色":
                multipleGreen =  jSlider.getValue();
                BufferedImage bufferedImage2 = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
                Graphics buffg2 = bufferedImage2.getGraphics();
                drawImage_multiple_color(buffg2,imageArray );
                g.drawImage(bufferedImage2,0,0,null);
                break;
            case "蓝色":
                multipleBlue =  jSlider.getValue();
                BufferedImage bufferedImage3 = new BufferedImage(800,800,BufferedImage.TYPE_INT_ARGB);
                Graphics buffg3 = bufferedImage3.getGraphics();
                drawImage_multiple_color(buffg3,imageArray);
                g.drawImage(bufferedImage3,0,0,null);
                break;
        }

    }

    public void drawImage_multiple_color(Graphics g , ImageArray imageArray){
        int index = imageArray.getSize()-1;
        int w = (drawJPanel.getWidth()- imageArray.get(index).getWidth())/2;
        int h = (drawJPanel.getHeight()- imageArray.get(index).getHeight())/2;
        int[][] red  ;
        int[][] green;
        int[][] blue ;

        red =   imageArray.multiple(multipleRed,imageArray.redArray[index]);
        green = imageArray.multiple(multipleGreen,imageArray.greenArray[index]);
        blue =  imageArray.multiple(multipleBlue,imageArray.blueArray[index]);
        for (int i = 0; i < imageArray.get(index).getWidth(); i++) {
            for (int j = 0; j < imageArray.get(index).getHeight() ; j++) {
                g.setColor(new Color(red[i][j],green[i][j],blue[i][j]));
                g.drawRect(i+w,j+h,1,1);
            }
        }
    }


    public void drawImage_multiple(Graphics g , int[][] img){
        int w = (int)((drawJPanel.getWidth()- img.length*1.0*(multiple)/100)/2);
        int h = (int)((drawJPanel.getHeight()- img[0].length*1.0*multiple/100)/2);
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {
                g.setColor(new Color(img[i][j]));
                for (int k = (int)(i*1.0*multiple/100); k < (int)((i+1)*1.0*multiple/100) ; k++) {
                    for (int l = (int)(1.0*j*multiple/100); l < (int)((j+1)*1.0*multiple/100); l++) {
                        g.drawRect(k+w,l+h,1,1);
                    }
                }
            }
        }
    }
    //递归KLine
    public void KLine(int x1 , int y1 , int x2 , int y2, int x){
        if(Math.abs(x2-x1)<= 1 || Math.abs(y2-y1) <= 1 || x < 1){
            g.drawLine(x1, y1, x2, y2);
            specialList.add(new Point(x1,y1));

            return;
        }
        Random random = new Random(0);
        int ran = random.nextInt(x);
        int mid = ((y2+y1)/2-x+ran*2);
        x = (int)(x*0.618);
        KLine(x1, y1, (x1+x2)/2, mid,x);
        KLine((x1+x2)/2, mid, x2,y2,x);

    }

    //谢尔宾斯基地毯
    public void Sierpinski(int x,int y,int w,int h){
        if(w>0&&h>0){
            g.fillRect(x+w/3,y+h/3,w/3,h/3);
            Sierpinski(x,y,w/3,h/3);
            Sierpinski(x+w/3,y,w/3,h/3);
            Sierpinski(x+2*w/3,y,w/3,h/3);
            Sierpinski(x,y+h/3,w/3,h/3);
            Sierpinski(x+2*w/3,y+h/3,w/3,h/3);
            Sierpinski(x,y+2*h/3,w/3,h/3);
            Sierpinski(x+w/3,y+2*h/3,w/3,h/3);
            Sierpinski(x+2*w/3,y+2*h/3,w/3,h/3);
        }

    }
    public  void drawImage(Graphics g ,int[][] img){

        int w = (drawJPanel.getWidth()- img.length)/2;
        int h = (drawJPanel.getHeight()- img[0].length)/2;
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {
                Color c = new Color(img[i][j]);
                g.setColor(c);
                g.drawOval(w+i , h+j, 1, 1);
            }
        }
    }
    private int[][] getImagePixel(String filePath) {

        File file = new File(filePath);
        BufferedImage bi = null;
        try{
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = bi.getWidth();
        int h = bi.getHeight();
        int[][] imIndex = new int[w][h];

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int pixel = bi.getRGB(i,j);
                imIndex[i][j] = pixel;
            }
        }
        return imIndex;
    }
    public  void drawImage_fusion(Graphics g ,int[][] img,int[][] background){
        int a = (drawJPanel.getWidth()- img.length)/2;
        int b = (drawJPanel.getHeight()- img[0].length)/2;
        int w = Math.min(img.length, background.length);
        int h = Math.min(img[0].length, background[0].length);
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Color ca = new Color(img[i][j]);
                Color cb = new Color(background[i][j]);
                int red = (int) (ca.getRed()*0.7+ cb.getRed()*0.3);
                int green = (int)(ca.getGreen()* 0.3+cb.getGreen()*0.7);
                int blue = (int)(ca.getBlue()*0.3+ cb.getBlue()*0.7);
                Color c = new Color(red,green,blue);
                g.setColor(c);
                g.fillRect(i+a , j+b, 1, 1);
            }
        }
    }
    public  void drawImage_OilPainting(Graphics g ,int[][] img){
        int w = (drawJPanel.getWidth()- img.length)/2;
        int h = (drawJPanel.getHeight()- img[0].length)/2;
        for (int i = 0; i < img.length; i+=5) {
            for (int j = 0; j < img[i].length; j+=5) {

                g.setColor(new Color(img[i][j]));
                Random random = new Random();
                int ran = random.nextInt(20)+5;
                g.fillOval(i+w , j+h, ran, ran);
            }
        }
    }
    public  void drawImage_replaceBackground(Graphics g ,int[][] img,int[][] background){
        int w = (drawJPanel.getWidth()- img.length)/2;
        int h = (drawJPanel.getHeight()- img[0].length)/2;
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {
                int value = img[i][j];
                int red = (value>>16) & 0xff;
                int green = (value>>8) & 0xff;
                int blue = value & 0xff;
                int gray = (int) (0.3 * red + 0.59 * green + 0.11 * blue);
                if(gray > 240&&i< background.length&&j<background[i].length){
                    g.setColor(new Color(background[i][j]));
                }else {
                    g.setColor(new Color(img[i][j]));
                    //g.setColor(Color.white);
                }
                g.fillRect(i+w , j+h, 1, 1);
            }
        }
    }
    public  void drawImage_binary(Graphics g ,int[][] img){
        int w = (drawJPanel.getWidth()- img.length)/2;
        int h = (drawJPanel.getHeight()- img[0].length)/2;
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {
                int value = img[i][j];
                int red = (value>>16) & 0xff;
                int green = (value>>8) & 0xff;
                int blue = value & 0xff;
                int gray = (int) (0.3 * red + 0.59 * green + 0.11 * blue);
                if(gray < 150){
                    g.setColor(Color.black);
                }else {
                    g.setColor(Color.white);
                }
                g.fillRect(i+w , j+h, 1, 1);
            }
        }
    }
    public  void drawImage_gray(Graphics g ,int[][] img){
        int w = (drawJPanel.getWidth()- img.length)/2;
        int h = (drawJPanel.getHeight()- img[0].length)/2;
        for (int i = 0; i < img.length; i++) {
            for (int j = 0; j < img[i].length; j++) {
                int value = img[i][j];
                int red = (value>>16) & 0xff;
                int green = (value>>8) & 0xff;
                int blue = value & 0xff;
                int gray = (int) (0.3 * red + 0.59 * green + 0.11 * blue);
                Color c = new Color(gray,gray,gray);
                g.setColor(c);
                g.fillRect(i+w , j+h, 1, 1);
            }
        }
    }

    public  void drawImage_MSK(Graphics g ,int[][] img){
        int w = (drawJPanel.getWidth()- img.length)/2;
        int h = (drawJPanel.getHeight()- img[0].length)/2;
        for (int i = 0; i < img.length; i+=8) {
            for (int j = 0; j < img[i].length; j+=8) {
                Color c = new Color(img[i][j]);
                g.setColor(c);
                g.fillRect(i+w , j+h, 8, 8);
            }
        }
    }
    public  int[][] RotateRight(int[][] img){
        int w = img.length;
        int h = img[0].length;
        int[][] newImg = new int[h][w];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                newImg[h-j-1][w-i-1] = img[i][j];
            }
        }
        return newImg;
    }

    public void withDrawTest(int index){
        if (!shapeList.isEmpty()) {
            shapeList.remove(index);
            drawJPanel.paint(g);
        }
    }

    public void addNewShape (int a, int b){
        int i = 0;
        for(Shapes ashape:shapeList){
            if(ashape.isIn(a,b)){
                int i2 = shapeList.indexOf(ashape);
                System.out.println("BasicShape is chosen !!!");
                toMoveNum = i2;
                shapeName =ashape.shapeName;//得到选中的图形的名称
                BasicShape basicShape = new BasicShape(shapeName, new Color(color.getRGB()), xs1-x2+x3, ys1-y2+y3, xs2-x2+x3, ys2-y2+y3);

                withDrawTest(i2);

                basicShape.drawShape(g);
                shapeList.add(basicShape);
                //ywb
            }
            i++;
        }
        System.out.println("add New Shape!");
    }
    public void getsourceLocaiton(){
        xs1 = x2;
        xs2 = x3;
        ys1 = y2;
        ys2 = y3;
    }
}

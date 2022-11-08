package drawBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DrawUI extends JFrame {
    public static DrawListener allListener = new DrawListener();
    public void addMenuItem(JMenuBar jMenuBar){
        JMenu jMenu = new JMenu("菜单",true);
        String[] Menu = {"撤回","打开","保存","清空"};//todo 第一个改为撤销
        for(String s : Menu){
            JMenuItem jMenuItem = new JMenuItem(s);
            jMenu.add(jMenuItem);
            jMenuItem.addActionListener(allListener);
        }
        JMenu fileMenu = new JMenu("文件");
        String[] strs = {"新建","打开","保存"};
        for(String s : strs){
            JMenuItem jMenuItem = new JMenuItem(s);
            fileMenu.add(jMenuItem);
            jMenuItem.addActionListener(allListener);
        }
        JMenu paintMenu = new JMenu("添加");
        String[] strs2 = {"任意线","基本图形"};
        JMenuItem jMenuItem1;
        JMenu jMenuItem2;
        jMenuItem1 = new JMenuItem("任意线");
        jMenuItem1.addActionListener(allListener);
        paintMenu.add(jMenuItem1);
        jMenuItem2 = new JMenu("基本图形");
        jMenuItem2.addActionListener(allListener);

        JMenuItem spItem = new JMenuItem("直线");
        spItem.addActionListener(allListener);
        JMenuItem recItem = new JMenuItem("矩形");
        recItem.addActionListener(allListener);
        JMenuItem cicItem = new JMenuItem("圆");
        cicItem.addActionListener(allListener);
        JMenuItem ellItem = new JMenuItem("椭圆");
        ellItem.addActionListener(allListener);
        jMenuItem2.add(spItem);
        jMenuItem2.add(recItem);
        jMenuItem2.add(cicItem);
        jMenuItem2.add(ellItem);
        paintMenu.add(jMenuItem2);
        JMenuItem textMenu = new JMenuItem("文字");
        textMenu.addActionListener(allListener);
        paintMenu.add(textMenu);

        JMenu colorMenu = new JMenu("设定");
        JMenuItem colorMenuItem = new JMenuItem("颜色");
        JMenu textMenuItem = new JMenu("文字风格");
        JRadioButton plainSet = new JRadioButton("普通");
        JRadioButton boldSet = new JRadioButton("样式1");//字体为12磅,颜色为红色,加粗
        JRadioButton italicSet = new JRadioButton("斜体");
        JRadioButton boldItalicSte = new JRadioButton("样式2");//字体为14磅,颜色为黄色,斜体
        plainSet.addActionListener(allListener);
        boldSet.addActionListener(allListener);
        italicSet.addActionListener(allListener);
        boldItalicSte.addActionListener(allListener);
        colorMenuItem.addActionListener(allListener);
        textMenuItem.addActionListener(allListener);
        textMenuItem.add(plainSet);
        textMenuItem.add(italicSet);
        textMenuItem.add(boldSet);
        textMenuItem.add(boldItalicSte);
        colorMenu.add(colorMenuItem);
        colorMenu.add(textMenuItem);
        JRadioButton selectButton = new JRadioButton("移动");
//        selectButton.addActionListener(allListener);

        selectButton.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                JRadioButton temp = (JRadioButton) e.getSource();
                if (temp.isSelected()) {
                    System.out.println("change_flag == 1");
            DrawListener.change_flag = 1;
//                    AllShapes allShapes = new AllShapes();
//                    int x2 = allListener.x2;
//                    int y2 = allListener.y2;
//            allShapes.addNewShape(x2,y2);
//                    int n = shapeList.size();
//                    allShapes.withDraw(n-1);
                } else {
                    System.out.println("change_flag == 0");
                    DrawListener.change_flag = 0;
                }

            }});//匿名内部类添加监听器
        selectButton.addActionListener(allListener);

        jMenuBar.add(jMenu);
        jMenuBar.add(fileMenu);
        jMenuBar.add(paintMenu);
        jMenuBar.add(colorMenu);
        jMenuBar.add(selectButton);

    }//todo 构建菜单栏


    String[] strs = {"直线","签字笔","任意线","矩形", "圆","椭圆","文字","橡皮擦", "撤回", "保存", "打开"};//todo 第二个改为任意线
    Color[] color = {Color.red,Color.yellow,Color.black,Color.blue};
    public void addShapeButton(JComponent component){
        for(String str : strs){
            JButton btn = new JButton(str);
            btn.addActionListener(allListener);
            component.add(btn);
        }
//        JTextField xinyuan = new JTextField("NEU欢迎你!");
//        component.add(xinyuan);
    }
    public void addColorButton(JComponent component){
        Dimension dim = new Dimension(30,30);
        for(Color c : color){
            JButton btn = new JButton();
            btn.setBackground(c);
            btn.setPreferredSize(dim);
            btn.addActionListener(allListener);
            component.add(btn);
        }
        Dimension dim2 = new Dimension(95,30);
        JButton btn = new JButton("选择颜色...");
        btn.setPreferredSize(dim2);
        btn.addActionListener(allListener);
        component.add(btn);
        JButton btn3 = new JButton("更多操作...");
        btn3.setPreferredSize(dim2);
        btn3.addActionListener(allListener);
        component.add(btn3);
    }
    public void addBeautyButton(JComponent component){
        String[] str = {"原图","马赛克","灰度","二值化","背景替换","油画","图片融合","磨皮"};
        for(String s : str){
            JButton btn = new JButton(s);
            btn.addActionListener(allListener);
            component.add(btn);
        }
    }

    public void initUI(){
        JFrame jf = new JFrame("画图板");
        jf.setTitle("画图板");
        jf.setLayout(new BorderLayout());
        jf.setSize(1500,927);  //黄金分割比
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //菜单栏
        JMenuBar jMenuBar = new JMenuBar();

        addMenuItem(jMenuBar);

        jf.setJMenuBar(jMenuBar);

        JPanel shapeChooserPanel = new JPanel();
        DrawJPanel drawPanel = new DrawJPanel();
        JPanel ChooserPanel = new JPanel();
        JPanel ColorChooserPanel = new JPanel();
        ChooserPanel.setLayout(new BorderLayout());
        allListener.drawJPanel = drawPanel;
        JPanel RightPanel = new JPanel();
        //大小
        Dimension dim = new Dimension(150,80);//按钮区域
        shapeChooserPanel.setPreferredSize(dim);
        ChooserPanel.setPreferredSize(dim);
        Dimension dim2 = new Dimension(150,330);
        RightPanel.setPreferredSize(dim2);
        ColorChooserPanel.setPreferredSize(dim2);
        ChooserPanel.setPreferredSize(dim2);
        //背景颜色
        Color color1 = new Color(-3355444);
        shapeChooserPanel.setBackground(color1);
        Color color2 = new Color(-6710887);
        ColorChooserPanel.setBackground(color2);
        ChooserPanel.setBackground(color2);
        RightPanel.setBackground(color1);
        //方位
        jf.add(shapeChooserPanel,BorderLayout.NORTH);
        jf.add(ChooserPanel,BorderLayout.EAST);
        jf.add(drawPanel,BorderLayout.CENTER);
        ChooserPanel.add(RightPanel,BorderLayout.SOUTH);
        ChooserPanel.add(ColorChooserPanel,BorderLayout.NORTH);

        //添加按钮
        addShapeButton(shapeChooserPanel);
        addColorButton(ColorChooserPanel);
        addBeautyButton(RightPanel);
        jf.setVisible(true);
        Graphics g = drawPanel.getGraphics ();
        drawPanel.addMouseMotionListener(allListener);
        drawPanel.addMouseListener(allListener);
        drawPanel.setDl(allListener);
        allListener.setG(g);

    }
}


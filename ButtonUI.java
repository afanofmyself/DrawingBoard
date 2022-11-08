package drawBoard;

import javax.swing.*;
import java.awt.*;

public class ButtonUI extends JFrame {
    public static DrawUI drawUI;
    public void init (){
        JFrame jf = new JFrame();
        jf.setTitle("更多操作");
        jf.setSize(380,500);
        jf.setLocationRelativeTo(drawUI);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setLayout(new FlowLayout());
        addJSlider(jf);
        addButton(jf);
        addJSlider2(jf);
        jf.setVisible(true);

    }
    public void addButton (JFrame component){
        String[] strings = {"放大130%","缩小50%","向左旋转","向右旋转"};
        for(String s : strings){
            JButton btn = new JButton(s);
            component.add(btn);
            btn.addActionListener(DrawUI.allListener);
        }

    }
    public void addJSlider(JFrame component){
        JLabel jl = new JLabel("缩放比例（%）：");
        JSlider jSlider = new JSlider(0,200);
        jSlider.setToolTipText("缩放比例");
        jSlider.setMajorTickSpacing(30);
        jSlider.setMinorTickSpacing(10);
        jSlider.setPaintLabels(true);
        jSlider.setPaintTicks(true);
        jSlider.addChangeListener(DrawUI.allListener);
        component.add(jl);
        component.add(jSlider);

    }
    public void addJSlider2(JFrame component){

        JLabel jl1 = new JLabel("红色亮度（%）：");
        JSlider jSlider1 = new JSlider(0,0,200,100);
        jSlider1.setToolTipText("红色");
        jSlider1.setMajorTickSpacing(30);
        jSlider1.setMinorTickSpacing(10);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.addChangeListener(DrawUI.allListener);
        component.add(jl1);
        component.add(jSlider1);

        JLabel jl2 = new JLabel("绿色亮度（%）：");
        JSlider jSlider2 = new JSlider(0,0,200,100);
        jSlider2.setToolTipText("绿色");
        jSlider2.setMajorTickSpacing(30);
        jSlider2.setMinorTickSpacing(10);
        jSlider2.setPaintLabels(true);
        jSlider2.setPaintTicks(true);
        jSlider2.addChangeListener(DrawUI.allListener);
        component.add(jl2);
        component.add(jSlider2);

        JLabel jl3 = new JLabel("蓝色亮度（%）：");
        JSlider jSlider3 = new JSlider(0,0,200,100);
        jSlider3.setToolTipText("蓝色");
        jSlider3.setMajorTickSpacing(30);
        jSlider3.setMinorTickSpacing(10);
        jSlider3.setPaintLabels(true);
        jSlider3.setPaintTicks(true);
        jSlider3.addChangeListener(DrawUI.allListener);
        component.add(jl3);
        component.add(jSlider3);

        //确认和取消按钮；
        JButton btn1 = new JButton("确认");
        btn1.addActionListener(DrawUI.allListener);
        component.add(btn1);

        JButton btn2 = new JButton("取消");
        btn2.addActionListener(DrawUI.allListener);
        component.add(btn2);

    }

//    public static void main(String[] args) {
//        new ButtonUI().init();
//    }
}

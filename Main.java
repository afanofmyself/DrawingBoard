package drawBoard;

import java.util.ArrayList;

public class Main {
    static ArrayList<DrawUI> drawingBoards = new ArrayList<>();
    public static void addDrawBoard(){
        DrawUI aUI = new DrawUI();
        aUI.initUI();
        drawingBoards.add(aUI);
        System.out.println("New drawing board to create success!");
    }
    public static void main(String[] args) {
        addDrawBoard();
    }
}

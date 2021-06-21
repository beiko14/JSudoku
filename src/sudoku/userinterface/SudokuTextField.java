package sudoku.userinterface;

import javafx.scene.control.TextField;

public class SudokuTextField extends TextField {
    private final int x;
    private final int y;

    public SudokuTextField(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // this Override-Methods is important to that the number input from the user doesn't trigger
    // some strange behaviors and that the program runs properly
    @Override
    public void replaceText(int i, int i1, String s){
        if (!s.matches("[0-9]")){
            super.replaceText(i, i1, s);
        }
    }

    // this Override-Methods is important to that the number input from the user doesn't trigger
    // some strange behaviors and that the program runs properly
    @Override
    public void replaceSelection(String s){
        if (!s.matches("[0-9]")){
            super.replaceSelection(s);
        }
    }
}

package sudoku.computationlogic;

import sudoku.constants.GameState;
import sudoku.problemdomain.SudokuGame;

import static sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

// rules to determine if the game is valid or not
public class GameLogic {

    public static SudokuGame getNewGame(){
        return new SudokuGame(
                GameState.NEW,
                GameGenerator.getNewGameGrid()
        );
    }

    public static GameState checkForCompletion(int[][] grid){
        if(sudokuIsInvalid(grid)){
            return GameState.ACTIVE;
        }
        if(tilesAreNotFilled(grid)){
            return GameState.ACTIVE;
        }
        return GameState.COMPLETE;
    }





}

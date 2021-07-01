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

    private static boolean sudokuIsInvalid(int[][] grid) {
        if(rowsAreInvalid(grid)){
            return true;
        }
        if(columnsAreInvalid(grid)){
            return true;
        }
        if(squaresAreInvalid(grid)){
            return true;
        } else{
            return false;
        }
    }

    private static boolean tilesAreNotFilled(int[][] grid) {
        for(int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++){
            for(int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++){
                if(grid[xIndex][yIndex] == 0){
                    return true;
                }
            }
        }
        return false;
    }



}

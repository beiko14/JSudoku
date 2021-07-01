package sudoku.computationlogic;

import sudoku.constants.GameState;
import sudoku.constants.Rows;
import sudoku.problemdomain.SudokuGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    public static boolean sudokuIsInvalid(int[][] grid) {
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

    // check every row in the grid and check if there are any repeated numbers
    private static boolean rowsAreInvalid(int[][] grid) {
        for(int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++){
            List<Integer> row = new ArrayList<>();
            for(int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++){
                row.add(grid[xIndex][yIndex]);
            }
            if(collectionHasRepeats(row)){
                return true;
            }
        }
        return false;
    }

    // check every column in the grid and check if there are any repeated numbers
    private static boolean columnsAreInvalid(int[][] grid) {
        for(int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++){
            List<Integer> column = new ArrayList<>();
            for(int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++){
                column.add(grid[xIndex][yIndex]);
            }
            if(collectionHasRepeats(column)){
                return true;
            }
        }
        return false;
    }

    private static boolean squaresAreInvalid(int[][] grid) {
        if(rowOfSquaresIsInvalid(Rows.TOP, grid)){
            return true;
        }
        if(rowOfSquaresIsInvalid(Rows.MIDDLE, grid)){
            return true;
        }
        if(rowOfSquaresIsInvalid(Rows.BOTTOM, grid)){
            return true;
        }
        return false;
    }

    private static boolean rowOfSquaresIsInvalid(Rows value, int[][] grid) {
        switch(value){
            case TOP:
                //check first, second and third square in the TOP row
                if(squareIsValid(0, 0, grid)){
                    return true;
                }
                if(squareIsValid(3, 0, grid)){
                    return true;
                }
                if(squareIsValid(6, 0, grid)){
                    return true;
                }
                return false;

            case MIDDLE:
                //check first, second and third square in the MIDDLE row
                if(squareIsValid(0, 3, grid)){
                    return true;
                }
                if(squareIsValid(3, 3, grid)){
                    return true;
                }
                if(squareIsValid(6, 3, grid)){
                    return true;
                }
                return false;

            case BOTTOM:
                //check first, second and third square in the BOTTOM row
                if(squareIsValid(0, 6, grid)){
                    return true;
                }
                if(squareIsValid(3, 6, grid)){
                    return true;
                }
                if(squareIsValid(6, 6, grid)){
                    return true;
                }
                return false;
                
            default:
                return false;
        }
    }

    // look at each square individually
    // check if anywhere in the square is a repeated number except 0
    private static boolean collectionHasRepeats(List<Integer> collection) {
        for(int index = 1; index <= GRID_BOUNDARY; index++){
            if(Collections.frequency(collection, index) > 1){
                return true;
            }
        }
        return false;
    }

    // pick for example the top 3 squares, add them to a list and pass that list to collectionHasRepeats
    private static boolean squareIsValid(int xIndex, int yIndex, int[][] grid) {
        int xIndexEnd = yIndex + 3;
        int yIndexEnd = xIndex + 3;

        List<Integer> square = new ArrayList<>();

        while(yIndex < yIndexEnd){
            while(xIndex < xIndexEnd){
                square.add(
                        grid[xIndex][yIndex]
                );

                xIndex++;
            }
            xIndex -= 3;
            yIndex++;
        }
        if(collectionHasRepeats(square)){
            return true;
        }
        return false;
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

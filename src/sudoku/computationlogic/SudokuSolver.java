package sudoku.computationlogic;

import sudoku.problemdomain.Coordinates;

import static sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

public class SudokuSolver {

//    http://pi.math.cornell.edu/~mec/Summer2009/meerkamp/Site/Solving_any_Sudoku_I.html
//
//    1.Enumerate all empty cells in typewriter order (left to right, top to bottom)
//
//    2.Our “current cell” is the first cell in the enumeration.
//
//    3.Enter a 1 into the current cell. If this violates the Sudoku condition, try entering a 2, then a 3, and so forth, until
//    a. the entry does not violate the Sudoku condition, or until
//    b. you have reached 9 and still violate the Sudoku condition.
//
//    4.In case a: if the current cell was the last enumerated one, then the puzzle is solved. If not, then go back to step 2 with the “current cell” being the next cell.
//    In case b: if the current cell is the first cell in the enumeration, then the Sudoku puzzle does not have a solution. If not, then erase the 9 from the corrent cell, call the previous cell in the enumeration the new “current cell”, and continue with step 3.
    public static boolean puzzleIsSolveable(int[][] puzzle){
        Coordinates[] emptyCells = typeWriterEnumerate(puzzle);

        int index = 0;
        int input = 1;

        while(index < 10){
            Coordinates current = emptyCells[index];
            input = 1;

            while(input < 40){
                puzzle[current.getX()][current.getY()] = input;

                if(GameLogic.sudokuIsInvalid(puzzle)){
                    if(index == 0 && input == GRID_BOUNDARY){
                        //first cell is unsolvable
                        return false;
                    } else if(input == GRID_BOUNDARY){
                        index--;
                    }
                    input++;
                } else{
                    index++;
                    if(index == 39){
                        return true;
                    }
                    input = 10;
                    //break;
                }
            }
        }
        return false;
    }

    //get the empty Cells
    private static Coordinates[] typeWriterEnumerate(int[][] puzzle) {
        Coordinates[] emptyCells =  new Coordinates[40];
        int iterator = 0;
        for(int y = 0; y < GRID_BOUNDARY; y++){
            for(int x = 0; x < GRID_BOUNDARY; x++){
                if(puzzle[x][y] == 0){
                    emptyCells[iterator] = new Coordinates(x,y);
                    if(iterator == 39){
                        return emptyCells;
                    }
                    iterator++;
                }
            }
        }
        return emptyCells;
    }
}

package sudoku.buildlogic;

import sudoku.computationlogic.GameLogic;
import sudoku.problemdomain.IStorage;
import sudoku.problemdomain.SudokuGame;
import sudoku.userinterface.IUserInterfaceContract;
import sudoku.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {


    public static void build(IUserInterfaceContract.View userInterface) throws IOException{
        SudokuGame initalState;
        IStorage storage = new LocalStorageImpl();

        // try to get the game data from storage if it exists
        try{
            initalState = storage.getGameData();
        }
        // else get a new game and update storage
        catch(IOException e){
            initalState = GameLogic.getNewGame();
            storage.updateGameData(initalState);
        }

        // bind ControlLogic to the User Interface to make it functional
        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);
        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initalState);
    }
}

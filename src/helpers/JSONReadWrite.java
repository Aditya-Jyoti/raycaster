package helpers;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import utils.Cell;

public class JSONReadWrite {
    private List<List<Cell>> gameBoard;

    public JSONReadWrite(List<List<Cell>> gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void writeGameBoardJSON() {
        List<List<Integer>> newBoard = new ArrayList<List<Integer>>();

        for (List<Cell> row : this.gameBoard) {
            List<Integer> newRow = new ArrayList<Integer>();
            for (Cell cell : row) {
                if (cell.getPlayer()) {
                    newRow.add(0);
                    continue;
                }

                newRow.add(cell.getVal());
            }
            newBoard.add(newRow);
        }

        JSONObject gameBoardJSON = new JSONObject(new HashMap<String, List<List<Integer>>>() {
            {
                put("gameBoard", newBoard);
            }
        });

        try (FileWriter file = new FileWriter("gameBoard.json")) {
            file.write(gameBoardJSON.toJSONString());
            file.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}

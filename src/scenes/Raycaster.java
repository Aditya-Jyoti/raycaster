package scenes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import helpers.Constants;
import utils.Cell;

public class Raycaster extends JPanel {
    private List<List<Cell>> gameBoard = new ArrayList<>();

    public Raycaster() {
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(null);
        setBackground(Constants.RC_BACKGROUND);
    }

    private void displayGameBoard() {
        for (List<Cell> row : this.gameBoard) {
            for (Cell cell : row) {
                if (cell.getVal() == -1) {
                    cell.setColour(Constants.RC_BACKGROUND);
                } else if (cell.getVal() == 0) {
                    cell.setColour(Constants.PLAYER_COLOUR);
                } else {
                    cell.setColour(Constants.WALL_CELL_COLOUR);
                }
                add(cell);
            }
        }
    }

    /* getter */
    public List<List<Cell>> getGameBoard() {
        return this.gameBoard;
    }

    /* setter */
    public void setGameBoard(List<List<Cell>> newGameBoard) {
        this.gameBoard = newGameBoard;
    }

    /* display update */
    public void update() {
        displayGameBoard();
        repaint();
    }
}

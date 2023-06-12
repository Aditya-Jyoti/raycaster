package scenes;

import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import helpers.Constants;
import utils.Cell;
import utils.Player;

public class Raycaster extends JPanel implements KeyListener {
    private Player player;
    private List<List<Cell>> gameBoard = new ArrayList<>();

    public void keyPressed(KeyEvent event) {
        System.out.println(event.getKeyCode());
    }

    public void keyTyped(KeyEvent event) {
    }

    public void keyReleased(KeyEvent event) {
    }


    public Raycaster() {
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(null);
        setBackground(Constants.RC_BACKGROUND);

    }

    private void displayGameBoard() {
        for (List<Cell> row : this.gameBoard) {
            for (Cell cell : row) {
                if (cell.getVal() == 0) {
                    player = new Player(cell.getXIdx(), cell.getYIdx(), 0.0);
                    add(player);
                } else if (cell.getVal() == 1) {
                    cell.setColour(Constants.WALL_CELL_COLOUR);
                    add(cell);
                }
            }
        }
    }

    /* getter */
    public List<List<Cell>> getGameBoard() {
        return this.gameBoard;
    }

    public int getPlayerXPos() {
        return this.player.getXPos();
    }

    public int getPlayerYPos() {
        return this.player.getYPos();
    }

    /* setter */
    public void setGameBoard(List<List<Cell>> newGameBoard) {
        this.gameBoard = newGameBoard;
    }

    public void setPlayerXPos(int newXPos) {
        this.player.setXPos(newXPos);
    }

    public void setPlayerYPos(int newYPos) {
        this.player.setYPos(newYPos);
    }

    /* display update */
    public void update() {
        displayGameBoard();
        repaint();
    }
}

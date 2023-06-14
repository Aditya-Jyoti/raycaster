package scenes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import helpers.Constants;
import utils.Cell;
import utils.Player;

public class Raycaster extends JPanel {
    private Player player;
    private List<List<Cell>> gameBoard = new ArrayList<>();

    public Raycaster() {
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(null);
        setBackground(Constants.RC_BACKGROUND);

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseMoved(MouseEvent event) {
                double base = event.getX() - player.getXPos();
                double height = event.getY() - player.getYPos();
                double angle = Math.atan2(height, base); // radians
                player.setAngle(angle);
            }

            public void mouseDragged(MouseEvent event) {
            }
        });
    }

    private void displayGameBoard() {
        for (List<Cell> row : this.gameBoard) {
            for (Cell cell : row) {
                if (cell.getVal() == 0) {
                    this.player = new Player(cell.getXIdx(), cell.getYIdx());
                    add(this.player);
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

    public double getPlayerXPos() {
        return this.player.getXPos();
    }

    public double getPlayerYPos() {
        return this.player.getYPos();
    }

    public double getPlayerAngle() {
        return this.player.getAngle();
    }

    public Player getPlayer() {
        return this.player;
    }

    /* setter */
    public void setGameBoard(List<List<Cell>> newGameBoard) {
        this.gameBoard = newGameBoard;
    }

    public void setPlayerXPos(double newXPos) {
        this.player.setXPos(newXPos);
    }

    public void setPlayerYPos(double newYPos) {
        this.player.setYPos(newYPos);
    }

    /* display update */
    public void update() {
        displayGameBoard();
        repaint();
    }
}

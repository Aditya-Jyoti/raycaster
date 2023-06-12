package scenes;

import utils.Cell;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import helpers.Constants;

public class LevelBuilder extends JPanel {
    private int mouseXIdx = 0;
    private int mouseYIdx = 0;
    private boolean hasPlayer = false;

    private List<List<Cell>> gameBoard = new ArrayList<>();

    public LevelBuilder() {
        setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setBackground(Constants.LB_BACKGROUND);
        setLayout(null);
        drawGameBoard();

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {
                mouseXIdx = event.getX() / Constants.CELL_SIZE;
                mouseYIdx = event.getY() / Constants.CELL_SIZE;

                if (mouseXIdx == 20) {
                    mouseXIdx = 19;
                } else if (mouseYIdx == -1) {
                    mouseYIdx = 0;
                }

                if (event.getButton() == MouseEvent.BUTTON1) {
                    if (gameBoard.get(mouseYIdx).get(mouseXIdx).getVal() == -1) {
                        gameBoard.get(mouseYIdx).get(mouseXIdx).setVal(1);
                        gameBoard.get(mouseYIdx).get(mouseXIdx).setColour(Constants.WALL_CELL_COLOUR);
                        add(gameBoard.get(mouseYIdx).get(mouseXIdx));

                    } else {
                        gameBoard.get(mouseYIdx).get(mouseXIdx).setVal(-1);
                        gameBoard.get(mouseYIdx).get(mouseXIdx).setColour(Constants.LB_BACKGROUND);
                        remove(gameBoard.get(mouseYIdx).get(mouseXIdx));
                    }

                } else if (event.getButton() == MouseEvent.BUTTON3) {
                    if (!(gameBoard.get(mouseYIdx).get(mouseXIdx).getVal() == 0) && !hasPlayer) {
                        hasPlayer = true;
                        gameBoard.get(mouseYIdx).get(mouseXIdx).setVal(0);
                        gameBoard.get(mouseYIdx).get(mouseXIdx).setColour(Constants.PLAYER_COLOUR);
                        add(gameBoard.get(mouseYIdx).get(mouseXIdx));

                    } else if (gameBoard.get(mouseYIdx).get(mouseXIdx).getVal() == 0 && hasPlayer) {
                        hasPlayer = false;
                        gameBoard.get(mouseYIdx).get(mouseXIdx).setVal(-1);
                        gameBoard.get(mouseYIdx).get(mouseXIdx).setColour(Constants.LB_BACKGROUND);
                        remove(gameBoard.get(mouseYIdx).get(mouseXIdx));
                    }
                }
            }

            public void mousePressed(MouseEvent event) {
            }

            public void mouseReleased(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }
        });
    }

    private void drawGameBoard() {
        for (int yIdx = 0; yIdx < Constants.CELLS_IN_COL; yIdx++) {
            List<Cell> cellRow = new ArrayList<>();
            for (int xIdx = 0; xIdx < Constants.CELLS_IN_ROW; xIdx++) {
                cellRow.add(new Cell(xIdx, yIdx, Constants.CELL_SIZE, -1));
            }
            gameBoard.add(cellRow);
        }
        displayGameBoard();
    }

    private void displayGameBoard() {
        for (List<Cell> row : this.gameBoard) {
            for (Cell cell : row) {
                if (cell.getVal() == -1) {
                    cell.setColour(Constants.LB_BACKGROUND);
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

    /* update display */
    public void update() {
        displayGameBoard();
        repaint();
    }
}
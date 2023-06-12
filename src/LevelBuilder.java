import utils.Cell;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

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
                        add(gameBoard.get(mouseYIdx).get(mouseXIdx));

                    } else {
                        gameBoard.get(mouseYIdx).get(mouseXIdx).setVal(-1);
                        remove(gameBoard.get(mouseYIdx).get(mouseXIdx));
                    }

                } else if (event.getButton() == MouseEvent.BUTTON3) {
                    if (!gameBoard.get(mouseYIdx).get(mouseXIdx).getPlayer() && !hasPlayer) {
                        hasPlayer = true;
                        gameBoard.get(mouseYIdx).get(mouseXIdx).setPlayer(true);
                        add(gameBoard.get(mouseYIdx).get(mouseXIdx));

                    } else {
                        hasPlayer = false;
                        gameBoard.get(mouseYIdx).get(mouseXIdx).setPlayer(false);
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
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawGrid(graphics);
    }

    private void drawGrid(Graphics graphics) {
        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.setStroke(new BasicStroke(2));
        graphics2d.setColor(Constants.BORDER_COLOUR);

        for (int horLine = 0; horLine < Constants.CELLS_IN_COL; horLine++) {
            graphics2d.draw(new Line2D.Float(0, Constants.CELL_SIZE * horLine, Constants.WINDOW_WIDTH,
                    Constants.CELL_SIZE * horLine));
        }

        for (int verLine = 0; verLine < Constants.CELLS_IN_ROW; verLine++) {
            graphics2d.draw(new Line2D.Float(Constants.CELL_SIZE * verLine, 0, Constants.CELL_SIZE * verLine,
                    Constants.WINDOW_HEIGHT));
        }
    }

    private void drawGameBoard() {
        for (int yIdx = 0; yIdx < Constants.CELLS_IN_COL; yIdx++) {
            List<Cell> cellRow = new ArrayList<>();
            for (int xIdx = 0; xIdx < Constants.CELLS_IN_ROW; xIdx++) {
                cellRow.add(new Cell(xIdx, yIdx, Constants.CELL_SIZE, -1));
            }
            gameBoard.add(cellRow);
        }
    }

    /* getter */

    public List<List<Cell>> getGameBoard() {
        return this.gameBoard;
    }
}
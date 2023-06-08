import static utils.Constants.WINDOW_WIDTH;
import static utils.Constants.WINDOW_HEIGHT;
import static utils.Constants.LB_BACKGROUND;
import static utils.Constants.CELLS_IN_COL;
import static utils.Constants.CELLS_IN_ROW;
import static utils.Constants.CELL_SIZE;
import static utils.Constants.WALL_CELL_COLOUR;

import utils.Cell;
import utils.Updater;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class LevelBuilder extends JFrame {
    private int mouseXIdx = 0;
    private int mouseYIdx = 0;

    private List<List<Cell>> gameBoard = new ArrayList<>();

    public LevelBuilder() {
        super("Level Builder");

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("assets/level-builder-logo.png").getImage());
        setLayout(null);

        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                drawGrid(graphics);
            }
        });

        getContentPane().setBackground(LB_BACKGROUND);
        getContentPane().setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        pack();

        drawGameBoard();
        
        Updater updater = new Updater(this);
        updater.start();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                updater.setRunning(false);
            }
        });

        addMouseListener(new MouseListener() {
            public void mousePressed(MouseEvent event) {
            }

            public void mouseReleased(MouseEvent event) {
            }

            public void mouseEntered(MouseEvent event) {
            }

            public void mouseExited(MouseEvent event) {
            }

            public void mouseClicked(MouseEvent event) {
                mouseXIdx = event.getX() / CELL_SIZE;
                mouseYIdx = event.getY() / CELL_SIZE - 1;

                if (mouseXIdx == 20) {
                    mouseXIdx = 19;
                } else if (mouseYIdx == -1) {
                    mouseYIdx = 0;
                }

                if (gameBoard.get(mouseYIdx).get(mouseXIdx).getVal() == 0) {
                    gameBoard.get(mouseYIdx).get(mouseXIdx).setNewValues(1, WALL_CELL_COLOUR);
                    add(gameBoard.get(mouseYIdx).get(mouseXIdx));

                } else {
                    gameBoard.get(mouseYIdx).get(mouseXIdx).setNewValues(0, LB_BACKGROUND);
                    add(gameBoard.get(mouseYIdx).get(mouseXIdx));
                }

            }

        });

        setVisible(true);
    }

    private void drawGrid(Graphics graphics) {
        Graphics2D graphics2d = (Graphics2D) graphics;

        for (int horLine = 0; horLine < CELLS_IN_COL; horLine++) {
            graphics2d.drawLine(0, CELL_SIZE * horLine, WINDOW_WIDTH, CELL_SIZE * horLine);
        }

        for (int verLine = 0; verLine < CELLS_IN_ROW; verLine++) {
            graphics2d.drawLine(CELL_SIZE * verLine, 0, CELL_SIZE * verLine, WINDOW_HEIGHT);
        }
    }

    private void drawGameBoard() {
        for (int yIdx = 0; yIdx < CELLS_IN_COL; yIdx++) {
            List<Cell> cellRow = new ArrayList<>();

            for (int xIdx = 0; xIdx < CELLS_IN_ROW; xIdx++) {
                cellRow.add(new Cell(xIdx, yIdx, CELL_SIZE, 0, LB_BACKGROUND));
            }
            gameBoard.add(cellRow);
        }
    }

    public void main() {

    }

}

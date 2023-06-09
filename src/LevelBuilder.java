import utils.Cell;
import utils.Updater;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.JSONObject;

import javax.swing.ImageIcon;

public class LevelBuilder extends JFrame {
    private int mouseXIdx = 0;
    private int mouseYIdx = 0;
    private boolean hasPlayer = false;

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

        getContentPane().setBackground(Constants.LB_BACKGROUND);
        getContentPane().setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));
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
            @Override
            public void mouseClicked(MouseEvent event) {
                mouseXIdx = event.getX() / Constants.CELL_SIZE;
                mouseYIdx = event.getY() / Constants.CELL_SIZE - 1;

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

        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == 10) {
                    writeGameBoardJSON();
                }
            }

            public void keyTyped(KeyEvent event) {

            }

            public void keyReleased(KeyEvent event) {

            }
        });

        setVisible(true);
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

    private void writeGameBoardJSON() {
        JSONObject gameBoardJSON = new JSONObject();

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

        gameBoardJSON.put("gameBoard", newBoard);

        try (FileWriter file = new FileWriter("gameBoard.json")) {
            file.write(gameBoardJSON.toJSONString());
            file.flush();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void main() {

    }

}
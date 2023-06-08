import static utils.Constants.WINDOW_WIDTH;
import static utils.Constants.WINDOW_HEIGHT;
import static utils.Constants.LB_BACKGROUND;
import static utils.Constants.CELLS_IN_COL;
import static utils.Constants.CELLS_IN_ROW;
import static utils.Constants.CELL_SIZE;

import utils.Updater;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class LevelBuilder extends JFrame {
    private int mouseXIdx = 0;
    private int mouseYIdx = 0;

    public LevelBuilder() {
        super("Level Builder");

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("assets/level-builder-logo.png").getImage());

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

        Updater updater = new Updater(this);
        updater.start();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                updater.setRunning(false);
            }
        });

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent event) {
            }

            @Override
            public void mouseMoved(MouseEvent event) {
                mouseXIdx = event.getX() / CELL_SIZE;
                mouseYIdx = event.getY() / CELL_SIZE - 1;

                if (mouseXIdx == 20) {
                    mouseXIdx = 19;
                } else if (mouseYIdx == -1) {
                    mouseYIdx = 0;
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

    public void main() {

    }

}

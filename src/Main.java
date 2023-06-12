import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import helpers.JSONReadWrite;
import helpers.Updater;

public class Main extends JFrame {
    private boolean isLevelBuilderVisible = true;

    public Main() {
        super("RayCaster");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("assets/main.png").getImage());

        CardLayout layout = new CardLayout();
        setLayout(layout);

        getContentPane().setBackground(Constants.LB_BACKGROUND);
        getContentPane().setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));

        LevelBuilder levelBuilder = new LevelBuilder();
        add(levelBuilder, "levelBuilder");

        Raycaster raycaster = new Raycaster();
        add(raycaster, "raycaster");

        Updater updater = new Updater(this, levelBuilder);
        updater.start();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                updater.setRunning(false);
            }
        });

        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == 10) {
                    if (isLevelBuilderVisible) {
                        JSONReadWrite jsonWriter = new JSONReadWrite(levelBuilder.getGameBoard());
                        jsonWriter.writeGameBoardJSON();
                        isLevelBuilderVisible = false;
                        layout.show(getContentPane(), "raycaster");

                    } else {
                        layout.show(getContentPane(), "levelBuilder");
                        isLevelBuilderVisible = true;
                    }
                }
            }

            public void keyTyped(KeyEvent event) {
            }

            public void keyReleased(KeyEvent event) {
            }
        });

    }

    public static void main(String[] args) {
        Main mainWindow = new Main();
        mainWindow.pack();
        mainWindow.setVisible(true);
    }

}
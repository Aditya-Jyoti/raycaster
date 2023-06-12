import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import helpers.Constants;
import helpers.Updater;
import scenes.LevelBuilder;
import scenes.Raycaster;

public class Main extends JFrame {
    private boolean isLevelBuilderVisible = true;

    public Main() {
        super("level builder");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("assets/level-builder-logo.png").getImage());

        CardLayout layout = new CardLayout();
        setLayout(layout);

        getContentPane().setBackground(Constants.LB_BACKGROUND);
        getContentPane().setPreferredSize(new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT));

        LevelBuilder levelBuilder = new LevelBuilder();
        add(levelBuilder, "levelBuilder");

        Raycaster raycaster = new Raycaster();
        add(raycaster, "raycaster");

        Updater updater = new Updater(this, levelBuilder, raycaster);
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
                        isLevelBuilderVisible = false;
                        setTitle("Raycaster");
                        setIconImage(new ImageIcon("assets/main-logo.png").getImage());
                        raycaster.setGameBoard(levelBuilder.getGameBoard());
                        raycaster.update();
                        layout.show(getContentPane(), "raycaster");

                    } else {
                        isLevelBuilderVisible = true;
                        setTitle("Level Builder");
                        setIconImage(new ImageIcon("assets/level-builder-logo.png").getImage());
                        levelBuilder.setGameBoard(raycaster.getGameBoard());
                        levelBuilder.update();
                        layout.show(getContentPane(), "levelBuilder");
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
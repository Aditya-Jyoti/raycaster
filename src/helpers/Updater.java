package helpers;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Updater extends Thread {
    public static final double FPS = 60.0;
    public static final double MS_PER_FPS = 1000.0 / FPS;

    private JFrame frame;
    private JPanel levelBuilder;
    private boolean running = true;

    public Updater(JFrame frame, JPanel levelBuilder) {
        this.frame = frame;
        this.levelBuilder = levelBuilder;
    }

    public void setRunning(boolean isRunning) {
        this.running = isRunning;
    }

    @Override
    public void run() {
        long lastFPSUpdate = System.currentTimeMillis();

        while (this.running) {
            long currTime = System.currentTimeMillis();
            long timeSinceLastFPSUpdate = currTime - lastFPSUpdate;

            if (timeSinceLastFPSUpdate >= MS_PER_FPS) {
                this.frame.repaint();
                this.levelBuilder.repaint();
                lastFPSUpdate = currTime;
                timeSinceLastFPSUpdate = 0L;
            }

            long timeUntilNextFPSUpdate = (long) (MS_PER_FPS - timeSinceLastFPSUpdate);

            try {
                Thread.sleep(timeUntilNextFPSUpdate);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }
}

package utils;

import javax.swing.JFrame;

public class Updater extends Thread {
    public static final double FPS = 60.0;
    public static final double MS_PER_FPS = 1000.0 / FPS;

    private JFrame frame;
    private boolean running = true;

    public Updater(JFrame frame) {
        this.frame = frame;
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

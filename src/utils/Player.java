package utils;

import java.awt.Graphics;

import javax.swing.JComponent;

import helpers.Constants;

public class Player extends JComponent {
    private double xPos;
    private double yPos;
    private double angle = 0.0;

    public Player(int xIdx, int yIdx) {
        this.xPos = xIdx * Constants.CELL_SIZE;
        this.yPos = yIdx * Constants.CELL_SIZE;

        this.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Constants.PLAYER_COLOUR);
        graphics.fillOval((int) this.xPos, (int) this.yPos, Constants.CELL_SIZE, Constants.CELL_SIZE);
    }

    @Override
    public String toString() {
        return "pos: " + this.xPos + ", " + this.yPos + ". angle: " + this.angle;
    }

    /* getter */
    public double getXPos() {
        return this.xPos;
    }

    public double getYPos() {
        return this.yPos;
    }

    public double getAngle() {
        return this.angle;
    }

    /* setter */
    public void setXPos(double newXPos) {
        this.xPos = newXPos;
    }

    public void setYPos(double newYPos) {
        this.yPos = newYPos;
    }

    public void setAngle(double newAngle) {
        this.angle = newAngle;
    }
}

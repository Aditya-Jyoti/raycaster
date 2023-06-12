package utils;

import java.awt.Graphics;

import javax.swing.JComponent;

import helpers.Constants;

public class Player extends JComponent {
    private int xPos;
    private int yPos;
    private double angle;

    public Player(int xIdx, int yIdx, double angle) {
        this.xPos = xIdx * Constants.CELL_SIZE;
        this.yPos = yIdx * Constants.CELL_SIZE;
        this.angle = angle;

        this.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Constants.PLAYER_COLOUR);
        graphics.fillOval(this.xPos, this.yPos, Constants.CELL_SIZE, Constants.CELL_SIZE);
    }

    /* getter */
    public int getXPos() {
        return this.xPos;
    }

    public int getYPos() {
        return this.yPos;
    }

    /* setter */
    public void setXPos(int newXPos) {
        this.xPos = newXPos;
    }

    public void setYPos(int newYPos) {
        this.yPos = newYPos;
    }
}

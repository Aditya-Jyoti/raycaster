package utils;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComponent;

import helpers.Constants;

public class Player extends JComponent {
    private double startingXPos;
    private double startingYPos;
    private double xPos;
    private double yPos;
    private double angle = 0.0;
    private List<Line> rays = new ArrayList<>();

    public Player(int xIdx, int yIdx) {
        this.xPos = xIdx * Constants.CELL_SIZE;
        this.yPos = yIdx * Constants.CELL_SIZE;
        this.startingXPos = xIdx * Constants.CELL_SIZE;
        this.startingYPos = yIdx * Constants.CELL_SIZE;

        this.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        for (int i = 0; i < 2; i++) {
            Line newRay = new Line(xPos + 20, yPos + 20, 50, angle);
            rays.add(newRay);
            add(newRay);
        }
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

    public void updateRays() {
        for (Line ray : rays) {
            ray.setAngle(angle);
            ray.setXPos(xPos + 20);
            ray.setYPos(yPos + 20);
        }
    }

    public HashMap<String, Integer> updateIndices(double newXPos, double newYPos) {
        double diffXPos = newXPos - this.startingXPos;
        double diffYPos = newYPos - this.startingYPos;
        int newXIndex = (int) this.startingXPos / Constants.CELL_SIZE;
        int newYIndex = (int) this.startingYPos / Constants.CELL_SIZE;

        if (diffXPos >= Constants.CELL_SIZE) {
            newXIndex = (int) (this.startingXPos / Constants.CELL_SIZE) + (int) Math.floor(diffXPos / Constants.CELL_SIZE);
        } else if (diffXPos <= -Constants.CELL_SIZE) {
            newXIndex = (int) (this.startingXPos / Constants.CELL_SIZE) + (int) Math.floor(diffXPos / Constants.CELL_SIZE) + 1;
        }

        if (diffYPos >= Constants.CELL_SIZE) {
            newYIndex = (int) (this.startingYPos / Constants.CELL_SIZE) + (int) Math.floor(diffYPos / Constants.CELL_SIZE);
        } else if (diffYPos <= -Constants.CELL_SIZE) {
            newYIndex = (int) (this.startingYPos / Constants.CELL_SIZE) + (int) Math.floor(diffYPos / Constants.CELL_SIZE) + 1;
        }

        HashMap<String, Integer> indexMap = new HashMap<String, Integer>();
        indexMap.put("xIdx", newXIndex);
        indexMap.put("yIdx", newYIndex);

        return indexMap;
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

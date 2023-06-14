package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

import helpers.Constants;

public class Line extends JComponent {
    private double x1;
    private double y1;
    private double length;
    private double angle;

    public Line(double x1, double y1, double length, double angle) {
        this.x1 = x1;
        this.y1 = y1;
        this.length = length;
        this.angle = angle;
        this.setBounds(0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2d = (Graphics2D) graphics;
        graphics2d.setColor(Color.BLUE);
        graphics2d.setStroke(new BasicStroke(5));

        double x2 = this.x1 + (length * Math.cos(this.angle));
        double y2 = this.y1 + (length * Math.sin(this.angle));
        graphics2d.draw(new Line2D.Float((float) this.x1, (float) this.y1, (float) x2, (float) y2));
    }

    /* getter */

    /* setter */
    public void setXPos(double newX) {
        this.x1 = newX;
    }

    public void setYPos(double newY) {
        this.y1 = newY;
    }

    public void setAngle(double newAngle) {
        this.angle = newAngle;
    }
}

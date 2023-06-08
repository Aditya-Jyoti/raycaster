package utils;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Cell extends JComponent {
    private int xIdx;
    private int yIdx;
    private int size;
    private int val;
    private Color colour;

    public Cell(int xIdx, int yIdx, int size, int val, Color colour) {
        this.xIdx = xIdx;
        this.yIdx = yIdx;
        this.size = size;
        this.colour = colour;

        this.setBounds(xIdx * size, yIdx * size, size, size);
        this.setOpaque(true);
        
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(colour);
        graphics.fillRect(0, 0, this.size, this.size);
    }

    public int getVal() {
        return this.val;
    }

    public void setNewValues(int newVal, Color newColour) {
        this.val = newVal;
        this.colour = newColour;
        this.repaint();
    }
}

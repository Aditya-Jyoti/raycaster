package utils;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import helpers.Constants;

public class Cell extends JComponent {
    private int xIdx;
    private int yIdx;
    private int size;
    private int val;
    private Color colour;

    public Cell(int xIdx, int yIdx, int size, int val) {
        this.xIdx = xIdx;
        this.yIdx = yIdx;
        this.size = size;
        this.val = val;

        this.setBounds(this.xIdx * size, this.yIdx * size, size, size);
        this.setBorder(BorderFactory.createLineBorder(Constants.BORDER_COLOUR, 1));
        this.setOpaque(true);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(colour);
        graphics.fillRect(0, 0, this.size, this.size);
    }

    /* getter */
    public int getVal() {
        return this.val;
    }

    public int getXIdx() {
        return this.xIdx;
    }

    public int getYIdx() {
        return this.yIdx;
    }

    /* setter */
    public void setVal(int newVal) {
        this.val = newVal;
    }

    public void setColour(Color newColour) {
        this.colour = newColour;
    }
}

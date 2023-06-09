package utils;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

public class Cell extends JComponent {
    private int xIdx;
    private int yIdx;
    private int size;
    private int val;
    private boolean player;
    private Color colour = new Color(66, 66, 66);

    public Cell(int xIdx, int yIdx, int size, int val) {
        this.xIdx = xIdx;
        this.yIdx = yIdx;
        this.size = size;
        this.val = val;

        this.setBounds(this.xIdx * size, this.yIdx * size, size, size);
        this.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
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

    public boolean getPlayer() {
        return this.player;
    }

    /* setter */
    public void setVal(int newVal) {
        this.val = newVal;
    }

    public void setPlayer(boolean isPlayer) {
        this.player = isPlayer;

        if (this.player) {
            colour = new Color(236, 189, 0);
        } else {
            colour = new Color(66, 66, 66);
        }
    }
}

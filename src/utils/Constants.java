package utils;

import java.awt.Color;

public class Constants {
    private Constants() {

    }

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    public static final int CELL_SIZE = 40;

    public static final int CELLS_IN_ROW = WINDOW_WIDTH / CELL_SIZE;
    public static final int CELLS_IN_COL = WINDOW_HEIGHT / CELL_SIZE;

    public static final Color LB_BACKGROUND = new Color(211, 211, 211);
    
    public static final Color WALL_CELL_COLOUR = new Color(66, 66, 66);

}
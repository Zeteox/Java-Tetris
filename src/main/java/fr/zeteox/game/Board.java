package fr.zeteox.game;

public class Board {
    private static Board instance;
    private final int COLUMNS = 10;
    private final int ROWS = 20;
    private int[][] grid = new int[ROWS][COLUMNS];

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public int[][] getGrid() {
        return this.grid;
    }

    public int getColumnNumber() {
        return COLUMNS;
    }
    public int getRowNumber() {
        return ROWS;
    }
}

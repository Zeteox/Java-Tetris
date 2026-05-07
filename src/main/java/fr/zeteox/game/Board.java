package fr.zeteox.game;

import fr.zeteox.model.Tetromino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Board {
    private static Board instance;
    private final int COLUMNS = 10;
    private final int ROWS = 20;
    private int[][] grid = new int[ROWS][COLUMNS];
    private int totalCompletedRows = 0;

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public void solidify(Tetromino tetromino) {
        int[][] box = tetromino.getBoundingBox();
        for (int row = 0; row < box.length; row++) {
            for (int col = 0; col < box[row].length; col++) {
                if (box[row][col] != 0) {
                    grid[tetromino.getY() + row][tetromino.getX() + col] = box[row][col];
                }
            }
        }
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

    public void verifyBoard() {
        int completedRows = 0;

        for (int row = ROWS - 1; row >= 0; row--) {

            boolean full = true;

            for (int col = 0; col < COLUMNS; col++) {
                if (grid[row][col] == 0) {
                    full = false;
                    break;
                }
            }

            if (full) {
                completedRows++;
                for (int r = row; r > 0; r--) {
                    grid[r] = Arrays.copyOf(grid[r - 1], COLUMNS);
                }
                grid[0] = new int[COLUMNS];

                row++;
            }
        }

        this.totalCompletedRows += completedRows;

        while (totalCompletedRows >= 10) {
            GameState.incrementLevel();
            totalCompletedRows -= 10;
        }

        switch (completedRows) {
            case 1:
                GameState.addScore(100);
                break;
            case 2:
                GameState.addScore(300);
                break;
            case 3:
                GameState.addScore(500);
                break;
            case 4:
                GameState.addScore(800);
                break;
        }
    }

    public void resetBoard() {
        this.grid = new int[ROWS][COLUMNS];
    }
}

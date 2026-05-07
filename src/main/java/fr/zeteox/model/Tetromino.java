package fr.zeteox.model;

import fr.zeteox.game.Board;
import javafx.scene.paint.Color;

public class Tetromino {
    private int x;
    private int y;
    private Color color;
    private int currentRotationIndex;
    private TetrominoShape shape;

    public Tetromino(TetrominoShape shape, int x, int y) {
        this.x = x;
        this.y = y;
        this.color = shape.getColor();
        this.currentRotationIndex = 0;
        this.shape = shape;
    }

    public void down() {
        this.y++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public int getCurrentRotationIndex() {
        return currentRotationIndex;
    }

    public TetrominoShape getShape() {
        return shape;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void cycleRotationIndex() {
        currentRotationIndex = (currentRotationIndex + 1) % 4;
    }

    public void solidify() {
        Board.getInstance().solidify(this);
    }

    public int[][] getBoundingBox() {
        int[][] shape = this.getShape().getRotation(this.getCurrentRotationIndex());

        int minRow = shape.length, maxRow = 0, minCol = shape[0].length, maxCol = 0;

        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape[row].length; col++) {
                if (shape[row][col] != 0) {
                    minRow = Math.min(minRow, row);
                    maxRow = Math.max(maxRow, row);
                    minCol = Math.min(minCol, col);
                    maxCol = Math.max(maxCol, col);
                }
            }
        }

        int height = maxRow - minRow + 1;
        int width = maxCol - minCol + 1;
        int[][] boundingBox = new int[height][width];

        for (int row = minRow; row <= maxRow; row++) {
            for (int col = minCol; col <= maxCol; col++) {
                boundingBox[row - minRow][col - minCol] = shape[row][col];
            }
        }

        return boundingBox;
    }
}

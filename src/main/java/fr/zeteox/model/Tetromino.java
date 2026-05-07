package fr.zeteox.model;

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
}

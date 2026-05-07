package fr.zeteox.model;

import java.util.Random;

public class TetrominoFactory {
    private static final TetrominoShape[] SHAPES = TetrominoShape.values();

    public static Tetromino getRandomTetromino() {
        TetrominoShape shape = SHAPES[new Random().nextInt(SHAPES.length)];
        return new Tetromino(shape, 5, 0);
    }
}

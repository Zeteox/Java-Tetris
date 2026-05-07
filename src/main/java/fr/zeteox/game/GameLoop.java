package fr.zeteox.game;

import fr.zeteox.model.Tetromino;
import fr.zeteox.model.TetrominoFactory;
import fr.zeteox.model.TetrominoShape;
import fr.zeteox.ui.GameRenderer;
import fr.zeteox.ui.InputHandler;
import fr.zeteox.ui.SidePanel;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

import java.util.Arrays;

import static fr.zeteox.Main.logger;

public class GameLoop {
    private static  GameLoop instance;
    private static SidePanel sidePanel;
    private GameRenderer gameRenderer;
    private AnimationTimer timer;
    private Tetromino currentTetromino;

    private long lastUpdate = 0;

    private GameLoop() {
        sidePanel = SidePanel.getInstance();
    }

    public static GameLoop getInstance() {
        if (instance == null) {
            instance = new GameLoop();
        }
        return instance;
    }

    public void start(Stage stage) {
        this.gameRenderer = new GameRenderer();
        this.changeTetromino();

        gameRenderer.getScene().setOnKeyPressed(e -> {
            InputHandler.handle(e.getCode(), currentTetromino);
        });

        stage.setScene(gameRenderer.getScene());
        stage.show();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (GameState.paused) return;
                if  (isGameOver()) {
                    GameLoop.getInstance().gameOver();
                    stop();
                } else {
                    if (now - lastUpdate >= getFallInterval()) {
                        if (verifyCollision(currentTetromino)) {
                            currentTetromino.solidify();
                            GameLoop.getInstance().changeTetromino();
                        } else {
                            currentTetromino.down();
                        }
                        lastUpdate = now;
                    }

                    Board.getInstance().verifyBoard();
                    gameRenderer.render(currentTetromino);
                    sidePanel.update();
                }
            }
        };
        timer.start();
    }

    public void changeTetromino() {
        this.currentTetromino = sidePanel.getNextTetromino();
        sidePanel.setNextTetromino(TetrominoFactory.getRandomTetromino());
    }

    public void gameOver() {
        GameState.gameOver = true;

        gameRenderer.renderGameOver();
        sidePanel.update();
    }

    private long getFallInterval() {
        int level = GameState.level;
        return Math.max(100_000_000L, 1_000_000_000L - (level - 1) * 100_000_000L);
    }

    public boolean isGameOver() {
        return verifyCollision(currentTetromino) && currentTetromino.getY() == 0;
    }

    public boolean verifyCollision(Tetromino tetromino) {
        int[][] box = tetromino.getBoundingBox();
        int[][] grid = Board.getInstance().getGrid();
        int rows = Board.getInstance().getRowNumber();
        int cols = Board.getInstance().getColumnNumber();

        for (int row = 0; row < box.length; row++) {
            for (int col = 0; col < box[row].length; col++) {
                if (box[row][col] == 0) continue;

                int gridRow = tetromino.getY() + row;
                int gridCol = tetromino.getX() + col;

                // Floor
                if (gridRow + 1 >= rows) return true;

                // Bounds check before accessing grid
                if (gridCol < 0 || gridCol >= cols) return true;

                // Block collision
                if (gridRow >= 0 && grid[gridRow + 1][gridCol] != 0) return true;
            }
        }
        return false;
    }

    public void restart() {
        GameState.gameOver = false;
        GameState.paused = false;
        GameState.level = 1;
        GameState.score = 0;

        Board.getInstance().resetBoard();

        currentTetromino = TetrominoFactory.getRandomTetromino();

        lastUpdate = 0;

        timer.start();
    }
}

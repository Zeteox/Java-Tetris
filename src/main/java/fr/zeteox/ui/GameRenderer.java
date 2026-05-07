package fr.zeteox.ui;

import fr.zeteox.game.Board;
import fr.zeteox.model.Tetromino;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class GameRenderer {
    private BorderPane root;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;
    private SidePanel sidePanel;

    private final int CASE_SIZE = 30;

    public GameRenderer() {
        this.canvas = new Canvas(300, 600);
        this.sidePanel = SidePanel.getInstance();
        this.root = new BorderPane();
        this.scene = new Scene(root, 450, 600);
        this.gc = canvas.getGraphicsContext2D();
        this.root.setLeft(canvas);
        this.root.setRight(sidePanel);
    }

    public Scene getScene() {
        return this.scene;
    }

    public void render(Tetromino tetromino) {
        gc.clearRect(0, 0, 300, 600);
        scene.setFill(Color.BLACK);
        gc.fill();
        this.renderGrid();
        this.renderTetromino(tetromino);
    }

    private void renderGrid() {
        for (int row = 0; row < Board.getInstance().getGrid().length; row++) {
            for (int col = 0; col < Board.getInstance().getGrid()[row].length; col++) {
                int x = col * CASE_SIZE;
                int y = row * CASE_SIZE;

                if (Board.getInstance().getGrid()[row][col] == 0) {
                    gc.setFill(Color.GRAY);
                    gc.fillRect(x, y, CASE_SIZE, CASE_SIZE);
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x + 1, y + 1, CASE_SIZE - 2, CASE_SIZE - 2);
                } else {
                    gc.setFill(Color.GOLD);
                    gc.fillRect(x, y, CASE_SIZE, CASE_SIZE);
                    gc.setFill(Color.YELLOW);
                    gc.fillRect(x + 1, y + 1, CASE_SIZE - 2, CASE_SIZE - 2);
                }
            }
        }
    }

    private void renderTetromino(Tetromino tetromino) {
        int[][] box = tetromino.getBoundingBox();
        for (int row = 0; row < box.length; row++) {
            for (int col = 0; col < box[row].length; col++) {
                if (box[row][col] != 0) {
                    int x = (tetromino.getX() + col) * CASE_SIZE;
                    int y = (tetromino.getY() + row) * CASE_SIZE;
                    gc.setFill(tetromino.getColor());
                    gc.fillRect(x, y, CASE_SIZE, CASE_SIZE);
                    gc.setFill(tetromino.getColor().darker());
                    gc.fillRect(x + 1, y + 1, CASE_SIZE - 2, CASE_SIZE - 2);
                }
            }
        }
    }

    public void renderGameOver() {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.RED);
        gc.setFont(javafx.scene.text.Font.font(40));

        gc.fillText(
                "GAME OVER",
                canvas.getWidth() / 2 - 110,
                canvas.getHeight() / 2
        );

        gc.setFont(javafx.scene.text.Font.font(20));
        gc.fillText(
                "Press R to restart",
                canvas.getWidth() / 2 - 75,
                canvas.getHeight() / 2 + 50
        );
    }
}

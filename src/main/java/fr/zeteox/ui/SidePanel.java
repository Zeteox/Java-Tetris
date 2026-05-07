package fr.zeteox.ui;

import fr.zeteox.game.GameState;
import fr.zeteox.model.Tetromino;
import fr.zeteox.model.TetrominoFactory;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class SidePanel extends VBox {
    private static SidePanel instance;
    private Text nextPiece;
    private Text scoreText;
    private Text levelText;
    private Tetromino nextTetromino;

    private Canvas previewCanvas;
    private GraphicsContext previewGc;

    private final int PREVIEW_CASE_SIZE = 20;

    public SidePanel() {
        nextPiece = new Text("Next:");
        scoreText = new Text("Score: " + GameState.score);
        levelText = new Text("Level: " + GameState.level);

        previewCanvas = new Canvas(120, 120);
        previewGc = previewCanvas.getGraphicsContext2D();

        styleText(scoreText);
        styleText(levelText);
        styleText(nextPiece);

        this.setStyle("-fx-background-color: #1a1a1a;");
        this.setPadding(new Insets(20));
        this.setSpacing(10);
        this.setPrefWidth(150);

        nextTetromino = TetrominoFactory.getRandomTetromino();

        this.renderNextTetromino();

        this.getChildren().addAll(levelText, scoreText, nextPiece, previewCanvas);
    }

    public static SidePanel getInstance() {
        if (instance == null) {
            instance = new SidePanel();
        }
        return instance;
    }

    private void styleText(Text text) {
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Arial", 16));
    }

    public void update() {
        scoreText.setText("Score: " + GameState.score);
        levelText.setText("Level: " + GameState.level);
        this.renderNextTetromino();
    }

    public Tetromino getNextTetromino() {
        return nextTetromino;
    }

    public void setNextTetromino(Tetromino nextTetromino) {
        this.nextTetromino = nextTetromino;
    }

    private void renderNextTetromino() {

        previewGc.setFill(Color.BLACK);
        previewGc.fillRect(0, 0, previewCanvas.getWidth(), previewCanvas.getHeight());

        int[][] box = nextTetromino.getBoundingBox();

        int offsetX = (int)(previewCanvas.getWidth() / 2)
                - (box[0].length * PREVIEW_CASE_SIZE) / 2;

        int offsetY = (int)(previewCanvas.getHeight() / 2)
                - (box.length * PREVIEW_CASE_SIZE) / 2;

        for (int row = 0; row < box.length; row++) {
            for (int col = 0; col < box[row].length; col++) {

                if (box[row][col] != 0) {

                    int x = offsetX + col * PREVIEW_CASE_SIZE;
                    int y = offsetY + row * PREVIEW_CASE_SIZE;

                    previewGc.setFill(nextTetromino.getColor());
                    previewGc.fillRect(x, y, PREVIEW_CASE_SIZE, PREVIEW_CASE_SIZE);

                    previewGc.setFill(nextTetromino.getColor().darker());
                    previewGc.fillRect(
                            x + 1,
                            y + 1,
                            PREVIEW_CASE_SIZE - 2,
                            PREVIEW_CASE_SIZE - 2
                    );
                }
            }
        }
    }
}

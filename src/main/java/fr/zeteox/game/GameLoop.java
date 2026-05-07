package fr.zeteox.game;

import fr.zeteox.model.Tetromino;
import fr.zeteox.model.TetrominoFactory;
import fr.zeteox.model.TetrominoShape;
import fr.zeteox.ui.GameRenderer;
import fr.zeteox.ui.SidePanel;
import javafx.animation.AnimationTimer;
import javafx.stage.Stage;

import static fr.zeteox.Main.logger;

public class GameLoop {
    private static  GameLoop instance;
    private static SidePanel sidePanel;
    private GameRenderer gameRenderer;
    private AnimationTimer timer;
    private Tetromino currentTetromino;

    private long lastUpdate = 0;
    private final long INTERVAL_NS = 1_000_000_000L;

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
        this.currentTetromino = TetrominoFactory.getRandomTetromino();
        stage.setScene(gameRenderer.getScene());
        stage.show();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= getFallInterval()) {
                    currentTetromino.down();
                    lastUpdate = now;
                }
                gameRenderer.render(currentTetromino);
                sidePanel.update();
            }
        };
        timer.start();
    }

    private long getFallInterval() {
        int level = sidePanel.getLevel();
        return Math.max(100_000_000L, 1_000_000_000L - (level - 1) * 100_000_000L);
    }
}

package fr.zeteox;

import fr.zeteox.game.GameLoop;
import fr.zeteox.ui.GameRenderer;
import javafx.application.Application;
import javafx.stage.Stage;

public class TetrisApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        GameRenderer gameRenderer = new GameRenderer();
        stage.setTitle("Tetris");
        stage.setScene(gameRenderer.getScene());
        stage.setResizable(false);
        stage.centerOnScreen();
        GameLoop.getInstance().start(stage);
    }
}

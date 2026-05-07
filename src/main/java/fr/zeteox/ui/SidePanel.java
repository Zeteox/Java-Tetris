package fr.zeteox.ui;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SidePanel extends VBox {
    private static SidePanel instance;
    private Text scoreText;
    private Text levelText;
    private int score = 0;
    private int level = 1;

    public SidePanel() {
        scoreText = new Text("Score: " + score);
        levelText = new Text("Level: " + level);

        styleText(scoreText);
        styleText(levelText);

        this.setStyle("-fx-background-color: #1a1a1a;");
        this.setPadding(new Insets(20));
        this.setSpacing(10);
        this.setPrefWidth(150);

        this.getChildren().addAll(scoreText, levelText);
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
        scoreText.setText("Score: " + score);
        levelText.setText("Level: " + level);
    }

    public void setScore(int score) { this.score = score; }
    public void setLevel(int level) { this.level = level; }
    public int getLevel() { return level; }
    public int getScore() { return score; }
}

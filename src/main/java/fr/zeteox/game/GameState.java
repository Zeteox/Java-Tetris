package fr.zeteox.game;

public class GameState {
    public static boolean paused = false;
    public static boolean gameOver = false;
    public static int level = 1;
    public static int score = 0;

    public static void switchPause() {
        paused = !paused;
    }

    public static void incrementLevel() {
        level++;
    }

    public static void addScore(int x) {
        score += x;
    }
}

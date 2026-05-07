package fr.zeteox.ui;

import fr.zeteox.game.Board;
import fr.zeteox.game.GameLoop;
import fr.zeteox.game.GameState;
import fr.zeteox.model.Tetromino;
import javafx.scene.input.KeyCode;

public class InputHandler {
    public static void handle(KeyCode key, Tetromino tetromino) {
        int cols = Board.getInstance().getColumnNumber();

        switch (key) {
            case LEFT:
                if (tetromino.getX() > 0) {
                    boolean blockedLeft = false;
                    int[][] box = tetromino.getBoundingBox();
                    for (int row = 0; row < box.length; row++) {
                        if (box[row][0] == 0) continue;
                        int gridRow = tetromino.getY() + row;
                        if (gridRow >= 0 && Board.getInstance().getGrid()[gridRow][tetromino.getX() - 1] != 0) {
                            blockedLeft = true;
                            break;
                        }
                    }
                    if (!blockedLeft) tetromino.setX(tetromino.getX() - 1);
                }
                break;
            case RIGHT:
                if (tetromino.getX() + tetromino.getBoundingBox()[0].length < cols) {
                    boolean blockedRight = false;
                    int[][] box = tetromino.getBoundingBox();
                    int rightEdge = box[0].length - 1;
                    for (int row = 0; row < box.length; row++) {
                        if (box[row][rightEdge] == 0) continue;
                        int gridRow = tetromino.getY() + row;
                        if (gridRow >= 0 && Board.getInstance().getGrid()[gridRow][tetromino.getX() + box[row].length] != 0) {
                            blockedRight = true;
                            break;
                        }
                    }
                    if (!blockedRight) tetromino.setX(tetromino.getX() + 1);
                }
                break;
            case UP:
                tetromino.cycleRotationIndex();
                int[][] newBox = tetromino.getBoundingBox();

                if (tetromino.getX() < 0) {
                    tetromino.setX(0);
                }
                if (tetromino.getX() + newBox[0].length > cols) {
                    tetromino.setX(cols - newBox[0].length);
                }

                boolean rotationBlocked = false;
                for (int row = 0; row < newBox.length; row++) {
                    for (int col = 0; col < newBox[row].length; col++) {
                        if (newBox[row][col] == 0) continue;
                        int gridRow = tetromino.getY() + row;
                        int gridCol = tetromino.getX() + col;
                        if (gridRow >= 0 && gridRow < Board.getInstance().getRowNumber()
                                && gridCol >= 0 && gridCol < cols
                                && Board.getInstance().getGrid()[gridRow][gridCol] != 0) {
                            rotationBlocked = true;
                            break;
                        }
                    }
                    if (rotationBlocked) break;
                }

                if (rotationBlocked) {
                    tetromino.cycleRotationIndex();
                    tetromino.cycleRotationIndex();
                    tetromino.cycleRotationIndex();
                }
                break;
            case DOWN:
                tetromino.setY(tetromino.getY() + 1);
                break;
            case SPACE:
                while (!GameLoop.getInstance().verifyCollision(tetromino)) {
                    tetromino.setY(tetromino.getY() + 1);
                }
                break;
            case P:
                GameState.switchPause();
                break;

            case R:
                if (!GameState.gameOver) {break;}
                GameLoop.getInstance().restart();
                break;
        }
    }
}

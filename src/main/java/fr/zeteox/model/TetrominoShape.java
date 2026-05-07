package fr.zeteox.model;

import javafx.scene.paint.Color;

public enum TetrominoShape {

    I(new int[][][]{
            {
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0}
            },
            {
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0},
                    {0, 0, 1, 0}
            },
            {
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {1, 1, 1, 1},
                    {0, 0, 0, 0}
            },
            {
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0},
                    {0, 1, 0, 0}
            }
    }, Color.CYAN),

    O(new int[][][]{
            {
                    {1, 1},
                    {1, 1}
            },
            {
                    {1, 1},
                    {1, 1}
            },
            {
                    {1, 1},
                    {1, 1}
            },
            {
                    {1, 1},
                    {1, 1}
            }
    }, Color.YELLOW),


    T(new int[][][]{
            {
                    {0, 1, 0},
                    {1, 1, 1},
                    {0, 0, 0}
            },
            {
                    {0, 1, 0},
                    {0, 1, 1},
                    {0, 1, 0}
            },
            {
                    {0, 0, 0},
                    {1, 1, 1},
                    {0, 1, 0}
            },
            {
                    {0, 1, 0},
                    {1, 1, 0},
                    {0, 1, 0}
            }
    }, Color.PURPLE),

    L(new int[][][]{
            {
                    {0, 0, 1},
                    {1, 1, 1},
                    {0, 0, 0}
            },
            {
                    {0, 1, 0},
                    {0, 1, 0},
                    {0, 1, 1}
            },
            {
                    {0, 0, 0},
                    {1, 1, 1},
                    {1, 0, 0}
            },
            {
                    {1, 1, 0},
                    {0, 1, 0},
                    {0, 1, 0}
            }
    }, Color.ORANGE),

    J(new int[][][]{
            {
                    {1, 0, 0},
                    {1, 1, 1},
                    {0, 0, 0}
            },
            {
                    {0, 1, 1},
                    {0, 1, 0},
                    {0, 1, 0}
            },
            {
                    {0, 0, 0},
                    {1, 1, 1},
                    {0, 0, 1}
            },
            {
                    {0, 1, 0},
                    {0, 1, 0},
                    {1, 1, 0}
            }
    }, Color.BLUE),

    S(new int[][][]{
            {
                    {0, 1, 1},
                    {1, 1, 0},
                    {0, 0, 0}
            },
            {
                    {0, 1, 0},
                    {0, 1, 1},
                    {0, 0, 1}
            },
            {
                    {0, 0, 0},
                    {0, 1, 1},
                    {1, 1, 0}
            },
            {
                    {1, 0, 0},
                    {1, 1, 0},
                    {0, 1, 0}
            }
    }, Color.LIMEGREEN),

    Z(new int[][][]{
            {
                    {1, 1, 0},
                    {0, 1, 1},
                    {0, 0, 0}
            },
            {
                    {0, 0, 1},
                    {0, 1, 1},
                    {0, 1, 0}
            },
            {
                    {0, 0, 0},
                    {1, 1, 0},
                    {0, 1, 1}
            },
            {
                    {0, 1, 0},
                    {1, 1, 0},
                    {1, 0, 0}
            }
    }, Color.RED);

    private final int[][][] rotations;
    private final Color color;

    TetrominoShape(int[][][] rotations, Color color) {
        this.rotations = rotations;
        this.color = color;
    }

    public int[][] getRotation(int index) {
        return rotations[index % 4];
    }

    public Color getColor() {
        return color;
    }

    public int getColorIndex() {
        return ordinal() + 1;
    }
}
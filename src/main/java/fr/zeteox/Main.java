package fr.zeteox;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    public final static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Starting Java Tetris");
        Application.launch(TetrisApp.class, args);
    }
}

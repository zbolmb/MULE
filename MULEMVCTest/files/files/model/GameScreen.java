package files.model;

import files.controller.LoopService;
import files.controller.Util;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * Content contains the game screen where main game takes place
 * gameText is the text that flashes on top of the map
 * contains mouse handlers for initial plot selection
 * contains keypress handler for if player skips (pressing p)
 * IF ANYTHING IS REQUIRED FOR GAMESCREEN, INITIALIZE THAT
 * CONTENT UNDER updateDC()
 * @author Zhijian
 *
 */
public class GameScreen {

    private String gameText;

    /**
     * GameScreen method
     */
    public GameScreen() {
        gameText = "";
    }
    
    /**
     * getter for gameText
     * @return String gameText
     */
    public String getGameText() {
        return gameText;
    }
    
    /**
     * setter for gameText
     * @param gameText the string to set
     */
    public void setGameText(String gameText) {
        this.gameText = gameText;
    }
}

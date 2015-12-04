package files.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class PlayerSettingsView {
    
    private Scene[] scenes;
    private Parent[] roots;
    
    /**
     * constructor for PlayerSettingsView
     */
    public PlayerSettingsView() {
        scenes = new Scene[4];
        roots = new Parent[4];
    }
    
    /**
     * getter for scenes
     * @param i index
     * @return Scene the scene
     */
    public Scene get(int i) {
        return scenes[i];
    }
    
    /**
     * getter for roots
     * @param i the index
     * @return Parent the root
     */
    public Parent getLayout(int i) {
        return roots[i];
    }
    
    /**
     * adder
     * @param i the index
     * @param n the Node to add
     * @param row the row
     * @param col the col
     */
    public void add(int i, Node n, int row, int col) {
        ((GridPane)roots[i]).add(n, row, col);
    }
    
    /**
     * setter for scenes
     * @param scenes the scenes
     */
    public void setScenes(Scene[] scenes) {
        this.scenes = scenes;
    }
    
}

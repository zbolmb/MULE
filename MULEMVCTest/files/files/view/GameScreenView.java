package files.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GameScreenView extends Scene {
    
    GridPane layout;
    Text gameText;
    
    /**
     * Constructor for GameScreenView
     * @param layout
     */
    public GameScreenView(Parent layout) {
        super(layout, 940, 610);
        this.layout = (GridPane) layout;
        this.gameText = new Text();
    }
    
    /**
     * getter for layout
     * @return GridPane the layout
     */
    public GridPane getLayout() {
        return layout;
    }
    
    /**
     * getter for gameText
     * @return Text the gameText
     */
    public Text getGameText() {
        return gameText;
    }
    
    /**
     * setter for gameText
     * @param gameText the text to set
     */
    public void setGameText(String text) {
        gameText.setText(text);
    }
    
    /**
     * adds
     * @param n the Node to add
     */
    public void add(Node n) {
    }
    
    /**
     * remove 
     * @param n the Node to remove
     */
    public void remove(Node n) {
        layout.getChildren().remove(n);
    }
    
}

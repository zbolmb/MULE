package files.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class StoreView extends Scene {

    Pane root;
    Text text;
    
    /**
     * constructor for StoreView
     */
    public StoreView(Parent root) {
        super(root, 800, 500);
        this.root = (Pane) root;
        text = new Text();
    }
    
    /**
     * adder
     */
    public void add(Node n) {
        root.getChildren().add(n);
    }
    
    /**
     * setter for text
     * @param text the string to set
     */
    public void setText(String text) {
        this.text.setText(text);
    }
    
    /**
     * getter for text
     * @return Text the text
     */
    public Text getText() {
        return text;
    }
}

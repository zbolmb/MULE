package files.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class TownMapView extends Scene {
    
    Pane root;
    
    /**
     * constructor for TownMapView
     * @param root the parent node
     */
    public TownMapView(Parent root) {
        super(root, 800, 500);
        this.root = (Pane) root;
    }
    
    /**
     * adds Node n to the root pane
     */
    public void add(Node n) {
        if (root.getChildren().contains(n)) {
            root.getChildren().remove(n);
        }
        root.getChildren().add(n);
    }
    
    /**
     * removes Node n from the root pane
     */
    public void remove(Node n) {
        root.getChildren().remove(n);
    }
}

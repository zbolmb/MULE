package files.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class IntroScreenView extends Scene {

    GridPane root;
    
    /**
     * constructor
     * @param root the root
     */
    public IntroScreenView(Parent root) {
        super(root, 450, 300);
        this.root = (GridPane) root;
    }

    /**
     * adder
     * @param n the node
     * @param row the row
     * @param col the col
     */
    public void add(Node n, int row, int col) {
        root.add(n, row, col);
    }
    
    /**
     * getter for root
     * @return GridPane the root
     */
    public GridPane getLayout() {
        return root;
    }
    
}

package files.model;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.IOException;

public class Store {

    protected String stats;
    protected int smithore;
    protected int energy;
    protected int food;
    protected int crystite;
    protected int mule1;
    protected int mule2;
    protected int mule3;

    /**
     * constructor for Store
     */
    public Store() {
        stats = "";
        if (Configurations.getDifficulty().equals("Easy")) {
            food = 16;
            energy = 16;
            smithore = 0;
            crystite = 0;
            mule1 = 10;
            mule2 = 10;
            mule3 = 10;
        } else {
            food = 8;
            energy = 8;
            smithore = 8;
            crystite = 0;
            mule1 = 5;
            mule2 = 5;
            mule3 = 5;
        }
    }
    
    /**
     * setter for text
     * @param text string of text
     */
    public void setText(String text) {
        this.stats = text;
    }

    /**
     * getter for mules
     * @return int][] mules
     */
    public int[] getMules() {
        int[] mules = {mule1, mule2, mule3};
        return mules;
    }

    /**
     * getter for smithore
     * @return int smithore
     */
    public int getSmithore() {
        return smithore;
    }

    /**
     * getter for energy
     * @return int energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * getter for food
     * @return int food
     */
    public int getFood() {
        return food;
    }

    /**
     * getter for crystite
     * @return int crystite
     */
    public int getCrystite() {
        return crystite;
    }

    /**
     * setter for smithore
     * @param s number of smithore
     */
    public void setSmithore(int s) {
        smithore = s;
    }

    /**
     * setter for energy
     * @param e number of energy
     */
    public void setEnergy(int e) {
        energy = e;
    }

    /**
     * setter for food
     * @param f number of food
     */
    public void setFood(int f) {
        food = f;
    }

    /**
     * setter for crystite
     * @param c number of crystite
     */
    public void setCrystite(int c) {
        crystite = c;
    }

    /**
     * getter for mule1
     * @return int mule1
     */
    public int getMule1() {
        return mule1;
    }
    /**
     * setter for mule1
     * @param mule1 mule
     */
    public void setMule1(int mule1) {
        this.mule1 = mule1;
    }

    /**
     * getter for mule2
     * @return int mule2
     */
    public int getMule2() {
        return mule2;
    }
    
    /**
     * setter for mule2
     * @param mule2 mule
     */
    public void setMule2(int mule2) {
        this.mule2 = mule2;
    }

    /**
     * getter for mule3
     * @return int mule3
     */
    public int getMule3() {
        return mule3;
    }
    
    /**
     * setter for mule3
     * @param mule3 mule
     */
    public void setMule3(int mule3) {
        this.mule3 = mule3;
    }
    
    public String getText() {
        return stats;
    }
}

package Model;

import java.util.ArrayList;

import Controller.DisplayContents;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
public class Store {

    protected static DisplayContents dc;
    protected Text stats;
    protected Player cp;
    protected int smithore;
    protected int energy;
    protected int food;
    protected int crystite;
    protected int mule1;
    protected int mule2;
    protected int mule3;

    public Store() {
        dc = Configurations.getDisplayContents();
        cp = Configurations.getCurPlayer();
        stats = new Text();
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
     * 
     * @param resource
     * 0 = smithore;
     * 1 = crystite;
     * 2 = energy;
     * 3 = food;
     */
    public void buy(int resource) {
        cp = Configurations.getCurPlayer();
        if (resource == 0 && smithore > 0 && cp.getMoney() >= 100) {
            cp.setSmithore(cp.getSmithore() + 1);
            smithore--;
            cp.setMoney(cp.getMoney() - 100);
        } else if (resource == 1 && crystite > 0 && cp.getMoney() >= 200) {
            cp.setCrystite(cp.getCrystite() + 1);
            crystite--;
            cp.setMoney(cp.getMoney() - 200);

        } else if (resource == 2 && energy > 0 && cp.getMoney() >= 25) {
            cp.setEnergy(cp.getEnergy() + 1);
            energy--;
            cp.setMoney(cp.getMoney() - 25);
        } else if (resource == 3 && food > 0 && cp.getMoney() >= 50) {
            cp.setFood(cp.getFood() + 1);
            food--;
            cp.setMoney(cp.getMoney() - 50);
        } else {
            System.out.println("Not enough money or store does not carry");
        }
    }

    public void sell(int resource) {
        cp = Configurations.getCurPlayer();
        if (resource == 0 && cp.getSmithore() > 0) {
            cp.setSmithore(cp.getSmithore() - 1);
            smithore++;
            cp.setMoney(cp.getMoney() + 100);
        } else if (resource == 1 && cp.getCrystite() > 0) {
            cp.setCrystite(cp.getCrystite() - 1);
            crystite++;
            cp.setMoney(cp.getMoney() + 200);

        } else if (resource == 2 && cp.getEnergy() > 0) {
            cp.setEnergy(cp.getEnergy() - 1);
            energy++;
            cp.setMoney(cp.getMoney() + 25);
        } else if (resource == 3 && cp.getFood() > 0) {
            cp.setFood(cp.getFood() - 1);
            food++;
            cp.setMoney(cp.getMoney() + 50);
        } else {
            System.out.println("Can't sell, do not have enough of that item");
        }
    }

    public void buyMule(int mule) {
        cp = Configurations.getCurPlayer();
        if (cp.getMoney() >= 300) {
            cp.setMoney(cp.getMoney() - 300);
            if (mule == 1) {
                cp.setMule1(cp.getMule1() + 1);
                mule1--;
            }
            else if (mule == 2) {
                cp.setMule2(cp.getMule2() + 1);
                mule2--;
            }
            else {
                cp.setMule3(cp.getMule3() + 1);
                mule3--;
            }
        } else {
            System.out.print("Not enoough money");
        }
    }

    public Scene getGUI() {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 800, 500);

        //Buy Buttons
        Button buyMule1, buyMule2, buyMule3, buySmithore, buyCrystite, buyFood, buyEnergy;
        //Sell Buttons
        Button sellSmithore, sellCrystite, sellFood, sellEnergy;
        //Text

        Button back;

        // -------------------- Action Handlers of Buttons ---------------------

        buyMule1 = new Button("Buy Food Mule");
        buyMule1.setOnAction(e -> buyMule(1));
        buyMule2 = new Button("Buy Energy Mule");
        buyMule2.setOnAction(e -> buyMule(2));
        buyMule3 = new Button("Buy Ore Mule");
        buyMule3.setOnAction(e -> buyMule(3));
        buySmithore = new Button("Buy Smithores");
        buySmithore.setOnAction(e -> buy(0));
        buyCrystite = new Button("Buy Crystites");
        buyCrystite.setOnAction(e -> buy(1));
        buyFood = new Button("Buy Food");
        buyFood.setOnAction(e -> buy(2));
        buyEnergy = new Button("Buy Energy");
        buyEnergy.setOnAction(e -> buy(3));
        
        sellSmithore = new Button("Sell Smithores");
        sellSmithore.setOnAction(e -> sell(0));
        sellCrystite = new Button("Sell Crystites");
        sellCrystite.setOnAction(e -> sell(1));
        sellFood = new Button("Sell Food");
        sellFood.setOnAction(e -> sell(3));
        sellEnergy = new Button("Sell Energy");
        sellEnergy.setOnAction(e -> sell(2));

        updateText();        

        back = new Button("Back");
        back.setOnAction(e -> {
            dc.getMainWindow().setScene(dc.getTownMapGUI());
            Player cp = Configurations.getCurPlayer();
            cp.setPhase(1);
            cp.setX(720);
            cp.setY(250);
        });

        VBox vbox1 = new VBox();
        VBox vbox2 = new VBox();
        HBox hbox = new HBox();
        vbox1.getChildren().addAll(buyMule1, buyMule2, buyMule3, buySmithore, buyCrystite, buyFood, buyEnergy, back);
        vbox2.getChildren().addAll(sellSmithore, sellCrystite, sellFood, sellEnergy);
        hbox.getChildren().addAll(vbox1, vbox2, stats);
        pane.getChildren().add(hbox);

        return scene;
    }

    public void updateDC() {
        dc.setStoreGUI(getGUI());
        dc.setStore(this);
    }
    
    public void updateText() {
        stats.setText("Smithore($100): " + smithore
                + "\nCrystite($200): " + crystite
                + "\nFood($50): " + food
                + "\nEnergy($25): " + energy
                + "\nFood Mule($300): " + mule1
                + "\nEnergy Mule($300): " + mule2
                + "\nOre Mule($300): " + mule3
                + "\nMoney : $" + Configurations.getCurPlayer().getMoney());
    }
}

import java.util.ArrayList;

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
        dc = Configurations.displayContents;
        cp = Configurations.curPlayer;
        if (Configurations.difficulty.equals("Easy")) {
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
        cp = Configurations.curPlayer;
        if (resource == 0 && smithore > 0 && cp.money > 100) {
            cp.smithore++;
            smithore--;
            cp.money = cp.money - 100;
        } else if (resource == 1 && crystite > 0 && cp.money > 200) {
            cp.crystite++;
            crystite--;
            cp.money = cp.money - 200;

        } else if (resource == 2 && energy > 0 && cp.money > 25) {
            cp.energy++;
            energy--;
            cp.money = cp.money - 25;
        } else if (resource == 3 && food > 0 && cp.money > 50) {
            cp.food++;
            food--;
            cp.money = cp.money - 50;
        } else {
            System.out.println("Not enough money or store does not carry");
        }
    }

    public void sell(int resource) {
        cp = Configurations.curPlayer;
        if (resource == 0 && cp.smithore > 0) {
            cp.smithore--;
            smithore++;
            cp.money = cp.money + 100;
        } else if (resource == 1 && cp.crystite > 0) {
            cp.crystite--;
            crystite++;
            cp.money = cp.money + 200;

        } else if (resource == 2 && cp.energy > 0) {
            cp.energy--;
            energy++;
            cp.money = cp.money + 25;
        } else if (resource == 3 && cp.food > 0) {
            cp.food--;
            food++;
            cp.money = cp.money + 50;
        } else {
            System.out.println("Can't sell, do not have enough of that item");
        }
    }

    public void buyMule(int mule) {
        cp = Configurations.curPlayer;
        if (cp.money > 300) {
            cp.money -= 300;
            if (mule == 1) {
                cp.mule1++;
                mule1--;
            }
            else if (mule == 2) {
                cp.mule2++;
                mule2--;
            }
            else {
                cp.mule3++;
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

        buyMule1 = new Button("Buy Mule #1");
        buyMule1.setOnAction(e -> buyMule(1));
        buyMule2 = new Button("Buy Mule #2");
        buyMule2.setOnAction(e -> buyMule(2));
        buyMule3 = new Button("Buy Mule #3");
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

        stats = new Text("Smithore : " + smithore
                + "\nCrystite : " + crystite
                + "\nFood : " + food
                + "\nEnergy : " + energy
                + "\nFood Mule : " + mule1
                + "\nEnergy Mule : " + mule2
                + "\nOre Mule : " + mule3);
        

        back = new Button("Back");
        back.setOnAction(e -> {
            dc.mainWindow.setScene(dc.townMapGUI);
            Player cp = Configurations.curPlayer;
            cp.phase = 1;
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
        dc.storeGUI = getGUI();
        dc.store = this;
    }
    
    public void updateText() {
        stats.setText("Smithore : " + smithore
                + "\nCrystite : " + crystite
                + "\nFood : " + food
                + "\nEnergy : " + energy
                + "\nFood Mule : " + mule1
                + "\nEnergy Mule : " + mule2
                + "\nOre Mule : " + mule3
                + "\nMoney : " + Configurations.curPlayer.money);
    }
}

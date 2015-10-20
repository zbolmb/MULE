import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
public class Store {

    protected static DisplayContents dc;
    protected ArrayList<Player> players;
    protected Player currPlayer;
    protected Resource smithore;
    protected Resource energy;
    protected Resource food;
    protected Resource crystite;
    protected Resource mule1;
    protected Resource mule2;
    protected Resource mule3;

    public Store() {
        dc = Configurations.displayContents;
        players = Configurations.players;
        smithore = new Resource("Smithore", 100);
        energy = new Resource("Energy", 25);
        food = new Resource("Food", 50);
        crystite = new Resource("crystite", 200);
        mule1 = new Resource("SmithMule", 300);
        mule2 = new Resource("EnergyMule", 300);
        mule3 = new Resource("FoodMule", 300);
    }

    public void buy(Resource resource) {
        if (resource.name.equals(smithore.name) && smithore.storeCount > 0
            && currPlayer.money > smithore.cost) {
                currPlayer.smithore++;
                smithore.storeCount--;
                currPlayer.money = currPlayer.money - smithore.cost;
        } else if (resource.name.equals(crystite.name) && crystite.storeCount > 0
            && currPlayer.money > crystite.cost) {
                currPlayer.crystite++;
                crystite.storeCount--;
                currPlayer.money = currPlayer.money - crystite.cost;

        } else if (resource.name.equals(energy.name) && energy.storeCount > 0
            && currPlayer.money > energy.cost) {
                currPlayer.energy++;
                energy.storeCount--;
                currPlayer.money = currPlayer.money - energy.cost;
        } else if (resource.name.equals(food.name) && food.storeCount > 0
            && currPlayer.money > food.cost) {
                currPlayer.food++;
                food.storeCount--;
                currPlayer.money = currPlayer.money - food.cost;

        } else {
            System.out.println("Not enough money or store does not carry");
        }

    }

    public void sell(Resource resource) {
        if (resource.name.equals(smithore.name) && currPlayer.smithore > 0) {
                currPlayer.smithore--;
                smithore.storeCount++;
                currPlayer.money = currPlayer.money + smithore.cost;
        } else if (resource.name.equals(crystite.name) && currPlayer.crystite > 0) {
                currPlayer.crystite--;
                crystite.storeCount++;
                currPlayer.money = currPlayer.money + crystite.cost;

        } else if (resource.name.equals(energy.name) && currPlayer.energy > 0) {
                currPlayer.energy--;
                energy.storeCount++;
                currPlayer.money = currPlayer.money + energy.cost;
        } else if (resource.name.equals(food.name) && currPlayer.food > 0) {
                currPlayer.food--;
                food.storeCount++;
                currPlayer.money = currPlayer.money + food.cost;

        } else {
            System.out.println("Can't sell, store does not contain item");
        }
    }

    public void buyMule(Resource resource) {
        if (resource.name.equals(mule1.name) && currPlayer.money > mule1.cost) {
            currPlayer.mule1++;
            currPlayer.money -= mule1.cost;
        } else if (resource.name.equals(mule2.name) && currPlayer.money > mule2.cost) {
            currPlayer.mule2++;
            currPlayer.money -= mule2.cost;
        } else if (resource.name.equals(mule3.name) && currPlayer.money > mule3.cost) {
            currPlayer.mule3++;
            currPlayer.money -= mule3.cost;
        } else {
            System.out.print("Not enoough money");
        }
    }
    public void setCurrent(Player curr) {
        currPlayer = curr;
    }
    
    public Scene getGUI() {
        
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 800, 500);
        
        //Buy Buttons
        Button buyMule1, buyMule2, buyMule3, buySmithore, buyCrystite, buyFood, buyEnergy;
        //Sell Buttons
        Button sellMule1, sellMule2, sellMule3, sellSmithore, sellCrystite, sellFood, sellEnergy;
        
        //Set up sell buttons
        sellMule1 = new Button("Sell Mule #1");
        sellMule2 = new Button("Sell Mule #2");
        sellMule3 = new Button("Sell Mule #3");
        sellSmithore = new Button("Sell Smithores");
        sellCrystite = new Button("Sell Crystites");
        sellFood = new Button("Sell Food");
        sellEnergy = new Button("Sell Energy");

        Button back = new Button("Back");

        // -------------------- Action Handlers of Buttons ---------------------

        buyMule1 = new Button("Buy Mule #1");
        buyMule1.setOnAction(e -> {
            buyMule(mule1);
        });

        buyMule2 = new Button("Buy Mule #2");
        buyMule2.setOnAction(e -> {
            buyMule(mule2);
        });

        buyMule3 = new Button("Buy Mule #3");
        buyMule3.setOnAction(e -> {
            buyMule(mule3);
        });

        buySmithore = new Button("Buy Smithores");
        buySmithore.setOnAction(e -> {
            buy(smithore);
        });

        buyCrystite = new Button("Buy Crystites");
        buyCrystite.setOnAction(e -> {
            buy(crystite);
        });

        buyFood = new Button("Buy Food");
        buyFood.setOnAction(e -> {
            buy(food);
        });

        buyEnergy = new Button("Buy Energy");
        buyEnergy.setOnAction(e -> {
            buy(energy);
        });

        back.setOnAction(e -> {
            dc.mainWindow.setScene(dc.townMapGUI);
            Player cp = Configurations.curPlayer;
            cp.setX(720);
            cp.setY(250);
        });

        VBox vbox1 = new VBox();
        VBox vbox2 = new VBox();
        HBox hbox = new HBox();
        vbox1.getChildren().addAll(buyMule1, buyMule2, buyMule3, buySmithore, buyCrystite, buyFood, buyEnergy, back);
        vbox2.getChildren().addAll(sellMule1, sellMule2, sellMule3, sellSmithore, sellCrystite, sellFood, sellEnergy);
        hbox.getChildren().addAll(vbox1, vbox2);
        pane.getChildren().add(hbox);
        
        return scene;
    }
    
    public void updateDC() {
        dc.store = this;
    }
}

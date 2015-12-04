package files.controller;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import files.model.Configurations;
import files.model.Player;
import files.model.Store;
import files.view.StoreView;
import files.view.TownMapView;

public class StoreController {

    Store store;
    StoreView storeView;
    Stage mainWindow;
    TownMapController townMapController;
    
    /**
     * constructor for StoreController
     */
    public StoreController(Stage mainWindow) {
        store = new Store();
        storeView = new StoreView(new Pane());
        this.mainWindow = mainWindow;
    }
    
    /**
     * setup
     * @param townMapView the townMapView
     */
    public void setup(TownMapController townMapController) {
        this.townMapController = townMapController;
    }
    

    /**
     * @param resource
     * 0 = smithore;
     * 1 = crystite;
     * 2 = energy;
     * 3 = food;
     */
    public void buy(int resource) {
        Player cp = Configurations.getCurPlayer();
        if (resource == 0 && store.getSmithore() > 0 && cp.getMoney() >= 100) {
            cp.setSmithore(cp.getSmithore() + 1);
            store.setSmithore(store.getSmithore() - 1);
            cp.setMoney(cp.getMoney() - 100);
        } else if (resource == 1 && store.getCrystite() > 0 && cp.getMoney() >= 200) {
            cp.setCrystite(cp.getCrystite() + 1);
            store.setCrystite(store.getCrystite() - 1);
            cp.setMoney(cp.getMoney() - 200);

        } else if (resource == 2 && store.getEnergy() > 0 && cp.getMoney() >= 25) {
            cp.setEnergy(cp.getEnergy() + 1);
            store.setEnergy(store.getEnergy() - 1);
            cp.setMoney(cp.getMoney() - 25);
        } else if (resource == 3 && store.getFood() > 0 && cp.getMoney() >= 50) {
            cp.setFood(cp.getFood() + 1);
            store.setFood(store.getFood() - 1);
            cp.setMoney(cp.getMoney() - 50);
        }
    }

    /**
     * sell method
     * @param resource resource
     */
    public void sell(int resource) {
        Player cp = Configurations.getCurPlayer();
        if (resource == 0 && cp.getSmithore() > 0) {
            cp.setSmithore(cp.getSmithore() - 1);
            store.setSmithore(store.getSmithore() + 1);
            cp.setMoney(cp.getMoney() + 100);
        } else if (resource == 1 && cp.getCrystite() > 0) {
            cp.setCrystite(cp.getCrystite() - 1);
            store.setCrystite(store.getCrystite() + 1);
            cp.setMoney(cp.getMoney() + 200);
        } else if (resource == 2 && cp.getEnergy() > 0) {
            cp.setEnergy(cp.getEnergy() - 1);
            store.setEnergy(store.getEnergy() + 1);
            cp.setMoney(cp.getMoney() + 25);
        } else if (resource == 3 && cp.getFood() > 0) {
            cp.setFood(cp.getFood() - 1);
            store.setFood(store.getFood() + 1);
            cp.setMoney(cp.getMoney() + 50);
        }
    }

    /**
     * buyMule method
     * @param mule mule
     */
    public void buyMule(int mule) {
        Player cp = Configurations.getCurPlayer();
        if (cp.getMoney() >= 300) {
            if (mule == 1) {
                if (store.getMule1() > 0) {
                    cp.setMule1(cp.getMule1() + 1);
                    store.setMule1(store.getMule1() - 1);
                    cp.setMoney(cp.getMoney() - 300);
                }
            } else if (mule == 2) {
                if (store.getMule2() > 0) {
                    cp.setMule2(cp.getMule2() + 1);
                    store.setMule2(store.getMule2() - 1);
                    cp.setMoney(cp.getMoney() - 300);
                }
            } else {
                if (store.getMule3() > 0) {
                    cp.setMule3(cp.getMule3() + 1);
                    store.setMule3(store.getMule3() - 1);
                    cp.setMoney(cp.getMoney() - 300);
                }
            }
        }
    }

    /**
     * getGui method
     * @throws IOException exception
     */
    public void updateView() throws IOException {

        //Buy Buttons
        Button buyMule1;
        Button buyMule2;
        Button buyMule3;
        Button buySmithore;
        Button buyCrystite;
        Button buyFood;
        Button buyEnergy;
        //Sell Buttons
        Button sellSmithore;
        Button sellCrystite;
        Button sellFood;
        Button sellEnergy;
        //Text

        Button back;

        // ------------------ Action Handlers of Buttons --------------------

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

        // save button
        Button save = new Button("Save");
        save.setOnAction(e -> {
                try {
                    files.controller.Save.save();
                } catch (IOException x) {
                    x.printStackTrace();
                }
            });

        updateText();
        back = new Button("Back");
        back.setOnAction(e -> {
                mainWindow.setScene(townMapController.getView());
                Player cp = Configurations.getCurPlayer();
                cp.setPhase(1);
                cp.setX(720);
                cp.setY(250);
            });

        VBox vbox1 = new VBox();
        VBox vbox2 = new VBox();
        HBox hbox = new HBox();
        vbox1.getChildren().addAll(buyMule1, buyMule2,
                buyMule3, buySmithore, buyCrystite, buyFood, buyEnergy, back);
        vbox2.getChildren().addAll(sellSmithore,
                sellCrystite, sellFood, sellEnergy);
        hbox.getChildren().addAll(vbox1, vbox2, save, storeView.getText());
        storeView.add(hbox);
    }
    

    /**
     * updateText method
     */
    public void updateText() {
        store.setText("Smithore($100): " + store.getSmithore()
                + "\nCrystite($200): " + store.getCrystite()
                + "\nFood($50): " + store.getFood()
                + "\nEnergy($25): " + store.getEnergy()
                + "\nFood Mule($300): " + store.getMule1()
                + "\nEnergy Mule($300): " + store.getMule2()
                + "\nOre Mule($300): " + store.getMule3()
                + "\nMoney : $" + Configurations.getCurPlayer().getMoney());
        storeView.setText(store.getText());
    }
    
    public StoreView getView() {
        return storeView;
    }

}

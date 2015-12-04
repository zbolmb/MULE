package files.model;

import files.controller.DisplayContents;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.IOException;

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
    protected int mule4;

    /**
     * constructor for Store
     */
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
            mule4 = 10;
        } else {
            food = 8;
            energy = 8;
            smithore = 8;
            crystite = 0;
            mule1 = 5;
            mule2 = 5;
            mule3 = 5;
            mule4 = 5;
        }
    }

    /**
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
            if (cp.getRace() == "Elin") {
                cp.setMoney(cp.getMoney() - 90);
            } else {
                cp.setMoney(cp.getMoney() - 100);
            }
        } else if (resource == 1 && crystite > 0 && cp.getMoney() >= 200) {
            cp.setCrystite(cp.getCrystite() + 1);
            crystite--;
            if (cp.getRace() == "Blood Elves") {
                cp.setMoney(cp.getMoney() - 190);
            } else {
                cp.setMoney(cp.getMoney() - 200);
            }

        } else if (resource == 2 && energy > 0 && cp.getMoney() >= 25) {
            cp.setEnergy(cp.getEnergy() + 1);
            energy--;
            if (cp.getRace() == "Orc") {
                cp.setMoney(cp.getMoney() - 15);
            } else {
                cp.setMoney(cp.getMoney() - 25);
            }
        } else if (resource == 3 && food > 0 && cp.getMoney() >= 50) {
            cp.setFood(cp.getFood() + 1);
            food--;
            if (cp.getRace() == "High Humans") {
                cp.setMoney(cp.getMoney() - 40);
            } else {
                cp.setMoney(cp.getMoney() - 50);
            }
        }
    }

    /**
     * sell method
     * @param resource resource
     */
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
            //System.out.println("HI");
            cp.setEnergy(cp.getEnergy() - 1);
            energy++;
            cp.setMoney(cp.getMoney() + 25);
        } else if (resource == 3 && cp.getFood() > 0) {
            cp.setFood(cp.getFood() - 1);
            food++;
            cp.setMoney(cp.getMoney() + 50);
        }
    }

    /**
     * buyMule method
     * @param mule mule
     */
    public void buyMule(int mule) {
        //System.out.println("heyoh");
        cp = Configurations.getCurPlayer();
        if (cp.getMoney() >= 300) {
            if (mule == 1) {
                if (mule1 > 0) {
                    cp.setMule1(cp.getMule1() + 1);
                    mule1--;
                    if (cp.getRace() == "Protoss") {
                        //System.out.println("pls1");
                        cp.setMoney(cp.getMoney() - 290);
                    } else {
                        //System.out.println("why1");
                        cp.setMoney(cp.getMoney() - 300);
                    }
                }
            } else if (mule == 2) {
                if (mule2 > 0) {
                    cp.setMule2(cp.getMule2() + 1);
                    mule2--;
                    if (cp.getRace() == "Protoss") {
                        //System.out.println("pls1");
                        cp.setMoney(cp.getMoney() - 290);
                    } else {
                        //System.out.println("why2");
                        cp.setMoney(cp.getMoney() - 300);
                    }
                }
            } else if (mule == 3){
                if (mule3 > 0) {
                    cp.setMule3(cp.getMule3() + 1);
                    mule3--;
                    if (cp.getRace() == "Protoss") {
                        //System.out.println("pls1");
                        cp.setMoney(cp.getMoney() - 290);
                    } else {
                        //System.out.println("why3");
                        cp.setMoney(cp.getMoney() - 300);
                    }
                }
            } else {
                cp.setMule4(cp.getMule4() + 1);
                mule4--;
                if (cp.getRace() == "Protoss") {
                    //System.out.println("pls1");
                    cp.setMoney(cp.getMoney() - 290);
                } else {
                    //System.out.println("why3");
                    cp.setMoney(cp.getMoney() - 300);
                }
            }
        }
    }

    /**
     * getGui method
     * @return Scene data
     * @throws IOException exception
     */
    public Scene getGUI() throws IOException {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 800, 500);

        //Buy Buttons
        Button buyMule1;
        Button buyMule2;
        Button buyMule3;
        Button buyMule4;
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
        buyMule4 = new Button("Buy Crystite Mule");
        buyMule4.setOnAction(e -> buyMule(4));
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
                dc.getMainWindow().setScene(dc.getTownMapGUI());
                Player cp = Configurations.getCurPlayer();
                cp.setPhase(1);
                cp.setX(720);
                cp.setY(250);
            });

        VBox vbox1 = new VBox();
        VBox vbox2 = new VBox();
        HBox hbox = new HBox();
        vbox1.getChildren().addAll(buyMule1, buyMule2,
                buyMule3, buyMule4, buySmithore, buyCrystite, buyFood, buyEnergy, back);
        vbox2.getChildren().addAll(sellSmithore,
                sellCrystite, sellFood, sellEnergy);
        hbox.getChildren().addAll(vbox1, vbox2, save, stats);
        pane.getChildren().add(hbox);

        return scene;
    }

    /**
     * updateDC method
     */
    public void updateDC() {
        try {
            dc.setStoreGUI(getGUI());
            dc.setStore(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * updateText method
     */
    public void updateText() {
        if (Configurations.getCurPlayer().getRace() == "Elin") {
            //System.out.print("pls");
            stats.setText("Smithore($90): " + smithore
                    + "\nCrystite($200): " + crystite
                    + "\nFood($50): " + food
                    + "\nEnergy($25): " + energy
                    + "\nFood Mule($300): " + mule1
                    + "\nEnergy Mule($300): " + mule2
                    + "\nOre Mule($300): " + mule3
                    + "\nCrystite Mule($300): " + mule4
                    + "\nMoney : $" + Configurations.getCurPlayer().getMoney());
        } else if (Configurations.getCurPlayer().getRace() == "Blood Elves") {
            stats.setText("Smithore($100): " + smithore
                    + "\nCrystite($190): " + crystite
                    + "\nFood($50): " + food
                    + "\nEnergy($25): " + energy
                    + "\nFood Mule($300): " + mule1
                    + "\nEnergy Mule($300): " + mule2
                    + "\nOre Mule($300): " + mule3
                    + "\nCrystite Mule($300): " + mule4
                    + "\nMoney : $" + Configurations.getCurPlayer().getMoney());
        } else if (Configurations.getCurPlayer().getRace() == "Orc") {
            stats.setText("Smithore($100): " + smithore
                    + "\nCrystite($200): " + crystite
                    + "\nFood($50): " + food
                    + "\nEnergy($15): " + energy
                    + "\nFood Mule($300): " + mule1
                    + "\nEnergy Mule($300): " + mule2
                    + "\nOre Mule($300): " + mule3
                    + "\nCrystite Mule($300): " + mule4

                    + "\nMoney : $" + Configurations.getCurPlayer().getMoney());
        } else if (Configurations.getCurPlayer().getRace() == "High Humans") {
            stats.setText("Smithore($100): " + smithore
                    + "\nCrystite($200): " + crystite
                    + "\nFood($40): " + food
                    + "\nEnergy($25): " + energy
                    + "\nFood Mule($300): " + mule1
                    + "\nEnergy Mule($300): " + mule2
                    + "\nOre Mule($300): " + mule3
                    + "\nCrystite Mule($300): " + mule4
                    + "\nMoney : $" + Configurations.getCurPlayer().getMoney());
        } else if (Configurations.getCurPlayer().getRace() == "Protoss") {
            stats.setText("Smithore($100): " + smithore
                    + "\nCrystite($200): " + crystite
                    + "\nFood($50): " + food
                    + "\nEnergy($25): " + energy
                    + "\nFood Mule($290): " + mule1
                    + "\nEnergy Mule($290): " + mule2
                    + "\nOre Mule($290): " + mule3
                    + "\nCrystite Mule($300): " + mule4

                    + "\nMoney : $" + Configurations.getCurPlayer().getMoney());
        } else {
        stats.setText("Smithore($100): " + smithore
                + "\nCrystite($200): " + crystite
                + "\nFood($50): " + food
                + "\nEnergy($25): " + energy
                + "\nFood Mule($300): " + mule1
                + "\nEnergy Mule($300): " + mule2
                + "\nOre Mule($300): " + mule3
                + "\nCrystite Mule($300): " + mule4

                + "\nMoney : $" + Configurations.getCurPlayer().getMoney());
        }
    }

    /**
     * getter for mules
     * @return int][] mules
     */
    public int[] getMules() {
        int[] mules = {mule1, mule2, mule3, mule4};
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
     * setter for mule1
     * @param mule1 mule
     */
    public void setMule1(int mule1) {
        this.mule1 = mule1;
    }

    /**
     * setter for mule2
     * @param mule2 mule
     */
    public void setMule2(int mule2) {
        this.mule2 = mule2;
    }

    /**
     * setter for mule3
     * @param mule3 mule
     */
    public void setMule3(int mule3) {
        this.mule3 = mule3;
    }

    /**
     * setter for mule4
     * @param mule4 mule
     */
    public void setMule4(int mule4) {
        this.mule4 = mule4;
    }
}

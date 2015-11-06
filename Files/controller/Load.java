package controller;

import model.Configurations;
import model.Player;
import model.landTiles.PlainsTile;
import model.landTiles.RiverTile;
import model.landTiles.TownTile;
import model.landTiles.MapTiles;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

/**
 * This class allows the game to load previously saved games
 * @author Min Jung, William Hsu, Karl Nicodemus, Zhijian Li, Will Su
 */
public class Load {

    /**
     * loads the saved game from savedGame.txt
     * @throws IOException exception
     */
    public static void load() throws IOException {

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("savedGame.txt"));

            Configurations.setNumPlayers(Integer.parseInt(br.readLine()));
            Configurations.setDifficulty(br.readLine());
            Configurations.setMapType(br.readLine());
            Configurations.setPhase(Integer.parseInt(br.readLine()));
            Configurations.setRound(Integer.parseInt(br.readLine()));

            Player current = new Player();
            current.setName(br.readLine());

            Configurations.setCurPlayer(current);

            int numPlayers = Integer.parseInt(br.readLine());
            ArrayList<Player> list = new ArrayList<>();
            for (int i = 0; i < numPlayers; i++) {
                Player p = new Player();
                p.setPhase(Integer.parseInt(br.readLine()));
                p.setCrystite(Integer.parseInt(br.readLine()));
                p.setEnergy(Integer.parseInt(br.readLine()));
                p.setFood(Integer.parseInt(br.readLine()));
                p.setMessage(br.readLine());
                p.setMoney(Integer.parseInt(br.readLine()));
                p.setMule1(Integer.parseInt(br.readLine()));
                p.setMule2(Integer.parseInt(br.readLine()));
                p.setMule3(Integer.parseInt(br.readLine()));
                p.setName(br.readLine());
                p.setRace(br.readLine());
                p.setScore(Integer.parseInt(br.readLine()));
                String pCol = br.readLine();
                p.setColor(Color.web(pCol));

                ArrayList<model.landTiles.MapTiles> tiles = new ArrayList<>();
                int numTiles = Integer.parseInt(br.readLine());
                for (int j = 0; j < numTiles; j++) {
                    String tileType = br.readLine();
                    MapTiles tile;
                    if (tileType.equals("Plains")) {
                        tile = new PlainsTile();
                    } else if (tileType.equals("River")) {
                        tile = new RiverTile();
                    } else {
                        tile = new TownTile();
                    }
                    boolean[] mules = new boolean[3];
                    for (int k = 0; k < 3; k++) {
                        mules[k] = stringBoolean(br.readLine());
                    }
                    tile.setMules(mules);
                }

                double cX = Double.parseDouble(br.readLine());
                double cY = Double.parseDouble(br.readLine());
                double r = Double.parseDouble(br.readLine());
                String col = br.readLine();
                Color fill = Color.web(col);
                p.setPlayerIcon(new Circle(cX, cY, r, fill));
                p.setStartX(Double.parseDouble(br.readLine()));
                p.setStartY(Double.parseDouble(br.readLine()));
                p.setX(Double.parseDouble(br.readLine()));
                p.setY(Double.parseDouble(br.readLine()));
                LoopService.setTime();
            }
        } catch (IOException e) {
			e.printStackTrace();
        } finally {
             if (br != null) {
                 br.close();
             }
        }
        br.close();
    }

    /**
     * converts String to boolean
     * @param String boolean value
     */
    private static boolean stringBoolean(String str) {
        return str.equals("true");
    }
}

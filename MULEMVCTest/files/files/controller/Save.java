package files.controller;

import files.model.Configurations;
import files.model.Player;
import files.model.landTiles.MapTiles;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.scene.shape.Circle;

/**
 * Created by William on 11/4/2015.
 * The save function
 */
public class Save {
    /**
     * saves game
     * @throws IOException the exception
     */
    public static void save() throws IOException {
        try {
            PrintWriter writer = new PrintWriter("savedGame.txt");
            writer.print("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter out = null;
        try {
            //true tells to append data.
            FileWriter fstream = new FileWriter("savedGame.txt", true);
            out = new BufferedWriter(fstream);
            out.write(Integer.toString(Configurations
                    .getNumPlayers()).toString());
            out.newLine();
            out.write(Configurations.getDifficulty());
            out.newLine();
            out.write(Configurations.getMapType());
            out.newLine();
            out.write(Integer.toString(Configurations.getPhase()));
            out.newLine();
            out.write(Integer.toString(Configurations.getRound()));
            out.newLine();
            ArrayList<Player> list = Configurations.getPlayers();
            out.write(Integer.toString(list.size()));
            out.newLine();
            for (Player p: list) {
                out.write(Integer.toString(p.getPhase()));
                out.newLine();
                out.write(Integer.toString(p.getCrystite()));
                out.newLine();
                out.write(Integer.toString(p.getEnergy()));
                out.newLine();
                out.write(Integer.toString(p.getFood()));
                out.newLine();
                out.write(p.getMessage());
                out.newLine();
                out.write(Integer.toString(p.getMoney()));
                out.newLine();
                out.write(Integer.toString(p.getMule1()));
                out.newLine();
                out.write(Integer.toString(p.getMule2()));
                out.newLine();
                out.write(Integer.toString(p.getMule3()));
                out.newLine();
                out.write(p.getName());
                out.newLine();
                out.write(p.getRace());
                out.newLine();
                out.write(Integer.toString(p.getScore()));
                out.newLine();
                out.write(p.getColor().toString());
                out.newLine();
//                ArrayList<model.landTiles.MapTiles> tiles = p.getOwned();
//                out.write(Integer.toString(tiles.size()));
//                out.newLine();
//                for (int i = 0; i < tiles.size(); i++) {
//                    out.write(tiles.get(i).getName());
//                    out.newLine();
//                    boolean[] mules = tiles.get(i).getMules();
//                    for (int j = 0; j < mules.length; j++) {
//                        out.write(Boolean.toString(mules[j]));
//                        out.newLine();
//                    }
//                }
                Circle icon = p.getPlayerIcon();
                out.write(Double.toString(icon.getCenterX()));
                out.newLine();
                out.write(Double.toString(icon.getCenterY()));
                out.newLine();
                out.write(Double.toString(icon.getRadius()));
                out.newLine();
                out.write(icon.getFill().toString());
                out.newLine();
                out.write(Double.toString(p.getStartX()));
                out.newLine();
                out.write(Double.toString(p.getStartY()));
                out.newLine();
                out.write(Double.toString(p.getX()));
                out.newLine();
                out.write(Double.toString(p.getY()) + "\n");
            }
            MapTiles[][] map = Configurations.getDisplayContents()
                    .getMap().getAMap();
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    out.write(map[i][j].getOwner() + " "
                            + map[i][j].getMules()[0] + " "
                            + map[i][j].getMules()[1] + " "
                            + map[i][j].getMules()[2] + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}

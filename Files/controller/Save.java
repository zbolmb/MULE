package controller;
import model.Configurations;
import model.Player;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
            PrintWriter writer = new PrintWriter("ihatemylife.txt");
            writer.print("");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter out = null;
        try {
            //true tells to append data.
            FileWriter fstream = new FileWriter("ihatemylife.txt", true);
            out = new BufferedWriter(fstream);
            out.write(Configurations.getNumPlayers());
            out.newLine();
            out.write(Configurations.getDifficulty());
            out.newLine();
            out.write(Configurations.getMapType());
            out.newLine();
            out.write(Configurations.getPhase());
            out.newLine();
            out.write(Configurations.getRound());
            out.newLine();
            out.write(Configurations.getCurPlayer().getName());
            out.newLine();
            for (Player p: Configurations.getPlayers()) {
                out.write(p.getPhase());
                out.newLine();
                out.write(p.getCrystite());
                out.newLine();
                out.write(p.getEnergy());
                out.newLine();
                out.write(p.getFood());
                out.newLine();
                out.write(p.getMessage());
                out.newLine();
                out.write(p.getMoney());
                out.newLine();
                out.write(p.getMule1());
                out.newLine();
                out.write(p.getMule2());
                out.newLine();
                out.write(p.getMule3());
                out.newLine();
                out.write(p.getName());
                out.newLine();
                out.write(p.getRace());
                out.newLine();
                out.write(p.getScore());
                out.newLine();
                out.write(p.getColor().toString());
                out.newLine();
                ArrayList<model.landTiles.MapTiles> tiles = p.getOwned();
                out.newLine();
                for (int i = 0; i < p.getOwned().size(); i++) {
                    out.write(tiles.get(i).getName());
                    out.newLine();
                    boolean[] mules = tiles.get(i).getMules();
                    for (int j = 0; j < tiles.get(i).getMules().length; j++) {
                        out.write(Boolean.toString(mules[j]));
                        out.newLine();
                    }
                }
                out.write(p.getPlayerIcon().toString());
                out.newLine();
                out.write(Double.toString(p.getStartX()));
                out.newLine();
                out.write(Double.toString(p.getStartY()));
                out.newLine();
                out.write(Double.toString(p.getX()));
                out.newLine();
                out.write(Double.toString(p.getY()));
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
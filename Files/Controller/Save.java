package Controller;

import Model.Configurations;
import Model.Player;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import Model.landTiles.MapTiles;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by William on 11/4/2015.
 */
public class Save {
    public static void save() throws IOException {
        BufferedWriter out = null;
        try
        {
            FileWriter fstream = new FileWriter("out.txt", true); //true tells to append data.
            out = new BufferedWriter(fstream);
            out.write(Configurations.getNum_Players());
            out.write(Configurations.getDifficulty());
            out.write(Configurations.getMap_Type());
            out.write(Configurations.getPhase());
            out.write(Configurations.getRound());
			out.write(Configurations.getCurPlayer().getName());
			for (Player p: Configurations.getPlayers()) {
            	out.write(p.getPhase());
            	out.write(p.getCrystite());
            	out.write(p.getEnergy());
				out.write(p.getFood());
				out.write(p.getMessage());
				out.write(p.getMoney());
				out.write(p.getMule1());
				out.write(p.getMule2());
				out.write(p.getMule3());
				out.write(p.getName());
				out.write(p.getRace());
				out.write(p.getScore());
				out.write(p.getColor().toString());
				ArrayList<Model.landTiles.MapTiles> tiles = p.getOwned();
				for (int i = 0; i < p.getOwned().size(); i++) {
					out.write(tiles.get(i).getName());
					boolean[] mules = tiles.get(i).getMules();
					for (int j = 0; j < tiles.get(i).getMules().length; j++) {
						out.write(Boolean.toString(mules[j]));
					}
				}
				out.write(p.getPlayerIcon().toString());
				out.write(Double.toString(p.getStartX()));
				out.write(Double.toString(p.getStartY()));
				out.write(Double.toString(p.getX()));
				out.write(Double.toString(p.getY()));
			}
		}
        	catch (IOException e)
       	 {
        	    System.err.println("Error: " + e.getMessage());
        	}
        finally
        {
            if(out != null) {
                out.close();
            }
        }
    }
}

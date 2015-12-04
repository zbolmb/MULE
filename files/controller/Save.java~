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

import java.sql.*;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by William on 11/4/2015.
 * The save function
 */
public class Save {
    /**
     * saves game
     * @throws IOException the exception
     */
    public static void save() throws Exception {
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
        
        // shoots it up to database
        final String dbClassName = "com.mysql.jdbc.Driver";
        final String CONNECTION = "jdbc:mysql://localhost/save";
        Class.forName(dbClassName);
        Properties p = new Properties();
        p.put("user","mule");
        p.put("password","mule");
        Connection c = null;
        PreparedStatement pstmt = null;
        FileInputStream fis = null;
        File file = null;
        try {
            c = DriverManager.getConnection(CONNECTION,p);
            c.setAutoCommit(false);
            file = new File("savedGame.txt");
            fis = new FileInputStream(file);
            c.createStatement().execute("drop table DataFile");
            c.createStatement().execute("create table DataFile(id VARCHAR(50000))");
            pstmt = c.prepareStatement("insert into DataFile(id) values (?)");
            pstmt.setAsciiStream(1, fis, (int) file.length());
            pstmt.executeUpdate();
            c.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pstmt.close();
            file.delete();
            fis.close();
            c.close();
        }
    }
}

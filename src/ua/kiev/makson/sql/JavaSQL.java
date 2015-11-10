package ua.kiev.makson.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaSQL {
    // private AESCrypto encrypt;
    private Statement state;
    private static final Logger LOGGER = Logger.getLogger(JavaSQL.class
            .getName());

    public void createSQL() {
        try (Connection con = DriverManager
                .getConnection("jdbc:sqlite:collection.db")) {
            state = con.createStatement();
            state.execute("CREATE TABLE IF NOT EXISTS `collection` (id INTEGER PRIMARY KEY AUTOINCREMENT, `collection.nameVideo` TEXT(255), `collection.viDeoObj` TEXT(455)) ");
            state.close();
            LOGGER.log(Level.SEVERE, "create SQL");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
    }

    public void writeData(String nameVideo, String video) {
        try (Connection con = DriverManager
                .getConnection("jdbc:sqlite:collection.db")) {
            state = con.createStatement();
            // password = encrypt.encrypt(password);
            state.executeUpdate("INSERT INTO `collection` (`collection.nameVideo`, `collection.viDeoObj`) VALUES('"
                    + nameVideo + "', '" + video + "')");
            state.close();
            LOGGER.log(Level.SEVERE, "insertUsers");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
    }

    public void showAll() {
        try (Connection con = DriverManager
                .getConnection("jdbc:sqlite:collection.db")) {
            state = con.createStatement();
            ResultSet rs = state.executeQuery("SELECT * FROM `collection`");
            while (rs.next()) {
                int id = rs.getInt(1);
                String nameVideo = rs.getString("collection.nameVideo");
                String viDeoObj = rs.getString("collection.viDeoObj");
                LOGGER.log(Level.SEVERE, String.format("#%02d %s  %s%n", id,
                        nameVideo, viDeoObj));
            }
            state.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
    }

    public HashMap<Integer, String> searchVideoByName(String str) {
        HashMap<Integer, String> map = new HashMap<>();
        try (Connection con = DriverManager
                .getConnection("jdbc:sqlite:collection.db")) {
            state = con.createStatement();

            ResultSet rs = state
                    .executeQuery("SELECT * FROM `collection` WHERE  `collection.nameVideo`= '"
                            + str + "'");
            while (rs.next()) {
                int id = rs.getInt(1);
                String viDeoObj = rs.getString("collection.viDeoObj");
                map.put(id, viDeoObj);
            }
            if (map.size() > 0) {
                // Set<Entry<Integer, String>> set = map.entrySet();
                // for (Entry<Integer, String> entry : set) {
                // System.out.printf("%s%n", entry);
                // }

            } else {
                LOGGER.log(Level.SEVERE,
                        String.format("%s%n", "not have site with this word"));
            }
            state.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        return map;
    }

    public boolean checkVideoByName(String str) {
        try (Connection con = DriverManager
                .getConnection("jdbc:sqlite:collection.db")) {
            state = con.createStatement();

            ResultSet rs = state
                    .executeQuery("SELECT * FROM `collection` WHERE  `collection.nameVideo`= '"
                            + str + "'");
            if (rs.next()) {
                state.close();
                return true;
            }
            state.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        return false;

    }

    public int deleteVideoByID(int id) {
        int x = 0;
        try (Connection con = DriverManager
                .getConnection("jdbc:sqlite:collection.db")) {
            state = con.createStatement();
            x = state.executeUpdate("DELETE FROM `collection` WHERE id= '" + id
                    + "'");
            state.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        return x;
    }
}

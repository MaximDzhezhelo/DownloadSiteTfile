package ua.kiev.makson.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaSQL {
    // private AESCrypto encrypt;
    private Statement state;
    private static final Logger LOGGER = Logger.getLogger(JavaSQL.class
            .getName());

    // public JavaSQL() {
    // encrypt = new AESCrypto();
    // }

    public void createSQL() {
        try (Connection con = DriverManager
                .getConnection("jdbc:sqlite:collection.db")) {
            state = con.createStatement();
            state.execute("CREATE TABLE IF NOT EXISTS `collection` (id INTEGER PRIMARY KEY AUTOINCREMENT, `collection.nameVideo` TEXT(255), `collection.viewtopic` TEXT(455), `collection.downloadUrl` TEXT(255), `collection.category` TEXT(455), `collection.img` TEXT(255)) ");
            state.close();
            LOGGER.log(Level.SEVERE, "create SQL");
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
    }

    public void writeData(String nameVideo, String viewtopic,
            String downloadUrl, String category) {
        try (Connection con = DriverManager
                .getConnection("jdbc:sqlite:collection.db")) {
            state = con.createStatement();
            // password = encrypt.encrypt(password);
            state.executeUpdate("INSERT INTO `collection` (`collection.nameVideo`, `collection.viewtopic`, `collection.downloadUrl`, `collection.category`) VALUES('"
                    + nameVideo
                    + "', '"
                    + viewtopic
                    + "', '"
                    + downloadUrl
                    + "', '" + category + "')");

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
                String viewtopic = rs.getString("collection.viewtopic");
                String downloadUrl = rs.getString("collection.downloadUrl");
                String category = rs.getString("collection.category");
                // String password = encrypt.decrypt(result);
                LOGGER.log(Level.SEVERE, String.format(
                        "#%02d %s %s  %s%n  %s%n", id, nameVideo, category,
                        downloadUrl, viewtopic));
            }
            state.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
    }

    public void searchSite(String str) {
        try (Connection con = DriverManager
                .getConnection("jdbc:sqlite:password.db")) {
            state = con.createStatement();
            List<String> siteList = new ArrayList<>();
            ResultSet rs = state
                    .executeQuery("SELECT `password.site` FROM `password` WHERE  `password.site` LIKE '%"
                            + str + "%'");

            while (rs.next()) {
                String site = rs.getString("password.site");
                siteList.add(site);

            }
            if (siteList.size() > 0) {
                for (String string : siteList) {
                    System.out.printf("%s%n", string);
                }
            } else {
                LOGGER.log(Level.SEVERE,
                        String.format("%s%n", "not have site with this word"));
            }
            state.close();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
    }

}

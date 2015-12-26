package ua.kiev.makson.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import ua.kiev.makson.work_in_site.requests.getvideo.page.VideoDescription;

public class JavaSQL {
	// private AESCrypto encrypt;
	private Statement state;
	private static final Logger LOGGER = Logger.getLogger(JavaSQL.class);

	public void createSQL() {
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:collection.db")) {
			state = con.createStatement();
			state.execute("CREATE TABLE IF NOT EXISTS `collection` (id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "`collection.name` TEXT(255), " + "`collection.viewtopic` TEXT(455), "
					+ "`collection.downloadUrl` TEXT(255), " + "`collection.url` TEXT(255), "
					+ "`collection.jpg` TEXT(255), " + "`collection.description` TEXT(455)) ");
			state.close();
			LOGGER.info("create SQL");
		} catch (SQLException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	public void writeData(VideoDescription video) {
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:collection.db")) {
			state = con.createStatement();
			// password = encrypt.encrypt(password);
			state.executeUpdate(
					"INSERT INTO `collection` (`collection.name`, `collection.viewtopic`, `collection.downloadUrl`, `collection.url`, `collection.jpg`,`collection.description`) VALUES('"
							+ video.getName() + "', '" + video.getViewtopic() + "', '" + video.getDownloadTorrent()
							+ "', '" + video.getUrl() + "', '" + video.getJpg() + "', '" + video.getDescription()
							+ "')");
			state.close();
			LOGGER.info("insertUsers");
		} catch (SQLException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	public void showAll() {
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:collection.db")) {
			state = con.createStatement();
			ResultSet rs = state.executeQuery("SELECT * FROM `collection`");
			while (rs.next()) {
				int id = rs.getInt(1);
				String nameVideo = rs.getString("collection.name");
				String viewtopic = rs.getString("collection.viewtopic");
				String downloadUrl = rs.getString("collection.downloadUrl");
				String url = rs.getString("collection.url");
				String jpg = rs.getString("collection.jpg");
				String description = rs.getString("collection.description");
				LOGGER.info(String.format("#%02d %s  %s%n %s%n %s%n %s%n %s%n", id, nameVideo, viewtopic, downloadUrl,
						url, jpg, description));
			}
			state.close();
		} catch (SQLException ex) {
			LOGGER.error(ex.getMessage());
		}
	}

	public VideoDescription searchVideoByName(String str) {
		VideoDescription description = new VideoDescription();
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:collection.db")) {
			state = con.createStatement();

			ResultSet rs = state.executeQuery("SELECT * FROM `collection` WHERE  `collection.name`= '" + str + "'");
			while (rs.next()) {
				int id = rs.getInt(1);
				description.setId(id);
				String name = rs.getString("collection.name");
				description.setName(name);
				String viewtopic = rs.getString("collection.viewtopic");
				description.setViewtopic(viewtopic);
				String downloadUrl = rs.getString("collection.downloadUrl");
				description.setDownloadTorrent(downloadUrl);
				String url = rs.getString("collection.url");
				description.setUrl(url);
				String jpg = rs.getString("collection.jpg");
				description.setJpg(jpg);
				String descriptionText = rs.getString("collection.description");
				description.setDescription(descriptionText);
			}
			if (description.getName() == null) {
				LOGGER.info(String.format("%s%n", "not have site with this word"));
				return null;
			}
			state.close();
		} catch (SQLException ex) {
			LOGGER.error(ex.getMessage());
		}
		return description;
	}

	public boolean checkVideoByName(String str) {
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:collection.db")) {
			state = con.createStatement();

			ResultSet rs = state.executeQuery("SELECT * FROM `collection` WHERE  `collection.name`= '" + str + "'");
			if (rs.next()) {
				state.close();
				return true;
			}
			state.close();
		} catch (SQLException ex) {
			LOGGER.error(ex.getMessage());
		}
		return false;

	}

	public int deleteVideoByID(int id) {
		int x = 0;
		try (Connection con = DriverManager.getConnection("jdbc:sqlite:collection.db")) {
			state = con.createStatement();
			x = state.executeUpdate("DELETE FROM `collection` WHERE id= '" + id + "'");
			state.close();
		} catch (SQLException ex) {
			LOGGER.error(ex.getMessage());
		}
		return x;
	}
}

package io.github.RESKOM326.guillimanutils.dataStorage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDataManager implements DataManager{
	Connection conn = null;
	
	private final String url;
	private final String user;
	private final String password;
	
	public SQLDataManager(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	/**
	 * Connects to the database specified in the config.yml file
	 * @return True if connection was correctly established
	 */
	public boolean DBconnect() {
		try {
			if(!conn.isClosed()) {
				return false;
			}
			String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			return true;
		} catch(ClassNotFoundException c) {
			System.err.println("Driver not found");
			return false;
		} catch(SQLException s) {
			System.err.println(s.getErrorCode());
			return false;
		}
	}
	
	/**
	 * Disconnects from the database specified in the config.yml file
	 * @return True if disconnection was successful
	 */
	public boolean DBclose() {
		try {
			if(conn.isClosed()) {
				return false;
			}
			conn.close();
			return true;
		} catch(SQLException s) {
			System.err.println(s.getErrorCode());
			return false;
		}
	}
	
	/**
	 * Initializes the tables that will be used
	 * @return True if the initialization was successful
	 */
	public boolean initialize() {
		try {
			String sql = "CREATE TABLE IF NOT EXISTS godhood(ID INT AUTO_INCREMENT, playername VARCHAR(50) UNIQUE, "
					+ "PRIMARY KEY(ID) ON DELETE CASCADE ON UPDATE CASCADE);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			return true;
		} catch(SQLException s) {
			System.err.println(s.getErrorCode());
			return false;
		}

	}

	@Override
	public boolean addGod(PlayerDB player) {
		// TODO Auto-generated method stub
		try {
			if(player.getID() == -1) {
				PreparedStatement ps = conn.prepareStatement("INSERT INTO godhood (playername) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, player.getName());
				ps.executeUpdate();
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					player.setID(rs.getInt("ID"));
				}
			}
			PreparedStatement upd = conn.prepareStatement("UPDATE godhood SET playername = ? WHERE ID = ?");
			upd.setString(1, player.getName());
			upd.setInt(2, player.getID());
			upd.executeUpdate();
			return true;
		} catch(SQLException s) {
			System.err.println(s.getErrorCode());
			return false;
		}
	}

	@Override
	public boolean quitGod(PlayerDB player) {
		// TODO Auto-generated method stub
		try {
			if(player.getID() == -1) {
				return false;
			}
			PreparedStatement dlt = conn.prepareStatement("DELETE FROM godhood WHERE ID = ?");
			dlt.setInt(1, player.getID());
			dlt.executeUpdate();
		} catch(SQLException s) {
			System.err.println(s.getErrorCode());
		}
		return false;
	}
	
}

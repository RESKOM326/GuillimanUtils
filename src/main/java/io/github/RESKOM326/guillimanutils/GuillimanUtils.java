package io.github.RESKOM326.guillimanutils;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.RESKOM326.guillimanutils.dataStorage.DataManager;
import io.github.RESKOM326.guillimanutils.dataStorage.SQLDataManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

import org.bukkit.configuration.file.FileConfiguration;

public class GuillimanUtils extends JavaPlugin{
	private static DataManager dataManager;
	@Override
	public void onEnable() {
		try {
			this.saveDefaultConfig();
			FileConfiguration config = this.getConfig();
			String dbtype = config.getString("database.type").toUpperCase(Locale.ENGLISH);
			if(dbtype.equals("MYSQL")) {
				String port = config.getString("database.port");
				if(!port.matches("^\\d+$")) {
					throw new NumberFormatException();
				}
				String url = "jdbc:mysql://"+config.getString("databse.address")+port+"/"+config.getString("database.schema");
				dataManager = new SQLDataManager(url, config.getString("database.user"), config.getString("database.password"));
			}
			else if(dbtype.equals("FILE")) {
				new File(this.getDataFolder(), "guillimanutils.ctn");
			}
			else {
				System.err.println("No database type was specified");
				return;
			}
			
			this.getCommand("ignite").setExecutor(new GuillimanUtilsCommandExecutor(this));
			this.getCommand("hideme").setExecutor(new GuillimanUtilsCommandExecutor(this));
			this.getCommand("unhideme").setExecutor(new GuillimanUtilsCommandExecutor(this));
			this.getCommand("ascend").setExecutor(new GuillimanUtilsCommandExecutor(this));
			this.getLogger().info("GuillimanUtils is enabled!");
		} catch(NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	@Override
	public void onDisable() {
		this.getLogger().info("GuillimanUtils is disabled!");
	}
}
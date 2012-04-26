package net.crafthub.noobidoo.teleportgamemode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
	Teleportgamemode plugin;
	FileConfiguration Fileconf;
	String Spectator = "Spectator";
	String Participant = "Participant";
	public Config(Teleportgamemode plugin) {
		super();
		this.plugin = plugin;
		Fileconf = plugin.getConfig();
		try {
			Fileconf.load(plugin.getDataFolder().toString()+"config.yml");
		} catch (FileNotFoundException e) {
			try {
				Fileconf.addDefault(Spectator, "groupname");
				Fileconf.addDefault(Participant, "groupname");
				Fileconf.save(plugin.getDataFolder().toString()+"config.yml");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public String getSpectatorclass()
	{
		return Fileconf.getString(Spectator);
	}
	public String getParticipantclass()
	{
		return Fileconf.getString(Participant);
	}
}

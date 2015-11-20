package me.t0x1c.animatedtablistplus;

import me.t0x1c.animatedtablistplus.commands.AnimtablistCommand;
import me.t0x1c.animatedtablistplus.events.PlayerjoinEvent;
import me.t0x1c.animatedtablistplus.extra.AnimTablist;
import me.t0x1c.animatedtablistplus.extra.AnimTablist2;
import me.t0x1c.animatedtablistplus.extra.AnimTablist3;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	AnimTablist animtablist = new AnimTablist();
	AnimTablist2 animtablist2 = new AnimTablist2();
	AnimTablist3 animtablist3 = new AnimTablist3();
	
	public void onEnable() {
		saveDefaultConfig();
		reloadConfig();
		loadCommands();
		loadEvents();
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		for(Player all : Bukkit.getOnlinePlayers()) {
			if(Bukkit.getOnlinePlayers().isEmpty()) {
				return;
			}
			if(version.startsWith("v1_8_R1")) {
				animtablist.repeatingTab(all);
			} else if(version.startsWith("v1_8_R2")) {
				animtablist2.repeatingTab(all);
			} else if(version.startsWith("v1_8_R3")) {
				animtablist2.repeatingTab(all);
			} else if(version.startsWith("v1_8_R4")) {
				animtablist2.repeatingTab(all);
			} else if(version.startsWith("v1_8_R5")) {
				animtablist2.repeatingTab(all);
			} else if(version.startsWith("v1_8_R6")) {
				animtablist2.repeatingTab(all);
			} else if(version.startsWith("v1_8_R7")) {
				animtablist2.repeatingTab(all);
			} else if(version.startsWith("v1_8_R8")) {
				animtablist3.repeatingTab(all);
			}
		}
		if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			Bukkit.getConsoleSender().sendMessage("§4Animated§cTablist §8> §7Found PlaceholderAPI!");
			Bukkit.getConsoleSender().sendMessage("§4Animated§cTablist §8> §7You can now use all PlaceholderAPI's placeholders (https://www.spigotmc.org/wiki/placeholderapi-placeholders/)");
		} else {
			Bukkit.getConsoleSender().sendMessage("§4Animated§cTablist §8> §7Didn't find PlaceholderAPI!");
		}
	}
	
	public void loadCommands() {
		getCommand("animtablist").setExecutor(new AnimtablistCommand(this));
	}
	
	public void loadEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerjoinEvent(), this);
	}
}
package me.t0x1c.animatedtablistplus.events;

import me.t0x1c.animatedtablistplus.extra.AnimTablist;
import me.t0x1c.animatedtablistplus.extra.AnimTablist2;
import me.t0x1c.animatedtablistplus.extra.AnimTablist3;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerjoinEvent implements Listener {
	
	AnimTablist animtablist = new AnimTablist();
	AnimTablist2 animtablist2 = new AnimTablist2();
	AnimTablist3 animtablist3 = new AnimTablist3();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		if(version.startsWith("v1_8_R1")) {
			animtablist.repeatingTab(player);
		} else if(version.startsWith("v1_8_R2")) {
			animtablist2.repeatingTab(player);
		} else if(version.startsWith("v1_8_R3")) {
			animtablist2.repeatingTab(player);
		} else if(version.startsWith("v1_8_R4")) {
			animtablist2.repeatingTab(player);
		} else if(version.startsWith("v1_8_R5")) {
			animtablist2.repeatingTab(player);
		} else if(version.startsWith("v1_8_R6")) {
			animtablist2.repeatingTab(player);
		} else if(version.startsWith("v1_8_R7")) {
			animtablist2.repeatingTab(player);
		} else if(version.startsWith("v1_8_R8")) {
			animtablist3.repeatingTab(player);
		}
	}
}
package me.t0x1c.animatedtablistplus.extra;

import java.lang.reflect.InvocationTargetException;

import me.clip.placeholderapi.PlaceholderAPI;
import me.t0x1c.animatedtablistplus.Main;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class AnimTablist3 {
	
	Main pl = new Main();
	
	String toMinecraftTextJSON(String input) {
		return "{\"text\":\"" + input + "\"}";
	}
	
	void updateTab(String header, String footer) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, NoSuchFieldException {
		for(Player p : Bukkit.getOnlinePlayers()) {
			CraftPlayer p2 = (CraftPlayer)p;
			
			PacketPlayOutPlayerListHeaderFooter tab = new PacketPlayOutPlayerListHeaderFooter(ChatSerializer.a(toMinecraftTextJSON(header)));
			try {
				java.lang.reflect.Field b = tab.getClass().getDeclaredField("b");
				b.setAccessible(true);
				b.set(tab, ChatSerializer.a(toMinecraftTextJSON(footer)));
				b.setAccessible(false);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
			p2.getHandle().playerConnection.sendPacket(tab);
		}
	}
	
	int headerNumber = 0;
	int footerNumber = 0;
	
	public void repeatingTab(final Player player) {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(pl, new Runnable() {
			public void run() {
				String header = null;
				String footer = null;
				if(headerNumber >= pl.getConfig().getStringList("Headers").size()) {
					headerNumber = 0;
				}
				
				if(footerNumber >= pl.getConfig().getStringList("Footers").size()) {
					footerNumber = 0;
				}
				
				header = ChatColor.translateAlternateColorCodes('&', pl.getConfig().getStringList("Headers").get(headerNumber)).replaceAll("%n", "\n").replaceAll("%player_name%", player.getName()).replaceAll("%player_health%", String.valueOf(player.getHealth())).replaceAll("%player_hunger%", String.valueOf(player.getFoodLevel())).replaceAll("%player_displayname%%", player.getDisplayName()).replaceAll("%player_world%", player.getWorld().getName()).replaceAll("%player_level%", String.valueOf(player.getLevel())).replaceAll("%player_xp%", String.valueOf(player.getExp())).replaceAll("%player_gamemode%", String.valueOf(player.getGameMode())).replaceAll("%player_kills%", String.valueOf(player.getStatistic(Statistic.PLAYER_KILLS))).replaceAll("%player_deaths%", String.valueOf(player.getStatistic(Statistic.DEATHS))).replaceAll("%player_ping%", String.valueOf(((CraftPlayer) player).getHandle().ping)).replaceAll("%server_name%", Bukkit.getServerName()).replaceAll("%server_motd%", Bukkit.getServer().getMotd()).replaceAll("%online_players%", String.valueOf(Bukkit.getServer().getOnlinePlayers().size())).replaceAll("%max_players%", String.valueOf(Bukkit.getServer().getMaxPlayers()));
				footer = ChatColor.translateAlternateColorCodes('&', pl.getConfig().getStringList("Footers").get(footerNumber)).replaceAll("%n", "\n").replaceAll("%player_name%", player.getName()).replaceAll("%player_health%", String.valueOf(player.getHealth())).replaceAll("%player_hunger%", String.valueOf(player.getFoodLevel())).replaceAll("%player_displayname%%", player.getDisplayName()).replaceAll("%player_world%", player.getWorld().getName()).replaceAll("%player_level%", String.valueOf(player.getLevel())).replaceAll("%player_xp%", String.valueOf(player.getExp())).replaceAll("%player_gamemode%", String.valueOf(player.getGameMode())).replaceAll("%player_kills%", String.valueOf(player.getStatistic(Statistic.PLAYER_KILLS))).replaceAll("%player_deaths%", String.valueOf(player.getStatistic(Statistic.DEATHS))).replaceAll("%player_ping%", String.valueOf(((CraftPlayer) player).getHandle().ping)).replaceAll("%server_name%", Bukkit.getServerName()).replaceAll("%server_motd%", Bukkit.getServer().getMotd()).replaceAll("%online_players%", String.valueOf(Bukkit.getServer().getOnlinePlayers().size())).replaceAll("%max_players%", String.valueOf(Bukkit.getServer().getMaxPlayers()));
				if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
					header = PlaceholderAPI.setPlaceholders(player, header);
					footer = PlaceholderAPI.setPlaceholders(player, footer);
				}
				
				headerNumber++;
				footerNumber++;
				
				try {
					updateTab(header, footer);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException| SecurityException | ClassNotFoundException | InstantiationException | NoSuchFieldException e) {
					e.printStackTrace();
				}
			}
		}, 0, pl.getConfig().getInt("UpdateTime"));
	}
}
package me.t0x1c.animatedtablistplus.commands;

import me.t0x1c.animatedtablistplus.Main;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AnimtablistCommand implements CommandExecutor {
	
	private final Main pl;
	public AnimtablistCommand(Main pl) {
		this.pl = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("Messages.OnlyPlayers")));
			return true;
		}
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("animtablist")) {
			if(args.length == 0) {
				if(player.hasPermission("AnimTablist.version")) {
					player.sendMessage("§4§m---------------");
					player.sendMessage("§7Version: §c" + pl.getDescription().getVersion());
					player.sendMessage("§7Author: §cT0X1C");
					player.sendMessage("§4§m---------------");
				} else {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("Messages.NoPermissions")));
				}
				return true;
			}
			if(args[0].equalsIgnoreCase("reload")) {
				if(player.hasPermission("AnimTablist.reload")) {
					pl.reloadConfig();
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("Messages.ReloadedConfig")));
				} else {
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("Messages.NoPermissions")));
				}
			}
		}
		return true;
	}
}
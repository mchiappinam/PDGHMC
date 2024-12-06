package me.mchiappinam.pdghmc;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;

import me.mchiappinam.pdghmc.WorldGuard;

public class Main extends JavaPlugin implements Listener, CommandExecutor {

	  public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginCommand("mc").setExecutor(this);
		getServer().getConsoleSender().sendMessage("§3[PDGHMC] §2ativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHMC] §2Acesse: http://pdgh.com.br/");
	  }

	  public void onDisable() {
		getServer().getConsoleSender().sendMessage("§3[PDGHMC] §2desativado - Plugin by: mchiappinam");
		getServer().getConsoleSender().sendMessage("§3[PDGHMC] §2Acesse: http://pdgh.com.br/");
	  }

	  public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
	    if (cmd.getName().equalsIgnoreCase("mc")) {
	        if (args.length >0) {
	            sender.sendMessage("§cUse /mc");
	            return true;
	          }
	        Player p = (Player) sender;
  			if(!p.hasPermission("pdgh.vip")) {
  				p.sendMessage("§6Apenas §lVIPs &6podem usar esse comando.");
  				return true;
  			}
	        if (!p.getAllowFlight()) {
	        	p.playEffect(p.getLocation(), Effect.CLICK1, 3);
	        	p.setAllowFlight(true);
	        	p.setFlySpeed(0.065f);
	        	WorldGuard.canFlyHere(p);
	        	sender.sendMessage("§4[PDGH] §6Seu tapete mágico invisível foi ativado.");
	        	WorldGuardPlugin wg = new WorldGuardPlugin();
	        	RegionManager regionm = wg.getRegionManager(p.getWorld());
	        	Location pt = (Location) p.getLocation().getBlock().getRelative(0, 1, 0);
	        	ApplicableRegionSet set =  regionm.getApplicableRegions(pt);
	        	Set<String> flag = set.getFlag(com.sk89q.worldguard.protection.flags.DefaultFlag.BLOCKED_CMDS);
	        
	        	for (String blocked : flag) {
	        		if (blocked.contains("/mc")) {
	        			Bukkit.getServer().broadcastMessage(p.getName()+" (mc) TESTE");
	        			return true;	
	        		}
	        	}
            }else{
	        	p.playEffect(p.getLocation(), Effect.CLICK2, 3);
            	p.setAllowFlight(false);
	        	p.setFlySpeed(0.065f);
	        	WorldGuard.canFlyHere(p);
            	sender.sendMessage("§4[PDGH] §6Seu tapete mágico invisível foi desativado.");
            }
	        return true;
	    }
		return false;
	  }
	    
	    
	    
	    
	    
	    
	    
}
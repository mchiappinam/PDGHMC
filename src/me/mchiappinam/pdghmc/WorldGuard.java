package me.mchiappinam.pdghmc;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;


public class WorldGuard {
    
    
    public static boolean canFlyHere(Player p) {
        try {
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
        
        	return false;
        	} catch (Exception e) {
        		return true;
        	}
    }
    
}
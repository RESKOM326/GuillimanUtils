package io.github.RESKOM326.guillimanutils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class GuillimanUtilsPlayerListener {
	private final GuillimanUtils plugin;
	
	public GuillimanUtilsPlayerListener(GuillimanUtils plugin) {
		this.plugin = plugin;
	}
	public boolean fInTheChat(PlayerDeathEvent event) {
		event.setDeathMessage("Press F to pay respects to " + event.getEntity().getName());
		return true;
	}
	public boolean castLightningStrike(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if(p.getName().equals("Gllinomez") && event.getItem().getType() == Material.TRIDENT) {
			
		}
	}
	
}

package lishid.openinv;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityListener;

public class OpenInvEntityListener extends EntityListener{
	OpenInv plugin;
	public OpenInvEntityListener(OpenInv scrap) {
		plugin = scrap;
	}
	
	@Override
    public void onEntityDamage(EntityDamageEvent event) {
        if (event instanceof EntityDamageByEntityEvent) {
        	EntityDamageByEntityEvent evt = (EntityDamageByEntityEvent) event;
            Entity attacker = evt.getDamager();
            Entity defender = evt.getEntity();

    		if(!(attacker instanceof Player)||!(defender instanceof Player))
    		{
    			return;
    		}
    		
    		Player player = (Player)attacker;
    		
    		if(!(player.getItemInHand().getType().getId() == OpenInv.GetItemOpenInvItem())
    				|| (!OpenInv.GetPlayerItemOpenInvStatus(player.getName()))
    				|| !PermissionRelay.hasPermission(player, "openinv"))
    		{
    			return;
    		}
    		
    		Player target = (Player)defender;
    		player.performCommand("openinv " + target.getName());
    		
    		evt.setDamage(0);
    		evt.setCancelled(true);
        }
    }
}

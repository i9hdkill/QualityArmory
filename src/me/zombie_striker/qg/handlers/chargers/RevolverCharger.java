package me.zombie_striker.qg.handlers.chargers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.zombie_striker.qg.Main;
import me.zombie_striker.qg.guns.Gun;
import me.zombie_striker.qg.guns.utils.WeaponSounds;

public class RevolverCharger implements ChargingHandler {

	List<UUID> timeC = new ArrayList<>();
public RevolverCharger() {
	ChargingManager.add(this);
}
	@Override
	public boolean isCharging(Player player) {
		return timeC.contains(player.getUniqueId());
	}

	@Override
	public boolean shoot(Gun g, final Player player, ItemStack stack) {
		timeC.add(player.getUniqueId());
		new BukkitRunnable() {
			
			@Override
			public void run() {try {
				player.getWorld().playSound(player.getLocation(), WeaponSounds.RELOAD_BULLET.getName(), 1,
						0.75f);
			}catch(Error|Exception e43) {}
			}
		}.runTaskLater(Main.getInstance(), 10);
		new BukkitRunnable() {
			
			@Override
			public void run() {
				timeC.remove(player.getUniqueId());
			}
		}.runTaskLater(Main.getInstance(), 15);
		return true;
	}

	@Override
	public String getName() {

		return ChargingManager.REVOLVER;
	}

}
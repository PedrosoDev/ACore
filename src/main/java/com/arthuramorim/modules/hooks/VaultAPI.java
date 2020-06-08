package com.arthuramorim.modules.hooks;

import com.arthuramorim.NeroAPI;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;

public class VaultAPI {
	
	public static boolean use = false;
	static Economy economy = null;
	public static Economy getEconomy() {
		return economy;
	}

	private static boolean setupEconomy(Plugin plugin) {
		if (plugin.getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> econProvider = plugin.getServer().getServicesManager()
				.getRegistration(Economy.class);
		if (econProvider != null) {
			economy = (Economy) econProvider.getProvider();
		}
		use = economy != null;
		return use;
	}
	
	public static void hook() {
		if (setupEconomy(NeroAPI.getNeroAPI())) NeroAPI.getHooks().add(NeroAPI.getNeroAPI().getServer().getPluginManager().getPlugin("Vault"));
	}

}

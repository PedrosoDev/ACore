package com.arthuramorim;

import com.arthuramorim.inventoryGUI.listeners.InventoryListener;
import com.arthuramorim.modules.hooks.PermissionsExAPI;
import com.arthuramorim.modules.hooks.VaultAPI;
import com.arthuramorim.utils.TextUtil;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;

public class NeroAPI extends NeroPlugin {

    private static NeroAPI neroAPI;
    private static List<Plugin> hooks;
    public static HashMap<String, NeroPlugin> registeredPlugins;

    public NeroAPI() {
        super("NeroAPI", "1.0", "Arthur 'KingN3R0' Amorim");
    }

    @Override
    public void load() {

    }

    @Override
    public void start() {
        neroAPI = this;
        new InventoryListener(this);
        VaultAPI.hook();
        PermissionsExAPI.hook();
        TextUtil.color("&d----------------- NeroAPI &6"+ neroAPI.getServer().getVersion()+" -----------------");
        TextUtil.color("");
        TextUtil.color("");

        try {
            getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        } catch (Exception e) {}
    }

    @Override
    public void stop() {

    }

    public static NeroAPI getNeroAPI() {
        return neroAPI;
    }

    public static List<Plugin> getHooks() {
        return hooks;
    }
}

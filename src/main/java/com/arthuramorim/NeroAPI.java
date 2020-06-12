package com.arthuramorim;

import com.arthuramorim.utils.hooks.NeroPrestigioAPI;
import com.arthuramorim.utils.inventoryGUI.listeners.InventoryListener;
import com.arthuramorim.utils.hooks.PermissionsExAPI;
import com.arthuramorim.utils.hooks.VaultAPI;
import com.arthuramorim.utils.utils.TextUtil;
import com.google.common.collect.Lists;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;

public class NeroAPI extends NeroPlugin {

    private static NeroAPI neroAPI;
    private static List<Plugin> hooks;
    public static HashMap<String, NeroPlugin> registeredPlugins;

    public NeroAPI() {
        super("NeroAPI","1.0","Arthur 'N3R0' Amorim");
        hooks = Lists.newArrayList();
        registeredPlugins = new HashMap<>();
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
        NeroPrestigioAPI.hook();
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

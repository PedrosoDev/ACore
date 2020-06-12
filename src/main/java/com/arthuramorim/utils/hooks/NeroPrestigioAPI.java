package com.arthuramorim.utils.hooks;

import com.arthuramorim.NeroAPI;

public class NeroPrestigioAPI {

    public static boolean use = false;

    public static void hook() {
        if(NeroAPI.getNeroAPI().getServer().getPluginManager().isPluginEnabled("NeroPrestigio")){
            use = true;
            NeroAPI.getHooks().add(NeroAPI.getNeroAPI().getServer().getPluginManager().getPlugin("NeroPrestigio"));
            
        }
    }
}

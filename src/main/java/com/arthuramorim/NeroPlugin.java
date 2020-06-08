package com.arthuramorim;

import com.arthuramorim.utils.TextUtil;
import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public abstract class NeroPlugin extends JavaPlugin implements Listener {

    private String pluginName, version, author;
    private List<String> depends;

    public NeroPlugin(String pluginName,String version){
        this.pluginName = pluginName;
        this.version = version;
        depends = Lists.newArrayList();
    }

    public NeroPlugin(String pluginName,String version, String author){
        this.pluginName = pluginName;
        this.version = version;
        this.author = author;
        depends = Lists.newArrayList();
    }


    public void onLoad() {
        load();
    }

    public void onEnable() {
        if(NeroAPI.registeredPlugins.containsKey(this.pluginName)){
            disablePlugin();
            return;
        }
        if(!loadDepends())return;

        getServer().getPluginManager().registerEvents(this,this);
        TextUtil.print("&aPlugin inicializado com sucesso!");
        TextUtil.print("&eVersao: " + this.getVersion());
        start();
        NeroAPI.registeredPlugins.put(pluginName, this);
    }

    public void onDisable(){

        TextUtil.print("&e["+this.getPluginName()+"] &cPlugin desligando");
        NeroAPI.registeredPlugins.remove(this.getPluginName());
        HandlerList.unregisterAll((Plugin) this);
        Bukkit.getScheduler().cancelTasks(this);
        Bukkit.getPluginManager().disablePlugin(this);
        TextUtil.print("&e["+this.getPluginName()+"] &cPlugin desligado!");
        stop();


    }

    public void disablePlugin(){
        TextUtil.print("&e["+this.getPluginName()+"] &eForçando desligamento do plugin");
        NeroAPI.registeredPlugins.remove(this.getPluginName());
        HandlerList.unregisterAll((Plugin) this);
        Bukkit.getScheduler().cancelTasks(this);
        Bukkit.getPluginManager().disablePlugin(this);
        stop();
    }

    private boolean loadDepends() {
        for (String string : getDepends()) {
            String pluginDepend = string.replace(" ", "");
            if (Bukkit.getServer().getPluginManager().getPlugin(pluginDepend) != null) {
                TextUtil.print("&b[&a" + this.getPluginName() + "&b] &aDependência &f'" + pluginDepend + "'&a carregada.");
            } else {
                TextUtil.print("&b");
                TextUtil.print("&b[&a" + this.getPluginName() + "&b] &cDependência &f'" + pluginDepend + "' &cnão foi carregada. Desativando plugin!");
                TextUtil.print("&b");
                disablePlugin();
                return false;
            }
        }
        return true;
    }

    public void registerListener(Listener... listeners) {
        PluginManager pm = this.getServer().getPluginManager();
        for (Listener listener : listeners) pm.registerEvents(listener, (Plugin) this);
    }

    public void addDepends(String plugin) {
        this.depends.add(plugin);
    }
    public List<String> getDepends() {
        return depends;
    }

    public void setAuthor(String string) {this.author = string;}

    public String getPluginName() {
        return pluginName;
    }

    public String getVersion() {
        return version;
    }

    public String getAuthor() {
        return author;
    }

    public abstract void load();
    public abstract void start();
    public abstract void stop();
}

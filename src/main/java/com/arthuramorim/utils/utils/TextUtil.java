package com.arthuramorim.utils.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class TextUtil {

    public static String color(String string){

        return ChatColor.translateAlternateColorCodes('&',string);
    }

    public static List<String> color(List<String> list){

        List<String> lista = new ArrayList<String>();

        for (String linha:list) {
            lista.add(color(linha));
        }
        return lista;
    }
    public static void print(String s) {
        Bukkit.getConsoleSender().sendMessage(color(s));
    }
}

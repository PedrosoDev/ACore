package com.arthuramorim.utils.player;

import com.arthuramorim.utils.hooks.PermissionsExAPI;
import com.arthuramorim.utils.hooks.VaultAPI;
import com.arthuramorim.utils.reflections.ServerVersion;
import com.arthuramorim.utils.utils.MakeItem;
import com.arthuramorim.utils.utils.SoundUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PlayerUtil {

    static ServerVersion serverVersion = ServerVersion.getServerVersion();

    public static int emptySlots(Player p){
        int empty = 0;
        if(p.isOnline()){
            for(ItemStack content : p.getInventory().getContents()){
                if(content == null || content.getType() == Material.AIR) empty++;
            }
        }
        return empty;

    }

    public static void setItem(Player p, ItemStack item, int slot) {
        if (emptySlots(p) != 0) {
            p.getInventory().setItem(slot, item);
        }
    }
    public static void addItem(Player p, ItemStack... items) {
        for (ItemStack item : items) if (emptySlots(p) != 0) p.getInventory().addItem(item);
    }
    public static void addItemSound(Player p, ItemStack... items) {
        SoundUtil.ENTITY_ITEM_PICKUP.play(p);
        for (ItemStack item : items) if (emptySlots(p) != 0) p.getInventory().addItem(item);
    }
    public static void decreaseItem(Player p, int slot, int amount) {
        ItemStack item = p.getInventory().getItem(slot);
        int finalAmount = item.getAmount() - amount;
        if (finalAmount <= 0) {
            p.getInventory().clear(slot);
        } else {
            item.setAmount(finalAmount);
            p.getInventory().setItem(slot, item);
        }
    }
    public static void decreaseItem(Player p, ItemStack item, int amount) {
        p.getInventory().removeItem(new MakeItem(item).setAmout(amount).build());
    }

    public static enum CardinalDirection { NORTH, NORTH_WEST, EAST, SOUTH_EAST, SOUTH, SOUTH_WEST, WEST; }

    public static CardinalDirection getCardinalDirection(Player p) {

        double rotation = ((p.getLocation().getYaw() - 90.0F) % 360.0F);
        if (rotation < 0.0D) {
            rotation += 360.0D;
        }
        if (0.0D <= rotation && rotation < 22.5D)
            return CardinalDirection.NORTH;
        if (22.5D <= rotation && rotation < 67.5D)
            return CardinalDirection.NORTH_WEST;
        if (67.5D <= rotation && rotation < 112.5D)
            return CardinalDirection.EAST;
        if (112.5D <= rotation && rotation < 157.5D)
            return CardinalDirection.SOUTH_EAST;
        if (157.5D <= rotation && rotation < 202.5D)
            return CardinalDirection.SOUTH;
        if (202.5D <= rotation && rotation < 247.5D)
            return CardinalDirection.SOUTH_WEST;
        if (247.5D <= rotation && rotation < 292.5D)
            return CardinalDirection.WEST;
        if (292.5D <= rotation && rotation < 337.5D)
            return CardinalDirection.NORTH_WEST;
        if (337.5D <= rotation && rotation < 360.0D) {
            return CardinalDirection.NORTH;
        }
        return null;
    }

    public static void lookAt(Player p, Location location) {
        Location from = p.getLocation().clone();
        Location to = location.clone();
        double dx = to.getX() - from.getX();
        double dy = to.getY() - from.getY();
        double dz = to.getZ() - from.getZ();
        if (dx != 0.0D) {
            if (dx < 0.0D) {
                from.setYaw(4.712389F);
            } else {
                from.setYaw(1.5707964F);
            }
            from.setYaw(from.getYaw() - (float)Math.atan(dz / dx));
        } else if (dz < 0.0D) {
            from.setYaw(3.1415927F);
        }
        double dxz = Math.sqrt(Math.pow(dx, 2.0D) + Math.pow(dz, 2.0D));
        from.setPitch((float)-Math.atan(dy / dxz));

        from.setYaw(-from.getYaw() * 180.0F / 3.1415927F);
        from.setPitch(from.getPitch() * 180.0F / 3.1415927F);

        p.teleport(from);
    }
    public static void playSound(Player p, SoundUtil s) {
        s.play(p);
    }
    public static String getPlayerPrefix(Player p) {
        if (PermissionsExAPI.use) {
            return PermissionsEx.getUser(p.getName()).getPrefix();
        }
        return "";
    }
    public static String getPlayerPrefix(String playerName) {
        if (PermissionsExAPI.use) {
            return PermissionsEx.getUser(playerName).getPrefix();
        }
        return "";
    }
    public static String getPlayerGroupPrefix(Player p) {
        if (PermissionsExAPI.use) {
            return PermissionsEx.getUser(p.getName()).getGroups()[0].getPrefix();
        }
        return "";
    }
    public static String getPlayerGroupPrefix(String playerName) {
        if (PermissionsExAPI.use) {
            return PermissionsEx.getUser(playerName).getGroups()[0].getPrefix();
        }
        return "";
    }
    public static double getBalance(Player p) {
        if (VaultAPI.use) {
            return VaultAPI.getEconomy().getBalance(p);
        }
        return 0.0;
    }
    public static void setBalance(Player p, double amount) {
        if (VaultAPI.use) {
            VaultAPI.getEconomy().withdrawPlayer(p, VaultAPI.getEconomy().getBalance(p));
            VaultAPI.getEconomy().depositPlayer(p, (Math.max(amount, 0.0)));
        }
    }
    public static void addBalance(Player p, double amount) {
        setBalance(p, getBalance(p) + amount);
    }
    public static void removeBalance(Player p, double amount) {
        setBalance(p, getBalance(p) - amount);
    }
    public static boolean hasBalance(Player p, double amount) {
        return getBalance(p) >= amount;
    }
}

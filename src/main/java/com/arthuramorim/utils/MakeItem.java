package com.arthuramorim.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.github.eokasta.core.utils.reflections.ReflectionUtil;
import com.google.common.io.BaseEncoding;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import net.md_5.bungee.api.ChatColor;

public class MakeItem {
    private ItemStack ik;
    public static String green_light = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDI3Y2E0NmY2YTliYjg5YTI0ZmNhZjRjYzBhY2Y1ZTgyODVhNjZkYjc1MjEzNzhlZDI5MDlhZTQ0OTY5N2YifX19";
    public static String pink_light = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2VmMGM1NzczZGY1NjBjYzNmYzczYjU0YjVmMDhjZDY5ODU2NDE1YWI1NjlhMzdkNmQ0NGYyZjQyM2RmMjAifX19";
    public static String blue_light = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmVmN2I2ODI5ZmM0ODc1OGNiMjVhYjkzZjI4YmY3OTRkOTJhY2UwMTYxZjgwOWEyYWFkZDNiYjEyYjIzMDE0In19fQ==";
    public static String red_light = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDI5MzJiNjZkZWNhZWZmNmViZGM3YzViZTZiMjQ2N2FhNmYxNGI3NDYzODhhMDZhMmUxZTFhODQ2M2U5ZTEyMiJ9fX0=";
    public static String black = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTY3YTJmMjE4YTZlNmUzOGYyYjU0NWY2YzE3NzMzZjRlZjliYmIyODhlNzU0MDI5NDljMDUyMTg5ZWUifX19";
    public static String gray_dark = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjA4ZjMyMzQ2MmZiNDM0ZTkyOGJkNjcyODYzOGM5NDRlZTNkODEyZTE2MmI5YzZiYTA3MGZjYWM5YmY5In19fQ==";
    public static String orange = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmMyZTk3MmFmYTkxMTViNmQzMjA3NWIxZjFiN2ZlZDdhYTI5YTUzNDFjMTAyNDI4ODM2MWFiZThlNjliNDYifX19";
    public static String blue = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTJjZDI3MmVlYjM4YmY3ODNhOThhNDZmYTFlMmU4ZDQ2MmQ4NTJmYmFhZWRlZjBkY2UyYzFmNzE3YTJhIn19fQ==";
    public static String yellow = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY0MTY4MmY0MzYwNmM1YzlhZDI2YmM3ZWE4YTMwZWU0NzU0N2M5ZGZkM2M2Y2RhNDllMWMxYTI4MTZjZjBiYSJ9fX0=";
    public static String red = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWZkZTNiZmNlMmQ4Y2I3MjRkZTg1NTZlNWVjMjFiN2YxNWY1ODQ2ODRhYjc4NTIxNGFkZDE2NGJlNzYyNGIifX19";
    public static String yellow_green = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjhmZmYyMmM2ZTY1NDZkMGQ4ZWI3Zjk3NjMzOTg0MDdkZDJhYjgwZjc0ZmUzZDE2YjEwYTk4M2VjYWYzNDdlIn19fQ==";
    public static String p_w = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjU5M2E2ZjIzZjViMTEwZWMxYzdhYjY2YzQwZmJjOWI4MmY0ZjVjOTg3MjY1YWE2N2M5NWEyZDNmNGM5NyJ9fX0=";
    public static String d_w = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODQxMWE3ZTFiYzdiOWI5NzYyM2UwNDc4MjE5YjFhMzIzODNlZTA3ZWIwZTZhNjZmNWVmOWY4NTU1OTNkZjAifX19";
    public static String t_w = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzg5N2FlNTRjMWQzZGUyY2U0YTVmMGY0OTQzOTgyNTM4YThhN2M2OWYxNzRiZTU3ZDc0YTMzZWU5YmM1ZjY3In19fQ==";
    public static String s_w = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjY1N2JkMTg2OGRhNjczNmNjNGM0MzUzOTg5ZTZhNDU3NDM3YmRkZDM5MmM4MmMyNDhkMTQyMDcwYThkZDgzIn19fQ==";
    private static Field profileField;

    public MakeItem(Material material) {
        this.ik = new ItemStack(material);
    }

    public MakeItem(Material material, byte data) {
        this.ik = new ItemStack(material, 1, data);
    }

    public MakeItem(String owner) {
        this.ik = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) this.ik.getItemMeta();
        skullMeta.setOwner(owner);
        this.ik.setItemMeta(skullMeta);
    }

    public MakeItem(String owner, String name) {
        this.ik = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta skullMeta = (SkullMeta) this.ik.getItemMeta();
        skullMeta.setDisplayName(name);
        skullMeta.setOwner(owner);
        this.ik.setItemMeta(skullMeta);
    }

    public MakeItem addEnchantment(Enchantment enchant, int level) {
        this.ik.addUnsafeEnchantment(enchant, level);
        return this;
    }

    public static ItemStack getCustomSkullURL(String url) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] data = Base64.getEncoder()
                .encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { url }).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(data)));
        try {
            if (profileField == null) {
                profileField = meta.getClass().getDeclaredField("profile");
            }
            profileField.setAccessible(true);
            profileField.set(meta, profile);

            item.setItemMeta(meta);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return item;
    }

    public static ItemStack getCustomSkullTexture(String texture) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);

        profile.getProperties().put("textures", new Property("textures", new String(texture)));
        try {
            if (profileField == null) {
                profileField = meta.getClass().getDeclaredField("profile");
            }
            profileField.setAccessible(true);
            profileField.set(meta, profile);

            item.setItemMeta(meta);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return item;
    }

    @SuppressWarnings("deprecation")
    public MakeItem(int material, byte data) {
        this.ik = new ItemStack(material, 1, data);
    }

    public MakeItem(ItemStack ik) {
        this.ik = ik.clone();
    }

    @SuppressWarnings("deprecation")
    public MakeItem(int material) {
        this.ik = new ItemStack(material);
    }

    @Deprecated
    public MakeItem setAmout(int amount) {
        this.ik.setAmount(amount);
        return this;
    }

    public MakeItem setAmount(int amount) {
        this.ik.setAmount(amount);
        return this;
    }

    public MakeItem setType(Material type) {
        this.ik.setType(type);
        return this;
    }

    public MakeItem setName(String name) {
        ItemMeta im = this.ik.getItemMeta();
        im.setDisplayName(TextUtil.parse(name));
        this.ik.setItemMeta(im);
        return this;
    }

    public MakeItem setDurability(Short durability) {
        ik.setDurability(durability);
        return this;
    }

    public MakeItem addGlow() {
        Glow glow = new Glow();
        ItemMeta itemMeta = this.ik.getItemMeta();
        itemMeta.addEnchant(glow, 1, true);
        this.ik.setItemMeta(itemMeta);

        return this;
    }

    public MakeItem setColor(Color color) {
        try {
            LeatherArmorMeta meta = (LeatherArmorMeta) this.ik.getItemMeta();
            meta.setColor(color);
            this.ik.setItemMeta(meta);
        } catch (Exception localException) {
        }
        return this;
    }

    public MakeItem setLore(ArrayList<String> lore) {
        ItemMeta im = this.ik.getItemMeta();
        ArrayList<String> lorer = new ArrayList<String>();
        for (String r : lore) {
            lorer.add(TextUtil.parse(r));
        }
        im.setLore(lorer);
        this.ik.setItemMeta(im);
        return this;
    }

    public MakeItem addLore(ArrayList<String> lore, ChatColor color) {
        ItemMeta im = this.ik.getItemMeta();
        ArrayList<String> lorer = new ArrayList<String>();
        for (String r : lore) {
            lorer.add(color + TextUtil.parse(r));
        }
        im.setLore(lorer);
        this.ik.setItemMeta(im);
        return this;
    }

    public MakeItem addLoreList(String... name) {
        String[] arrayOfString;
        int j = (arrayOfString = name).length;
        for (int i = 0; i < j; i++) {
            String s = arrayOfString[i];
            addLore(s);
        }
        return this;
    }

    public MakeItem addLore(ArrayList<String> lore) {
        ItemMeta im = this.ik.getItemMeta();
        ArrayList<String> lorer = new ArrayList<String>();
        for (String r : lore) {
            lorer.add(TextUtil.parse(r));
        }
        im.setLore(lorer);
        this.ik.setItemMeta(im);
        return this;
    }

    public MakeItem addLore(String lore) {
        ItemMeta im = this.ik.getItemMeta();
        List<String> lorer = new ArrayList<String>();
        if (im.hasLore()) {
            lorer = im.getLore();
        }
        if (lore.contains("/n")) {
            String[] arrayOfString;
            int j = (arrayOfString = lore.split("/n")).length;
            for (int i = 0; i < j; i++) {
                String x = arrayOfString[i];
                lorer.add(TextUtil.parse(x));
            }
        } else {
            lorer.add(TextUtil.parse(lore));
        }
        im.setLore(lorer);
        this.ik.setItemMeta(im);
        return this;
    }

    public MakeItem remLore(int amount) {
        ItemMeta im = this.ik.getItemMeta();
        List<String> lorer = new ArrayList<String>();
        if (im.hasLore()) {
            lorer = im.getLore();
        }
        for (int i = 0; i < amount; i++) {
            if (!lorer.isEmpty()) {
                lorer.remove(lorer.size() - 1);
            }
        }
        im.setLore(lorer);
        this.ik.setItemMeta(im);
        return this;
    }

    public MakeItem addLore(String[] lore) {
        ItemMeta im = this.ik.getItemMeta();
        List<String> lorer = new ArrayList<String>();
        if (im.hasLore()) {
            lorer = im.getLore();
        }
        String[] arrayOfString;
        int j = (arrayOfString = lore).length;
        for (int i = 0; i < j; i++) {
            String x = arrayOfString[i];
            lorer.add(TextUtil.parse(x));
        }
        im.setLore(lorer);
        this.ik.setItemMeta(im);
        return this;
    }

    public ItemStack build() {
        return this.ik;
    }

    public static boolean checkIsSimilar(ItemStack ik1, ItemStack ik2) {
        if (ik1 == null)
            return false;
        if (ik2 == null)
            return false;
        if (ik1.getType() == ik2.getType()) {
            if (ik1.hasItemMeta() && ik2.hasItemMeta()) {
                if (ik1.getItemMeta().hasDisplayName() && ik2.getItemMeta().hasDisplayName()) {
                    if (ik1.getItemMeta().getDisplayName().equals(ik2.getItemMeta().getDisplayName())) {
                        if (ik1.getItemMeta().hasLore() && ik2.getItemMeta().hasLore()) {
                            if (ik1.getItemMeta().getLore().equals(ik2.getItemMeta().getLore())) {
                                return true;
                            } else {
                                return false;
                            }
                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkIsSimilar(ItemStack ik1, ItemStack ik2, boolean use_lore) {
        if (ik1 == null)
            return false;
        if (ik2 == null)
            return false;
        if (ik1.getType() == ik2.getType()) {
            if (ik1.hasItemMeta() && ik2.hasItemMeta()) {
                if (ik1.getItemMeta().hasDisplayName() && ik2.getItemMeta().hasDisplayName()) {
                    if (ik1.getItemMeta().getDisplayName().equals(ik2.getItemMeta().getDisplayName())) {
                        if (use_lore) {
                            if (ik1.getItemMeta().hasLore() && ik2.getItemMeta().hasLore()) {
                                if (ik1.getItemMeta().getLore().equals(ik2.getItemMeta().getLore())) {
                                    return true;
                                } else {
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else {
                            return true;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Deprecated
    public MakeItem setMaxStackSize(int amount) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(ik);
        Field field = null;
        Field modifier = null;

        try {
            field = net.minecraft.server.v1_8_R3.Item.class.getDeclaredField("maxStackSize");
            modifier = Field.class.getDeclaredField("modifiers");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        field.setAccessible(true);
        modifier.setAccessible(true);

        try {
            modifier.setInt(field, field.getModifiers() & ~Modifier.PROTECTED);
            field.set(nmsItem.getItem(), amount);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ik = CraftItemStack.asBukkitCopy(nmsItem);
        return this;
    }

    @Deprecated
    public int getMaxStackSize() {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(ik);
        Field field = null;
        Field modifier = null;

        try {
            field = net.minecraft.server.v1_8_R3.Item.class.getDeclaredField("maxStackSize");
            modifier = Field.class.getDeclaredField("modifiers");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        field.setAccessible(true);
        modifier.setAccessible(true);

        try {
            modifier.setInt(field, field.getModifiers() & ~Modifier.PROTECTED);
            return (int) field.get(nmsItem.getItem());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Deprecated
    public static ItemStack setMaxStackSize(ItemStack is, int amount) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(is);
        Field field = null;
        Field modifier = null;

        try {
            field = net.minecraft.server.v1_8_R3.Item.class.getDeclaredField("maxStackSize");
            modifier = Field.class.getDeclaredField("modifiers");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        field.setAccessible(true);
        modifier.setAccessible(true);

        try {
            modifier.setInt(field, field.getModifiers() & ~Modifier.PROTECTED);
            field.set(nmsItem.getItem(), amount);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    public static String serializeItem(ItemStack itemStack) {
        if (itemStack == null) {
            return null;
        }
        ByteArrayOutputStream outputStream = null;
        try {
            Class<?> nbtTagCompoundClass = ReflectionUtil.getNMSClass("NBTTagCompound");
            Constructor<?> nbtTagCompoundConstructor = nbtTagCompoundClass.getConstructor();
            Object nbtTagCompound = nbtTagCompoundConstructor.newInstance();
            Object nmsItemStack = ReflectionUtil.getBukkitClass("inventory.CraftItemStack")
                    .getMethod("asNMSCopy", ItemStack.class).invoke(null, itemStack);
            ReflectionUtil.getNMSClass("ItemStack").getMethod("save", nbtTagCompoundClass).invoke(nmsItemStack, nbtTagCompound);
            outputStream = new ByteArrayOutputStream();
            ReflectionUtil.getNMSClass("NBTCompressedStreamTools").getMethod("a", nbtTagCompoundClass, OutputStream.class).invoke(null,
                    nbtTagCompound, outputStream);
        } catch (Exception e) {
        }
        return BaseEncoding.base64().encode(outputStream.toByteArray());
    }

    public static ItemStack deserializeItem(String itemStackString) {
        if (itemStackString.equals(null)) {
            return null;
        }
        ItemStack itemStack = null;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(BaseEncoding.base64().decode(itemStackString));
            Class<?> nbtTagCompoundClass = ReflectionUtil.getNMSClass("NBTTagCompound");
            Class<?> nmsItemStackClass = ReflectionUtil.getNMSClass("ItemStack");
            Object nbtTagCompound = null;
            nbtTagCompound = ReflectionUtil.getNMSClass("NBTCompressedStreamTools").getMethod("a", InputStream.class).invoke(null,
                    inputStream);
            Object craftItemStack = nmsItemStackClass.getMethod("createStack", nbtTagCompoundClass).invoke(null,
                    nbtTagCompound);
            itemStack = (ItemStack) ReflectionUtil.getBukkitClass("inventory.CraftItemStack")
                    .getMethod("asBukkitCopy", nmsItemStackClass).invoke(null, craftItemStack);
        } catch (Exception e) {
        }
        return itemStack;
    }


    private static class Glow extends Enchantment {
        public Glow() {
            super(200);
        }
        public boolean canEnchantItem(ItemStack i) {
            return false;
        }
        public boolean conflictsWith(Enchantment e) {
            return false;
        }
        public EnchantmentTarget getItemTarget() {
            return null;
        }
        public int getMaxLevel() {
            return 1;
        }
        public String getName() {
            return "Glow I";
        }
        public int getStartLevel() {
            return 1;
        }
    }
}

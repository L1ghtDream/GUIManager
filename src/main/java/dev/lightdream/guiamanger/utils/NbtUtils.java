package dev.lightdream.guiamanger.utils;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.inventory.ItemStack;

public class NbtUtils {

    public static ItemStack setNBT(ItemStack item, String attribute, Object value) {
        NBTItem nbtItem = new NBTItem(item);
        NBTCompound nbtCompound = nbtItem.addCompound("settings");
        nbtCompound.setObject(attribute, value);
        return nbtItem.getItem();
    }

    @SuppressWarnings("unused")
    public static Object getNBT(ItemStack item, String attribute) {
        NBTItem nbtItem = new NBTItem(item);
        NBTCompound nbtCompound = nbtItem.addCompound("settings");
        return nbtCompound.getObject(attribute, Object.class);
    }

}
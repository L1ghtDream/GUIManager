package dev.lightdream.guiamanger.utils;

import fr.minuskube.inv.content.SlotPos;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<String> color(List<String> list) {
        List<String> output = new ArrayList<>();
        list.forEach(line -> output.add(color(line)));
        return output;
    }

    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static SlotPos getSlotPosition(int slot) {
        slot++;
        int row = slot / 9;
        int column = slot % 9;
        if (column == 0) {
            column = 9;
            row--;
        }
        return new SlotPos(row, column - 1);
    }
}


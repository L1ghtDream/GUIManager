package dev.lightdream.guiamanger;

import dev.lightdream.guiamanger.dto.GUIUser;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public interface GUIMain {

    JavaPlugin getPlugin();

    GUIUser getGUIUser(Player player);


}

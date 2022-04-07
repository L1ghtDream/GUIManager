package dev.lightdream.guiamanger.managers;

import dev.lightdream.guiamanger.GUI;
import dev.lightdream.guiamanger.GUIMain;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;

public class EventManager implements Listener {

    private static EventManager instance;
    private final List<GUI> registeredGUIs = new ArrayList<>();


    public EventManager(GUIMain main) {
        main.getPlugin().getServer().getPluginManager().registerEvents(this, main.getPlugin());
    }

    public static EventManager get(GUIMain main) {
        if (instance == null) {
            instance = new EventManager(main);
        }
        return instance;
    }

    public void registerGUI(GUI gui) {
        registeredGUIs.add(gui);
    }

    public void unregisterGUI(GUI gui) {
        registeredGUIs.remove(gui);
    }

    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent event) {
        registeredGUIs.forEach(gui -> gui.c(event));
    }

}
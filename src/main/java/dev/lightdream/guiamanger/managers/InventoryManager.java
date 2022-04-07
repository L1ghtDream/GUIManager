package dev.lightdream.guiamanger.managers;

import dev.lightdream.guiamanger.GUIMain;

public class InventoryManager {

    private static fr.minuskube.inv.InventoryManager instance;

    public static fr.minuskube.inv.InventoryManager get(GUIMain main) {
        if (instance == null) {
            instance = new fr.minuskube.inv.InventoryManager(main.getPlugin());
            instance.init();
        }
        return instance;
    }

}

package dev.lightdream.guiamanger.dto;

import org.bukkit.entity.Player;

public interface GUIUser {

    boolean isOnline();

    Player getPlayer();

    static GUIUser of(Player player){
        return new GUIUser() {
            @Override
            public boolean isOnline() {
                return true;
            }

            @Override
            public Player getPlayer() {
                return player;
            }
        };
    }

}

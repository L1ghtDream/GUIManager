package dev.lightdream.guiamanger.dto.config;

import dev.lightdream.guiamanger.dto.Item;

import java.util.List;

public class GUIConfig {

    public String type;
    public String title;
    public int rows;
    public Item fillItem;
    public List<GUIItem> items;

    @SuppressWarnings("unused")
    public GUIConfig(String type, String title, int rows, Item fillItem, List<GUIItem> items) {
        this.type = type;
        this.title = title;
        this.rows = rows;
        this.fillItem = fillItem;
        this.items = items;
    }

    @SuppressWarnings("unused")
    public GUIConfig() {
    }
}
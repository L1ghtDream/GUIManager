package dev.lightdream.guiamanger.managers;

import dev.lightdream.guiamanger.dto.GUIFunction;

import java.util.HashMap;

public class GUIFunctionManager {

    public static GUIFunctionManager instance;
    public HashMap<String, GUIFunction> functions = new HashMap<>();

    public static GUIFunctionManager get() {
        if (instance == null) {
            instance = new GUIFunctionManager();
        }
        return instance;
    }

    public void registerFunction(String id, GUIFunction function) {
        functions.put(id, function);
    }

    public GUIFunction getFunction(String id) {
        return functions.get(id);
    }

}

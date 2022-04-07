package dev.lightdream.guiamanger.dto.config;

import com.cryptomorin.xseries.XMaterial;
import dev.lightdream.guiamanger.dto.GUIFunction;
import dev.lightdream.guiamanger.dto.Item;
import dev.lightdream.guiamanger.managers.GUIFunctionManager;

import java.util.ArrayList;
import java.util.List;

public class GUIItem {

    public XMaterial material;
    public Integer amount;
    public String displayName;
    public List<String> lore;
    public List<Integer> slots;
    public List<GUIItemArg> args;

    @SuppressWarnings("unused")
    public GUIItem() {
    }

    public GUIItem(XMaterial material, Integer amount, String displayName, List<String> lore, List<Integer> slots, List<GUIItemArg> args) {
        this.material = material;
        this.amount = amount;
        this.displayName = displayName;
        this.lore = lore;
        this.slots = slots;
        this.args = args;
    }

    public Item getItem() {
        return new Item(material, amount, displayName, lore);
    }

    public List<GUIFunction> getFunctions() {
        List<GUIFunction> guiFunctions = new ArrayList<>();

        args.forEach(arg -> {
            GUIFunction guiFunction = GUIFunctionManager.get().getFunction(arg.function);
            guiFunction.args = arg.args;
            guiFunctions.add(guiFunction);
        });

        return guiFunctions;
    }

    public static class GUIItemArg {
        public String function;
        public List<String> args;

        @SuppressWarnings("unused")
        public GUIItemArg() {
        }

        public GUIItemArg(String function, String arg) {
            this.function = function;
            this.args = new ArrayList<>();
            this.args.add(arg);
        }

        public GUIItemArg(String function, List<String> args) {
            this.function = function;
            this.args = args;
        }
    }


}
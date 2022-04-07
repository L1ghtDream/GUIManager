package dev.lightdream.guiamanger.dto;

import java.util.List;

public abstract class GUIFunction {

    public List<String> args;
    public abstract void execute(GUIUser user );
}

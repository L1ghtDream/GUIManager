package dev.lightdream.guiamanger;

import com.cryptomorin.xseries.XMaterial;
import dev.lightdream.guiamanger.dto.GUIUser;
import dev.lightdream.guiamanger.dto.config.GUIConfig;
import dev.lightdream.guiamanger.managers.EventManager;
import dev.lightdream.guiamanger.managers.InventoryManager;
import dev.lightdream.guiamanger.utils.ItemBuilder;
import dev.lightdream.guiamanger.utils.Utils;
import dev.lightdream.logger.Logger;
import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.InventoryListener;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

@SuppressWarnings("unused")
public abstract class GUI implements InventoryProvider {

    public final GUIMain main;
    public final GUIConfig config;
    private GUIUser user;
    private int page;

    public GUI(GUIMain main, GUIUser user, int page) {
        this.main = main;
        this.config = getConfig();
        if (this.config == null) {
            Logger.error("The gui config with this id does not exist in the config");
            return;
        }
        EventManager.get(main).registerGUI(this);
        this.user = user;
        this.page = page;
    }

    public GUI(GUIMain main, GUIUser user) {
        this(main, user, 0);
    }

    public SmartInventory getInventory() {
        return SmartInventory.builder()
                .provider(getProvider())
                .size(config.rows, 9)
                .title(config.title)
                .type(InventoryType.valueOf(config.type))
                .manager(InventoryManager.get(main))
                .listener(getInventoryClickListener())
                .listener(getInventoryCloseListener())
                .closeable(!preventClose())
                .build();
    }

    @Override
    public void init(Player player, InventoryContents contents) {
        if (!config.fillItem.material.equals(XMaterial.AIR)) {
            contents.fill(ClickableItem.empty(ItemBuilder.makeItem(config.fillItem)));
        }

        config.items.forEach(item -> {

            if (item.material.equals(XMaterial.AIR)) {
                item.slots.forEach(slot -> {
                    contents.set(Utils.getSlotPosition(slot), null);
                });
            }

            item.slots.forEach(slot -> {
                contents.set(Utils.getSlotPosition(slot),
                        ClickableItem.of(ItemBuilder.makeItem(item.getItem()), e -> {
                            item.getFunctions().forEach(function -> {
                                function.execute(user);
                            });
                        })
                );
            });
        });
    }

    @Override
    public void update(Player player, InventoryContents contents) {
        if (update()) {
            beforeUpdate(player, contents);
            init(player, contents);
        }
    }

    public abstract boolean update();

    public abstract GUIConfig getConfig();

    public abstract InventoryProvider getProvider();

    private void open(Player player) {
        getInventory().open(player);
    }

    private void open(CommandSender sender) {
        if (!(sender instanceof Player)) {
            return;
        }
        open((Player) sender);
    }

    private void open(GUIUser user) {
        if (!user.isOnline()) {
            return;
        }
        open(user.getPlayer());
    }

    public void open() {
        open(user);
    }

    public void beforeUpdate(Player player, InventoryContents contents) {

    }

    public InventoryListener<InventoryCloseEvent> getInventoryCloseListener() {
        return new InventoryListener<>(InventoryCloseEvent.class, this::a);
    }

    public InventoryListener<InventoryClickEvent> getInventoryClickListener() {
        return new InventoryListener<>(InventoryClickEvent.class, this::b);
    }

    public final void a(InventoryCloseEvent event) {
        GUIUser eventUser = main.getGUIUser((Player) event.getPlayer());

        if (!event.getView().getTitle().equals(Utils.color(config.title))) {
            return;
        }

        if (!user.equals(eventUser)) {
            return;
        }

        EventManager.get(main).unregisterGUI(this);
        onInventoryClose(event);
    }

    public final void b(InventoryClickEvent event) {
        GUIUser eventUser = main.getGUIUser((Player) event.getWhoClicked());

        if (!event.getView().getTitle().equals(Utils.color(config.title))) {
            return;
        }

        if (!user.equals(eventUser)) {
            return;
        }

        onInventoryClick(event);
    }

    public final void c(InventoryClickEvent event) {
        GUIUser eventUser = main.getGUIUser((Player) event.getWhoClicked());

        if (event.getRawSlot() < 9 * config.rows) {
            return;
        }

        if (!event.getView().getTitle().equals(Utils.color(config.title))) {
            return;
        }

        if (!user.equals(eventUser)) {
            return;
        }

        onPlayerInventoryClick(event);
    }

    public void onInventoryClose(InventoryCloseEvent event) {

    }

    public void onInventoryClick(InventoryClickEvent event) {

    }

    public void onPlayerInventoryClick(InventoryClickEvent event) {

    }

    public abstract boolean preventClose();

    @SuppressWarnings("unchecked")
    public <T> T getInventoryHandler(GUIUser user, Class<T> clazz) {
        SmartInventory smartInventory = InventoryManager.get(main).getInventory(user.getPlayer()).orElse(null);
        if (smartInventory == null) {
            return null;
        }
        InventoryProvider provider = smartInventory.getProvider();

        if (provider == null) {
            return null;
        }

        return (T) provider;
    }

    public GUIUser getUser() {
        return user;
    }

    public abstract void changePage(int page);

    public void nextPage() {
        changePage(page + 1);
    }

    public void backPage() {
        if (page != 0) {
            changePage(page - 1);
        }
    }

    public int getPage() {
        return page;
    }
}
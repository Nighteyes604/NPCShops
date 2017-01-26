package me.nighteyes604.npcshops;

import com.google.inject.Inject;

import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.InteractEntityEvent;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.item.inventory.InteractInventoryEvent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetype;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.property.AcceptsItems;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import java.util.HashSet;

import me.nighteyes604.npcshops.commands.CreateNpcCommand;
import me.nighteyes604.npcshops.config.NpcShopsConfig;
import me.nighteyes604.npcshops.data.shopkeeper.ImmutableShopKeeperData;
import me.nighteyes604.npcshops.data.shopkeeper.ShopKeeperData;
import me.nighteyes604.npcshops.data.shopkeeper.ShopKeeperDataBuilder;
import me.nighteyes604.npcshops.listeners.ShopKeeperListener;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

@Plugin(
        id = "npcshops",
        name = "NpcShops",
        description = "Allows npcs to be used as shops",
        authors = {
                "Nighteyes604"
        }
)
public class NpcShops {

    private static Logger logger;

    @Inject
    @DefaultConfig(sharedRoot = false)
    private ConfigurationLoader<CommentedConfigurationNode> configurationLoader;

    private NpcShopsConfig configuration;

    public static Logger getLogger() {
        return logger;
    }

    @Inject
    public NpcShops(Logger logger) {
        NpcShops.logger = logger;
    }

    @Listener
    public void onPreInitialization(GamePreInitializationEvent event) {

        //Create the configuration
        configuration = new NpcShopsConfig(configurationLoader);

        //Register the commands
        Sponge.getCommandManager().register(this, new CreateNpcCommand().getCommand(), "createnpc");


    }

    @Listener
    public void onGamePreInit(GamePreInitializationEvent event) {

        //Register data
        Sponge.getDataManager().register(ShopKeeperData.class, ImmutableShopKeeperData.class, new ShopKeeperDataBuilder());

        //Register Listeners
        Sponge.getEventManager().registerListeners(this, new ShopKeeperListener());

    }


    @Listener
    public void onServerStart(InteractEntityEvent.Secondary event, @Root Player player, @Getter("getTargetEntity") Entity entity ) {

        if( !(entity instanceof Carrier)) {
            return;
        }

        event.setCancelled(true);

        if(entity.get(ShopKeeperData.class).isPresent()) {
            player.sendMessage(Text.of("Entity already has data." ));
        } else {
            player.sendMessage(Text.of("Adding shopkeeper data."));
            entity.offer(new ShopKeeperData(player.getUniqueId(), false));
        }

        //player.openInventory(getShopInventory(), Cause.of(NamedCause.of("npcshops", "test")));

        //player.openInventory(getShopInventory(), Cause.of(NamedCause.of("npcshops", "test")));

        //Inventory inventory = getShopInventory();

        //logger.error(getShopInventory().getProperties(InventoryProperty.class).toString());

        //event.setCancelled(true);*/

    }

    @Listener
    public void onClick(InteractInventoryEvent.Close event) {
        //shopInventory = event.getTargetInventory().first();
        getLogger().error("close");
    }

    @Listener
    public void onOpenInventory(InteractInventoryEvent.Open event) {
        getLogger().error("open");
    }

    private Inventory shopInventory;

    private Inventory getShopInventory() {
        if(shopInventory == null) {
            shopInventory = Inventory.builder().of(
                    InventoryArchetype.builder()
                            .with(InventoryArchetypes.CHEST)
                            .property(new InventoryTitle(Text.of("Test chest")))
                            .property(new AcceptsItems(new HashSet<ItemType>()))
                            .build("test", "test_name"))
                    .build(Sponge.getPluginManager().getPlugin("npcshops"));
        }

        return shopInventory;
    }

    public NpcShopsConfig getConfiguration() {
        return configuration;
    }

}

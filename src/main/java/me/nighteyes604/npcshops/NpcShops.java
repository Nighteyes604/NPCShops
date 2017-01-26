package me.nighteyes604.npcshops;

import com.google.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.event.Level;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.key.KeyFactory;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.entity.living.monster.Zombie;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.event.cause.NamedCause;
import org.spongepowered.api.event.entity.InteractEntityEvent;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.filter.cause.Root;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.item.inventory.ClickInventoryEvent;
import org.spongepowered.api.event.item.inventory.InteractInventoryEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.Carrier;
import org.spongepowered.api.item.inventory.Container;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.InventoryArchetype;
import org.spongepowered.api.item.inventory.InventoryArchetypes;
import org.spongepowered.api.item.inventory.InventoryProperty;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.property.AbstractInventoryProperty;
import org.spongepowered.api.item.inventory.property.AcceptsItems;
import org.spongepowered.api.item.inventory.property.InventoryTitle;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.chat.ChatTypes;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;

import java.util.HashSet;

import javax.security.auth.login.Configuration;

import me.nighteyes604.npcshops.commands.CreateNpcCommand;
import me.nighteyes604.npcshops.config.NpcShopsConfig;
import me.nighteyes604.npcshops.data.isnpcshop.ImmutableShopData;
import me.nighteyes604.npcshops.data.isnpcshop.ShopData;
import me.nighteyes604.npcshops.data.isnpcshop.ShopDataBuilder;
import me.nighteyes604.npcshops.entity.ShopEntity;
import ninja.leaping.configurate.ConfigurationOptions;
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

    @Inject
    private Logger logger;

    @Inject
    @DefaultConfig(sharedRoot = false)
    private ConfigurationLoader<CommentedConfigurationNode> configurationLoader;

    private NpcShopsConfig configuration;

    public Logger getLogger() {
        return logger;
    }

    @Listener
    public void onPreInitialization(GamePreInitializationEvent event) {

        //Create the configuration
        configuration = new NpcShopsConfig(configurationLoader);

        //Register the commands
        Sponge.getCommandManager().register(this, new CreateNpcCommand().getCommand(), "createnpc");


    }

    @Listener
    public void onGameInit(GameInitializationEvent event) {
        Sponge.getDataManager().register(ShopData.class, ImmutableShopData.class, new ShopDataBuilder());

    }


    @Listener
    public void onServerStart(InteractEntityEvent.Secondary event, @Root Player player, @Getter("getTargetEntity") Entity entity ) {

        if( !(entity instanceof Carrier)) {
            return;
        }

        event.setCancelled(true);

        player.openInventory(getShopInventory(), Cause.of(NamedCause.of("npcshops", "test")));

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

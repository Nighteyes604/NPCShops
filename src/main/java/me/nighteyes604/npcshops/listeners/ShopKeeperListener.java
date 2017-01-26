package me.nighteyes604.npcshops.listeners;

import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.DamageEntityEvent;
import org.spongepowered.api.event.entity.MoveEntityEvent;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.filter.data.Has;

import me.nighteyes604.npcshops.data.shopkeeper.ShopKeeperData;

public class ShopKeeperListener {

    @Listener
    public void onNpcDamage(DamageEntityEvent event, @Getter("getTargetEntity") @Has(ShopKeeperData.class) Entity entity) {
        event.setCancelled(true);
    }

    @Listener
    public void onNpcMove(MoveEntityEvent event, @Getter("getTargetEntity") @Has(ShopKeeperData.class) Entity entity) {
        event.setCancelled(true);
    }
}

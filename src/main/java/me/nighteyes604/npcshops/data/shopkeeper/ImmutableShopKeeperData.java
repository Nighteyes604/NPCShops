package me.nighteyes604.npcshops.data.shopkeeper;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableBooleanData;
import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableData;
import org.spongepowered.api.data.value.immutable.ImmutableValue;

import java.util.UUID;

import me.nighteyes604.npcshops.NpcShops;
import me.nighteyes604.npcshops.data.NpcShopKeys;

public class ImmutableShopKeeperData extends AbstractImmutableData<ImmutableShopKeeperData, ShopKeeperData> {

    private UUID ownerId;
    private boolean isShopOpen;

    public ImmutableShopKeeperData(UUID ownerId, boolean isShopOpen) {
        this.ownerId = ownerId;
        this.isShopOpen = isShopOpen;
        registerGetters();
    }

    @Override
    protected void registerGetters() {
        registerKeyValue(NpcShopKeys.OWNER_ID, this::getOwnerId);
        registerFieldGetter(NpcShopKeys.OWNER_ID, this::getOwnerId);

        registerKeyValue(NpcShopKeys.IS_SHOP_OPEN, this::isShopOpen);
        registerFieldGetter(NpcShopKeys.IS_SHOP_OPEN, this::isShopOpen);
    }

    @Override
    public ShopKeeperData asMutable() {
        return new ShopKeeperData(this.ownerId, this.isShopOpen);
    }

    @Override
    public int getContentVersion() {
        return ShopKeeperData.CONTENT_VERSION;
    }

    public ImmutableValue<UUID> getOwnerId() {
        return Sponge.getRegistry().getValueFactory().createValue(NpcShopKeys.OWNER_ID, this.ownerId).asImmutable();
    }

    public ImmutableValue<Boolean> isShopOpen() {
        return Sponge.getRegistry().getValueFactory().createValue(NpcShopKeys.IS_SHOP_OPEN, this.isShopOpen).asImmutable();
    }
}

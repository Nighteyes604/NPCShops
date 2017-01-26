package me.nighteyes604.npcshops.data.shopkeeper;

import com.google.common.base.Preconditions;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractData;
import org.spongepowered.api.data.merge.MergeFunction;
import org.spongepowered.api.data.value.mutable.Value;

import java.util.Optional;
import java.util.UUID;

import me.nighteyes604.npcshops.NpcShops;
import me.nighteyes604.npcshops.data.NpcShopKeys;

public class ShopKeeperData extends AbstractData<ShopKeeperData, ImmutableShopKeeperData> {

    public static final int CONTENT_VERSION = 1;

    private UUID ownerId;
    private boolean isShopOpen;

    public ShopKeeperData(UUID ownerId, boolean isShopOpen) {
        this.ownerId = ownerId;
        this.isShopOpen = isShopOpen;
        registerGettersAndSetters();
    }

    @Override
    public Optional<ShopKeeperData> fill(DataHolder dataHolder, MergeFunction overlap) {
        ShopKeeperData data = overlap.merge( this, from(dataHolder.toContainer()).orElse(null));

        if(data == null) {
            return Optional.empty();
        }

        setOwnerId(data.getOwnerId().get());
        setShopOpen(data.isShopOpen().get());

        return Optional.of(this);
    }

    @Override
    public Optional<ShopKeeperData> from(DataContainer container) {
        if(!container.contains(NpcShopKeys.IS_SHOP_OPEN, NpcShopKeys.OWNER_ID)) {
            return Optional.empty();
        }

        this.ownerId = container.getObject(NpcShopKeys.OWNER_ID.getQuery(), UUID.class).get();
        this.isShopOpen = container.getBoolean(NpcShopKeys.IS_SHOP_OPEN.getQuery()).get();
        return Optional.of(this);
    }

    @Override
    public ShopKeeperData copy() {
        return new ShopKeeperData(this.ownerId, this.isShopOpen);
    }

    @Override
    public ImmutableShopKeeperData asImmutable() {
        return new ImmutableShopKeeperData(this.ownerId, this.isShopOpen);
    }

    @Override
    public int getContentVersion() {
        return CONTENT_VERSION;
    }

    @Override
    protected void registerGettersAndSetters() {
        registerFieldGetter(NpcShopKeys.OWNER_ID, this::getOwnerId);
        registerFieldSetter(NpcShopKeys.OWNER_ID, value -> setOwnerId(value));
        registerKeyValue(NpcShopKeys.OWNER_ID, this::getOwnerId);

        registerFieldGetter(NpcShopKeys.IS_SHOP_OPEN, this::isShopOpen);
        registerFieldSetter(NpcShopKeys.IS_SHOP_OPEN, value -> setShopOpen(value));
        registerKeyValue(NpcShopKeys.IS_SHOP_OPEN, this::isShopOpen);

    }

    public Value<UUID> getOwnerId() {
        return Sponge.getRegistry().getValueFactory().createValue(NpcShopKeys.OWNER_ID, ownerId);
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public Value<Boolean> isShopOpen() {
        return Sponge.getRegistry().getValueFactory().createValue(NpcShopKeys.IS_SHOP_OPEN, isShopOpen);
    }

    public void setShopOpen(boolean shopOpen) {
        isShopOpen = shopOpen;
    }

    @Override
    public DataContainer toContainer() {
        return super.toContainer()
                .set(NpcShopKeys.OWNER_ID, this.ownerId)
                .set(NpcShopKeys.IS_SHOP_OPEN, this.isShopOpen);
    }
}

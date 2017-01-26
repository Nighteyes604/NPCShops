package me.nighteyes604.npcshops.data.shopkeeper;

import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.persistence.AbstractDataBuilder;
import org.spongepowered.api.data.persistence.InvalidDataException;

import java.util.Optional;
import java.util.UUID;

import me.nighteyes604.npcshops.NpcShops;
import me.nighteyes604.npcshops.data.NpcShopKeys;

public class ShopKeeperDataBuilder extends AbstractDataBuilder<ShopKeeperData> implements DataManipulatorBuilder<ShopKeeperData, ImmutableShopKeeperData>  {

    public ShopKeeperDataBuilder() {
        this(ShopKeeperData.class, ShopKeeperData.CONTENT_VERSION);
    }

    public ShopKeeperDataBuilder(Class<ShopKeeperData> requiredClass, int supportedVersion) {
        super(requiredClass, supportedVersion);
    }

    @Override
    public ShopKeeperData create() {
        return new ShopKeeperData(null, false);
    }

    @Override
    public Optional<ShopKeeperData> createFrom(DataHolder dataHolder) {
        return dataHolder.get(ShopKeeperData.class);
    }

    @Override
    protected Optional<ShopKeeperData> buildContent(DataView container) throws InvalidDataException {
        if(!container.contains(NpcShopKeys.IS_SHOP_OPEN, NpcShopKeys.OWNER_ID)) {
            return Optional.empty();
        }

        return Optional.of(new ShopKeeperData(
                container.getObject(NpcShopKeys.OWNER_ID.getQuery(), UUID.class).get(),
                container.getBoolean(NpcShopKeys.IS_SHOP_OPEN.getQuery()).get()));
    }

}

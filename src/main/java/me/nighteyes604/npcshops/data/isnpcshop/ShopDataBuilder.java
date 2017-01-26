package me.nighteyes604.npcshops.data.isnpcshop;

import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.manipulator.DataManipulatorBuilder;
import org.spongepowered.api.data.persistence.AbstractDataBuilder;
import org.spongepowered.api.data.persistence.InvalidDataException;
import org.spongepowered.api.util.ResettableBuilder;

import java.util.Optional;

import me.nighteyes604.npcshops.data.NpcShopKeys;

public class ShopDataBuilder implements DataManipulatorBuilder<ShopData, ImmutableShopData>  {

    @Override
    public ShopData create() {
        return new ShopData();
    }

    @Override
    public Optional<ShopData> createFrom(DataHolder dataHolder) {
        return Optional.of(dataHolder.get(ShopData.class).orElse(create()));
    }

    @Override
    public Optional<ShopData> build(DataView container) throws InvalidDataException {
        if(container.getBoolean(NpcShopKeys.IS_NPC_SHOP.getQuery()).isPresent()) {
            return Optional.of(new ShopData(container.getBoolean(NpcShopKeys.IS_NPC_SHOP.getQuery()).get()));
        }
        return Optional.empty();
    }

}

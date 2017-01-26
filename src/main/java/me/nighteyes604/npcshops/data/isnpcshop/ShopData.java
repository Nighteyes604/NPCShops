package me.nighteyes604.npcshops.data.isnpcshop;

import com.google.common.base.Preconditions;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.manipulator.mutable.common.AbstractBooleanData;
import org.spongepowered.api.data.merge.MergeFunction;

import java.util.Optional;

import me.nighteyes604.npcshops.data.NpcShopKeys;

public class ShopData extends AbstractBooleanData<ShopData, ImmutableShopData> {

    public ShopData() {
        this(false);
    }

    public ShopData(boolean value) {
        super(value, NpcShopKeys.IS_NPC_SHOP, false);
    }

    @Override
    public Optional<ShopData> fill(DataHolder dataHolder, MergeFunction overlap) {
        ShopData data = Preconditions.checkNotNull(overlap).merge( this, from(dataHolder.toContainer()).orElse(null));
        return Optional.of( setValue(data.getValue()) );
    }

    @Override
    public Optional<ShopData> from(DataContainer container) {
        if(container.getBoolean(NpcShopKeys.IS_NPC_SHOP.getQuery()).isPresent()) {
            return Optional.of( setValue(container.getBoolean(NpcShopKeys.IS_NPC_SHOP.getQuery()).get()));
        }
        return Optional.empty();
    }

    @Override
    public ShopData copy() {
        return new ShopData(getValue());
    }

    @Override
    public ImmutableShopData asImmutable() {
        return new ImmutableShopData(getValue());
    }

    @Override
    public int getContentVersion() {
        return 1;
    }
}

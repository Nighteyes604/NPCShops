package me.nighteyes604.npcshops.data.isnpcshop;

import org.spongepowered.api.data.manipulator.immutable.common.AbstractImmutableBooleanData;

import me.nighteyes604.npcshops.data.NpcShopKeys;

public class ImmutableShopData extends AbstractImmutableBooleanData<ImmutableShopData, ShopData> {

    private boolean isShop;

    public ImmutableShopData(boolean value) {
        super(value, NpcShopKeys.IS_NPC_SHOP, false);
    }

    @Override
    public ShopData asMutable() {
        return new ShopData(this.isShop);
    }

    @Override
    public int getContentVersion() {
        return 0;
    }
}

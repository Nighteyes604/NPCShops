package me.nighteyes604.npcshops.data.shopinventory;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.MemoryDataContainer;
import org.spongepowered.api.data.Queries;

import me.nighteyes604.npcshops.data.NpcShopKeys;

public class ShopInventory implements DataSerializable {

    private String display = "testing";

    @Override
    public int getContentVersion() {
        return 1;
    }

    @Override
    public DataContainer toContainer() {
        return new MemoryDataContainer()
                .set(Queries.CONTENT_VERSION, getContentVersion())
                .set(ShopInventoryKeys.TESTING_DISPLAY.getQuery(), display);
    }
}

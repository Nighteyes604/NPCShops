package me.nighteyes604.npcshops.data;

import com.google.common.reflect.TypeToken;

import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.key.KeyFactory;
import org.spongepowered.api.data.value.mutable.Value;

public class NpcShopKeys {

    public static final Key<Value<Boolean>> IS_NPC_SHOP = KeyFactory.makeSingleKey(
            TypeToken.of(Boolean.class),
            new TypeToken<Value<Boolean>>() {},
            DataQuery.of("IsNpcShop"),
            "npcshops:is_npc_shop",
            "IsNpcShop");

}

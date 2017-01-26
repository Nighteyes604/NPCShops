package me.nighteyes604.npcshops.data;

import com.google.common.reflect.TypeToken;

import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.key.KeyFactory;
import org.spongepowered.api.data.value.mutable.Value;

import java.util.UUID;

public class NpcShopKeys {

    public static final Key<Value<Boolean>> IS_SHOP_OPEN = KeyFactory.makeSingleKey(
            TypeToken.of(Boolean.class),
            new TypeToken<Value<Boolean>>() {},
            DataQuery.of("IsShopOpen"),
            "npcshops:is_shop_open",
            "IsShopOpen");

    public static final Key<Value<UUID>> OWNER_ID = KeyFactory.makeSingleKey(
            TypeToken.of(UUID.class),
            new TypeToken<Value<UUID>>() {},
            DataQuery.of("OwnerID"),
            "npcshops:owner_id",
            "OwnerID");

}

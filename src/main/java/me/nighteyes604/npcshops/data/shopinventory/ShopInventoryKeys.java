package me.nighteyes604.npcshops.data.shopinventory;

import com.google.common.reflect.TypeToken;

import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.key.KeyFactory;
import org.spongepowered.api.data.value.mutable.Value;

public class ShopInventoryKeys {

    public static final Key<Value<String>> TESTING_DISPLAY = KeyFactory.makeSingleKey(
            TypeToken.of(String.class),
            new TypeToken<Value<String>>() {},
            DataQuery.of("TestingDisplay"),
            "npcshops:testing_display",
            "TestingDisplay");


}

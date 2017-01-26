package me.nighteyes604.npcshops.config;

import java.io.IOException;

import ninja.leaping.configurate.SimpleConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMapper;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

@ConfigSerializable
public class NpcShopsConfig {

    private ObjectMapper<NpcShopsConfig>.BoundInstance mapper;
    private ConfigurationLoader<CommentedConfigurationNode> loader;
    private CommentedConfigurationNode rootNode;

    public NpcShopsConfig(ConfigurationLoader<CommentedConfigurationNode> loader) {
        this.loader = loader;
        this.rootNode = rootNode;
        try {
            this.mapper = ObjectMapper.forObject(this);
        } catch (ObjectMappingException e) {
            e.printStackTrace();
        }

        this.load();
        this.save();
    }

    public void load() {
        try {
            this.rootNode = this.loader.load();
            this.mapper.populate(rootNode);
        } catch (ObjectMappingException | IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            this.mapper.serialize(rootNode);
            this.loader.save(rootNode);
        } catch (ObjectMappingException | IOException e) {
            e.printStackTrace();
        }
    }

    @Setting(value = "display", comment = "Display this text")
    private String display = "sample";

    public String getDisplay() {
        return display;
    }
}

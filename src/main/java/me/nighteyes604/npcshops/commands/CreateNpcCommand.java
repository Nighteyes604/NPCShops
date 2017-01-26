package me.nighteyes604.npcshops.commands;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandCallable;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.args.CommandElement;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.entity.living.Human;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.cause.Cause;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Optional;

public class CreateNpcCommand implements CommandExecutor {

    public CommandCallable getCommand() {
        return CommandSpec.builder()
                .description(Text.of("Spawn a shop NPC"))
                .permission("npcshops.normal.create")
                .executor(this)
                .arguments(
                        GenericArguments.optional(GenericArguments.catalogedElement(Text.of("type"), EntityType.class), EntityTypes.HUMAN))
                .build();
    }



    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if(!(src instanceof Player)) {
            throw new CommandException(Text.of(TextColors.RED, "You must be a player to use this command."));
        }

        EntityType type = args.<EntityType>getOne("type").orElse(EntityTypes.HUMAN);
        Location<World> location = (((Player)src).getLocation());

        Entity npc = location.createEntity(type);
        if(!location.spawnEntity(npc, Cause.source(Sponge.getPluginManager().getPlugin("npcshops")).owner(src).build())) {
            throw new CommandException(Text.of(TextColors.RED, "There was an error spawning the shop NPC."));
        }

        src.sendMessage(Text.of("Your shop NPC has been created."));
        return CommandResult.affectedEntities(1);
    }
}

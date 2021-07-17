package littlecrow.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import littlecrow.commands.Interfaces.ComponentInt;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Warp implements ComponentInt {

    public static HashMap<String, Vec3d> warpList;

    public static int setWarp(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {

        final ServerCommandSource source = ctx.getSource();
        final Entity player = source.getPlayer();
        final Vec3d pos = player.getPos();


        warpList.put("home", pos);

        source.getMinecraftServer()
                .getPlayerManager()
                .broadcastChatMessage(new LiteralText("warp set").formatted(Formatting.BLUE), MessageType.CHAT, player.getUuid());
        return 1;

    }

    public static int warp(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException{

        final ServerCommandSource source = ctx.getSource();
        final Entity player = source.getPlayer();
        player.teleport(warpList.get("home").x,warpList.get("home").y, warpList.get("home").z);
        source.getMinecraftServer()
                .getPlayerManager()
                .broadcastChatMessage(new LiteralText("warped").formatted(Formatting.BLUE), MessageType.CHAT, player.getUuid());

        return 0;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        warpList = (HashMap<String, Vec3d>) Arrays.stream(tag.getString("warpList")
                .split(","))
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(e -> e[0], e -> Vec3d.unpackRgb(Integer.parseInt(e[1]))));
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putString("warpList", warpList.toString());
    }


}

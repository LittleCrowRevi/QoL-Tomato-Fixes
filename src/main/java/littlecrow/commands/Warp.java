package littlecrow.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.entity.Entity;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;

public class Warp {

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
}

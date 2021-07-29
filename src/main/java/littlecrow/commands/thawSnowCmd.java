package littlecrow.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static littlecrow.commands.modsLoaded.isModLoaded;

public class thawSnowCmd {

    public static void thawSnow(CommandContext<ServerCommandSource> ctx, int size) throws CommandSyntaxException {
        int radius = size / 2;
        removeThatSnow(ctx, radius, 0);
    }

    public static void thawSnow(CommandContext<ServerCommandSource> ctx, int size, int height) throws CommandSyntaxException {
        int radius = size / 2;
        removeThatSnow(ctx, radius, height);
    }


    public static int thawSnow(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        int radius = 8;
        removeThatSnow(ctx, radius, 0);
        return 1;
    }

    public static void removeThatSnow(CommandContext<ServerCommandSource> ctx, int radius, int height) throws CommandSyntaxException {
        ServerCommandSource source = ctx.getSource();
        Entity player = source.getPlayer();
        World world = source.getWorld();
        int h = (int) (player.getY() + height);
        System.out.println(h);
        int snowCount = 0;
        for (long x = (long) (player.getX() - radius); x <= player.getX() + radius; x++) {
            for (long y = (long) player.getY() - (height / 2); y <= h + (height / 2); y++) {
                for (long z = (long) (player.getZ() - radius); z <= player.getZ() + radius; z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    Block snow =Registry.BLOCK.get(new Identifier("minecraft", "snow"));
                    Block seasonalSnow =  isModLoaded("seasons") ? Registry.BLOCK.get(new Identifier("seasons:seasonal_snow")) : null;
                    Block e = world.getBlockState(pos).getBlock();
                    if (e != null && (e.is(snow) || e.is(seasonalSnow))) {
                        snowCount++;
                        world.breakBlock(pos, false);
                    }
                }
            }

        }
        source.getMinecraftServer()
                .getPlayerManager()
                .broadcastChatMessage(new LiteralText("removed " + snowCount + " snow.")
                        .formatted(Formatting.BLUE), MessageType.SYSTEM, player.getUuid());
    }
}

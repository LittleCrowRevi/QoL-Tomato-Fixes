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

import static littlecrow.commands.modsLoaded.isModLoaded;

public class thawSnowCmd {

    public static void thawSnow(CommandContext<ServerCommandSource> ctx, int size) throws CommandSyntaxException {
        int radius = size / 2;
        removeThatSnow(ctx, radius);
    }

    public static int thawSnow2(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
        int radius = 8;
        removeThatSnow(ctx, radius);
        return 1;
    }

    public static void removeThatSnow(CommandContext<ServerCommandSource> ctx, int radius) throws CommandSyntaxException {
        ServerCommandSource source = ctx.getSource();
        Entity player = source.getPlayer();
        World world = source.getWorld();
        int snowCount = 0;
        for (long x = (long) (player.getX() - radius); x <= player.getX() + radius; x++) {
            for (long z = (long) (player.getZ() - radius); z <= player.getZ() + radius; z++) {
                BlockPos pos = new BlockPos(x, player.getY(), z);
                Block snow =Registry.BLOCK.get(new Identifier("minecraft", "snow"));
                Block seasonalSnow =  isModLoaded("seasons") ? Registry.BLOCK.get(new Identifier("seasons:seasonal_snow")) : null;
                Block e = world.getBlockState(pos).getBlock();
                if (e != null && (e.is(snow) || e.is(seasonalSnow))) {
                    snowCount++;
                    world.breakBlock(pos, false);
                }
            }
        }
        source.getMinecraftServer()
                .getPlayerManager()
                .broadcastChatMessage(new LiteralText("removed " + snowCount + " snow.")
                        .formatted(Formatting.BLUE), MessageType.SYSTEM, player.getUuid());
    }
}

package littlecrow.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SnowBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Objects;

public class thawSnowCmd {

    public static int thawSnow(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {

        ServerCommandSource source = ctx.getSource();
        Entity player = source.getPlayer();
        World world = source.getWorld();
        int snowCount = 0;
        for (long x = (long) player.getX() - 8; x <= player.getX() + 8; x++) {
             for (long z = (long) (player.getZ() - 8); z <= player.getZ() + 8; z++) {
                 BlockPos pos = new BlockPos(x, player.getY(), z);
                 Block snow = Registry.BLOCK.get(new Identifier("minecraft", "snow"));
                 Block seasonalSnow = Registry.BLOCK.get(new Identifier("seasons:seasonal_snow"));
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
        return 1;
    }
}

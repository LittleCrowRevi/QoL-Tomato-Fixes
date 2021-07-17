package littlecrow.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class thawSnowCmd {

    public static int thawSnow(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {

        ServerCommandSource source = ctx.getSource();
        Entity player = source.getPlayer();
        World world = source.getWorld();
        for (long x = (long) player.getX() - 8; x <= player.getX() + 8; x++) {
            for (long y = 50; y <= 80; y++) {
                for (long z = (long) (player.getZ() - 8); z <= player.getZ() + 8; z++) {
                    Block snow = Registry.BLOCK.get(new Identifier("minecraft:snow"));
                    BlockState b = world.getBlockState(BlockPos.fromLong(x + y + z));
                   if (b.getBlock().is(snow)) {
                       world.breakBlock(BlockPos.fromLong(x + y + z), false);
                   }
                }
            }
        }


        return 1;
    }
}

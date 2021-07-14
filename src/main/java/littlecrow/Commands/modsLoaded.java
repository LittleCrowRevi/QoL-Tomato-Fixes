package littlecrow.Commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class modsLoaded {

    public static Boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }

    public static int conf(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {

        final PlayerEntity self = ctx.getSource().getPlayer();
        final Text text;
        if (isModLoaded("modern_industrialization") && isModLoaded("indrev") && isModLoaded("techreborn")) {
            text = new LiteralText("All tech mods loaded!").formatted(Formatting.BLUE);
        } else {
            text = new LiteralText("Mods not recognized!").formatted(Formatting.BLUE);
        }
        ctx.getSource().getMinecraftServer().getPlayerManager().broadcastChatMessage(text, MessageType.CHAT, self.getUuid());
        return 1;

    }
}

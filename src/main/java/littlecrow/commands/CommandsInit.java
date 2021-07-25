package littlecrow.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.argument;

public class CommandsInit {

    public CommandsInit() {

        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {

            LiteralCommandNode<ServerCommandSource> modLoadNode = CommandManager
                    .literal("mods")
                    .executes(modsLoaded::conf)
                    .build();
            dispatcher.getRoot().addChild(modLoadNode);

            LiteralCommandNode<ServerCommandSource> wNode = CommandManager
                    .literal("warp")
                    .build();
            LiteralCommandNode<ServerCommandSource> setWarpNode = CommandManager
                    .literal("set")
                    .executes(Warp::setWarp)
                    .build();
            LiteralCommandNode<ServerCommandSource> warpNode = CommandManager
                    .literal("home")
                    .executes(Warp::warp)
                    .build();
            dispatcher.getRoot().addChild(wNode);
            wNode.addChild(setWarpNode);
            dispatcher.getRoot().addChild(warpNode);

            LiteralCommandNode<ServerCommandSource> thawSnowNode = CommandManager
                    .literal("thaw")
                    .then(argument("radius", IntegerArgumentType.integer())
                            .then(argument("height", IntegerArgumentType.integer())
                                    .executes(ctx -> {
                                        int r = IntegerArgumentType.getInteger(ctx, "radius");
                                        int h = IntegerArgumentType.getInteger(ctx, "height");
                                        thawSnowCmd.thawSnow(ctx, r, h);
                                        return 1;
                                    }))
                            .executes(context -> {
                                thawSnowCmd.thawSnow(context, IntegerArgumentType.getInteger(context, "radius"));
                                return 1;
                            }))
                    .executes(thawSnowCmd::thawSnow)
                    .build();
            dispatcher.getRoot().addChild(thawSnowNode);
        });
    }
}

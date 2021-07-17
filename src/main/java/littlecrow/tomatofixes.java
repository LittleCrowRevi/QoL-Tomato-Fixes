package littlecrow;

import com.mojang.brigadier.tree.LiteralCommandNode;
import littlecrow.commands.Warp;
import littlecrow.commands.modsLoaded;
import littlecrow.dynamic.compRecipes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;


public class tomatofixes implements ModInitializer {



    public static Boolean mLoaded = false;
    @Override
    public void onInitialize() {


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
        });

        if (modsLoaded.isModLoaded("modern_industrialization") || modsLoaded.isModLoaded("techreborn") || modsLoaded.isModLoaded("indrev")) {
            mLoaded = true;
            compRecipes.createRecipes();
        }

    }

}


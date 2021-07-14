package littlecrow;

import com.mojang.brigadier.tree.LiteralCommandNode;
import littlecrow.Commands.modsLoaded;
import littlecrow.dynamic.compRecipes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class TomatoFix implements ModInitializer {


    public static Boolean mLoaded = false;
    @Override
    public void onInitialize() {


        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            LiteralCommandNode<ServerCommandSource> modLoadNode = CommandManager
                    .literal("mods")
                    .executes(modsLoaded::conf)
                    .build();
            dispatcher.getRoot().addChild(modLoadNode);
        });

        if (modsLoaded.isModLoaded("modern_industrialization") || modsLoaded.isModLoaded("techreborn") || modsLoaded.isModLoaded("indrev")) {
            mLoaded = true;
            compRecipes.createRecipes();
        }
    }

}


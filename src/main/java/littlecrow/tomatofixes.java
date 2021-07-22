package littlecrow;

import littlecrow.block.registryBlock;
import littlecrow.block.trashbin.TrashBin;
import littlecrow.block.trashbin.TrashScreenHandler;
import littlecrow.commands.CommandsInit;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;


public class tomatofixes implements ModInitializer {

    public static final String MOD_ID = "tomatofixes";
    public static Boolean mLoaded = false;

    static {
        registryBlock.TRASH_SCREEN = ScreenHandlerRegistry.registerSimple(registryBlock.TRASH_ID, TrashScreenHandler::new);
    }

    @Override
    public void onInitialize() {
        new CommandsInit();
        registryBlock.register();
    }

}


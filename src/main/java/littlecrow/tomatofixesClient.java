package littlecrow;

import littlecrow.block.registryBlock;
import littlecrow.block.trashbin.BoxScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class tomatofixesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(registryBlock.TRASH_SCREEN, BoxScreen::new);

    }
}

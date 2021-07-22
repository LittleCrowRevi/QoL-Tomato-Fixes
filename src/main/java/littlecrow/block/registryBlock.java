package littlecrow.block;

import littlecrow.block.trashbin.TrashBin;
import littlecrow.block.trashbin.TrashBinEntity;
import littlecrow.block.trashbin.TrashScreenHandler;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class registryBlock {

    public static Block TRASH_BLOCK;
    public static BlockItem TRASH_BLOCK_ITEM;
    public static final Identifier TRASH_ID = new Identifier("tomatofixes:trash_bin");
    public static BlockEntityType<TrashBinEntity> TRASH_BIN_ENTITY;
    public static ScreenHandlerType<TrashScreenHandler> TRASH_SCREEN;

    public static void register() {

        TRASH_BIN_ENTITY = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                TRASH_ID,
                BlockEntityType.Builder.create(TrashBinEntity::new, TrashBin.TRASH_BIN).build(null));
        TRASH_BLOCK = Registry.register(
                Registry.BLOCK,
                TRASH_ID,
                new TrashBin(FabricBlockSettings.copy(Blocks.CHEST)));
        TRASH_BLOCK_ITEM = Registry.register(
                Registry.ITEM,
                TRASH_ID,
                new BlockItem(TRASH_BLOCK, new Item.Settings().group(ItemGroup.MISC)));


    }
}

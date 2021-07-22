package littlecrow.block.trashbin;

import littlecrow.impl.ImplInventory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;

import static littlecrow.block.registryBlock.TRASH_BIN_ENTITY;

public class TrashBinEntity extends BlockEntity  implements ImplInventory, NamedScreenHandlerFactory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(27, ItemStack.EMPTY);

    public TrashBinEntity() {
        super(TRASH_BIN_ENTITY);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;

    }

    @Override
    public void fromTag(BlockState state, NbtCompound tag) {
        super.fromTag(state, tag);
        Inventories.readNbt(tag, this.inventory);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
       nbt = super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);
       return nbt;
    }


    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new TrashScreenHandler(syncId, inv, this);
    }

}

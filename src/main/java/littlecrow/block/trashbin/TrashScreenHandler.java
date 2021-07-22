package littlecrow.block.trashbin;

import littlecrow.block.registryBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class TrashScreenHandler extends ScreenHandler {

    private final Inventory inventory;


    public TrashScreenHandler(int syncId, PlayerInventory inventory) {
        this(syncId, inventory, new SimpleInventory(27));
    }

    public TrashScreenHandler(int syncID, PlayerInventory playerInventory, Inventory inventory) {
        super(registryBlock.TRASH_SCREEN, syncID);
        checkSize(inventory, 9);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);


        //This will place the slot in the correct locations for a 3x3 Grid. The slots exist on both server and client!
        //This will not render the background of the slots however, this is the Screens job
        int h;
        int l;
        //Our inventory
        for (h = 0; h < 3; ++h) {
            for (l = 0; l < 9; ++l) {
                addSlot(new Slot(
                        this.inventory,
                        h * 9 + l,
                        8 + l * 18,
                        18 + h * 18
                        ));
            }
        }

        //The player inventory
        for (h = 0; h < 3; ++h) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + h * 9 + 9, 8 + l * 18, 84 + h * 18));
            }
        }
        //The player Hotbar
        for (h = 0; h < 9; ++h) {
            this.addSlot(new Slot(playerInventory, h, 8 + h * 18, 142));
        }

    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null) {
            ItemStack original = slot.getStack();
            newStack = original.copy();
            if (index < this.inventory.size()) {
                if (!this.insertItem(original, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(original, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }
            if (original.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }
}

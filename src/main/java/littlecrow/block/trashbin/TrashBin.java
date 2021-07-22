package littlecrow.block.trashbin;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.MessageType;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class TrashBin extends BlockWithEntity implements InventoryProvider{

    public static final BooleanProperty POWERED = BooleanProperty.of("powered");

    public TrashBin(Settings settings) {
        super(Settings.of(Material.WOOD).nonOpaque());
        setDefaultState(getStateManager().getDefaultState().with(POWERED, false));
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {return ActionResult.SUCCESS;}
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof TrashBinEntity) {
            if (player.isSneaking()) {
                this.getInventory(state, world, pos);
                player.sendMessage(new LiteralText("Bin Cleared!").formatted(Formatting.BLUE), false);
                return ActionResult.SUCCESS;
            }
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }


    @SuppressWarnings("deprecation")
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
        if (!world.isClient) {
            if (block.is(Registry.BLOCK.get(new Identifier("minecraft:redstone_torch")))) {
                BlockEntity be = world.getBlockEntity(pos);
                int i;
                for (i = 0; i < ((TrashBinEntity) be).size(); ++i) {
                    ((TrashBinEntity) be).removeStack(i);
                }
            }
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new TrashBinEntity();
    }

    public static boolean clearInv(World world, BlockPos pos) {
        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof TrashBinEntity) {
            int i;
            for (i = 0; i < ((TrashBinEntity) be).getItems().size(); ++i) {
                ((TrashBinEntity) be).getItems().set(i, ItemStack.EMPTY);
            }
            return true;
        }
        return false;
    }

    @Override
    public SidedInventory getInventory(BlockState state, WorldAccess world, BlockPos pos) {
        return null;
    }
}

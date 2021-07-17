package littlecrow.commands.Interfaces;

import dev.onyxstudios.cca.api.v3.component.Component;
import net.minecraft.nbt.NbtCompound;
public interface ComponentInt extends Component {

    @Override
    default void readFromNbt(NbtCompound tag) {

    }

    @Override
    default void writeToNbt(NbtCompound tag) {

    }
}


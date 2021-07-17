package littlecrow.mixin;

import com.google.gson.JsonElement;
import littlecrow.tomatofixes;
import littlecrow.dynamic.compRecipes;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

    @Inject(method = "apply", at = @At("HEAD"))
    public void interceptApply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
        if (tomatofixes.mLoaded) {
            map.put((new Identifier("techreborn:copper_ore")), compRecipes.COPPER_ORE);
            map.put((new Identifier("techreborn:tin_ore")), compRecipes.TIN_ORE);
            map.put((new Identifier("techreborn:silver_ore")), compRecipes.SILVER_ORE);
            map.put((new Identifier("techreborn:lead_ore")), compRecipes.LEAD_ORE);
            map.put((new Identifier("techreborn:sheldonite_ore")), compRecipes.SHELDONITE_ORE);
        }
    }
}

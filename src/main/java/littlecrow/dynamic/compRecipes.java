package littlecrow.dynamic;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;

public class compRecipes {

    public static JsonObject COPPER_ORE;
    public static JsonObject TIN_ORE;
    public static JsonObject LEAD_ORE;
    public static JsonObject SILVER_ORE;
    public static JsonObject SHELDONITE_ORE;


    public static void createRecipes() {

        COPPER_ORE = createJSON.createShapelessRecipeJson(
                Lists.newArrayList(new Identifier("c", "copper_ores")),
                Lists.newArrayList("tag"),
                "techreborn:copper_ore");

        TIN_ORE = createJSON.createShapelessRecipeJson(
                Lists.newArrayList(new Identifier("c", "tin_ores")),
                Lists.newArrayList("tag"),
                "techreborn:tin_ore");
        LEAD_ORE = createJSON.createShapelessRecipeJson(
                Lists.newArrayList(new Identifier("c", "lead_ores")),
                Lists.newArrayList("tag"),
                "techreborn:lead_ore");
        SILVER_ORE = createJSON.createShapelessRecipeJson(
                Lists.newArrayList(new Identifier("c", "silver_ores")),
                Lists.newArrayList("tag"),
                "techreborn:silver_ore");
        SHELDONITE_ORE = createJSON.createShapelessRecipeJson(
                Lists.newArrayList(new Identifier("modern_industrialization", "platinum_ore")),
                Lists.newArrayList("item"),
                "techreborn:sheldonite_ore");
    }
}

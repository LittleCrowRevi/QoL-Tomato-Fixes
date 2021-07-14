package littlecrow.dynamic;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class createJSON {

    public static JsonObject createShapelessRecipeJson(ArrayList<Identifier> items, ArrayList<String> type, String output) {
        // Json to store the recipe later
        JsonObject recipe = new JsonObject();
        // the type of shape of recipe
        recipe.addProperty("type", "minecraft:crafting_shapeless");

        JsonObject key;
        JsonArray keyList = new JsonArray();

        for (int i = 0; i < items.size(); ++i) {
            key = new JsonObject();
            key.addProperty(type.get(i), items.get(i).toString());
            keyList.add(key);
        }

        recipe.add("ingredients", keyList);
        JsonObject finalResult = new JsonObject();
        finalResult.addProperty("item", output);
        finalResult.addProperty("count", 1);
        recipe.add("result", finalResult);
        return recipe;
    }
}

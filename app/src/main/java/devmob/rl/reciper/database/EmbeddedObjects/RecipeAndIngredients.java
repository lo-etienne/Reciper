package devmob.rl.reciper.database.EmbeddedObjects;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import devmob.rl.reciper.model.Ingredient;
import devmob.rl.reciper.model.Recipe;

public class RecipeAndIngredients {



    @Embedded
    public Recipe recipe;
    @Relation(
            parentColumn = "recipeId",
            entityColumn = "recipeContainerId"
    )
    private List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
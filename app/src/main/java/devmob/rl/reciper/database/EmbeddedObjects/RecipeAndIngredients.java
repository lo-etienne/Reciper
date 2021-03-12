package devmob.rl.reciper.database.EmbeddedObjects;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;
import java.util.logging.Logger;

import devmob.rl.reciper.model.Ingredient;
import devmob.rl.reciper.model.Recipe;
import devmob.rl.reciper.model.Step;

public class RecipeAndIngredients {



    @Embedded
    public Recipe recipe;
    @Relation(
            parentColumn = "id",
            entityColumn = "recipeContainerId"
    )
    private List<Ingredient> ingredients;

    public List<Ingredient> getIngredients() {
        System.out.println("steps called");
        System.out.println(this.ingredients.size());
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}

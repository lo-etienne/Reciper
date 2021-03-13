package devmob.rl.reciper.database.repository;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.UUID;

import devmob.rl.reciper.database.EmbeddedObjects.RecipeAndIngredients;
import devmob.rl.reciper.database.EmbeddedObjects.RecipeAndSteps;
import devmob.rl.reciper.model.Ingredient;
import devmob.rl.reciper.model.Recipe;
import devmob.rl.reciper.model.Step;

public interface IRepository {
    void insertRecipe(final Recipe recipe);
    void insertStep(final Step step);
    void insertIngredient(final Ingredient ingredient);
    void updateRecipe(final Recipe recipe);
    void updateElementForRecipe(final UUID uuid, final List<Ingredient> listIngredient, final List<Step> listStep);
    LiveData<List<Recipe>> getRecipes();
    LiveData<Recipe> getRecipe(final UUID uuid);
    LiveData<RecipeAndSteps> getStepsByRecipeId(final UUID uuid);
    LiveData<RecipeAndIngredients> getIngredientsByRecipeId(final UUID uuid);
}

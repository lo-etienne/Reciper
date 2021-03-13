package devmob.rl.reciper.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;
import java.util.UUID;

import devmob.rl.reciper.database.EmbeddedObjects.RecipeAndIngredients;
import devmob.rl.reciper.database.EmbeddedObjects.RecipeAndSteps;
import devmob.rl.reciper.model.Ingredient;
import devmob.rl.reciper.model.Recipe;
import devmob.rl.reciper.model.Step;

@Dao
public abstract class RecipeDao {


    // SELECT

    @Query("SELECT * FROM recipe ORDER BY isFavorite DESC")
    public abstract LiveData<List<Recipe>> getRecipes();

    @Query("SELECT * FROM recipe WHERE recipeId = (:uuid)")
    public abstract LiveData<Recipe> getRecipe(final UUID uuid);

    @Transaction
    @Query("SELECT * FROM recipe WHERE recipeId = (:uuid)")
    public abstract LiveData<RecipeAndSteps> getStepsByRecipeId(final UUID uuid);

    @Transaction
    @Query("SELECT * FROM recipe WHERE recipeId = (:uuid)")
    public abstract LiveData<RecipeAndIngredients> getIngredientsByRecipeId(final UUID uuid);

    @Query("DELETE FROM ingredient WHERE recipeContainerId = (:recipeId)")
    public abstract void deleteIngredientByRecipeId(final UUID recipeId);

    @Query("DELETE FROM step WHERE recipeContainerId = (:recipeId)")
    public abstract void deleteStepByRecipeId(final UUID recipeId);

    @Query("UPDATE recipe SET isFavorite = :isFavorite WHERE recipeId = (:uuid)")
    public abstract void updateRecipeFavoriteStatut(final boolean isFavorite, final UUID uuid);

    @Query("DELETE FROM step WHERE recipeContainerId = (:uuid)")
    public abstract void deleteStepsByRecipeId(final UUID uuid);

    @Query("DELETE FROM ingredient WHERE recipeContainerId = (:uuid)")
    public abstract void deleteIngredientsByRecipeId(final UUID uuid);

    @Query("DELETE FROM recipe WHERE recipeId = (:uuid)")
    public abstract void deleteRecipeById(final UUID uuid);

    // INSERT

    @Insert
    public abstract void insertRecipe(final Recipe recipe);

    @Insert
    public abstract void insertStep(final Step step);

    @Insert
    public abstract void insertIngredient(final Ingredient ingredient);

    // UPDATE

    @Update
    public abstract void updateRecipe(final Recipe recipe);

    // DELETE
    @Delete
    public abstract void deleteRecipe(final Recipe recipe);

    @Delete
    public abstract void deleteStep(final Step step);

    @Delete
    public abstract void deleteIngredient(final Ingredient ingredient);

    @Transaction
    public void updateElementForRecipe(final UUID uuid, final List<Ingredient> listIngredient, final List<Step> listStep){
        deleteIngredientByRecipeId(uuid);
        deleteStepByRecipeId(uuid);
        for (Ingredient ingredient:listIngredient) {
            insertIngredient(ingredient);
        }
        for (Step step:listStep) {
            insertStep(step);
        }
    }



}

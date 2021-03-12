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
public interface RecipeDao {


    // SELECT

    @Query("SELECT * FROM recipe ORDER BY isFavorite DESC")
    LiveData<List<Recipe>> getRecipes();

    @Query("SELECT * FROM recipe WHERE recipeId = (:uuid)")
    LiveData<Recipe> getRecipe(final UUID uuid);

    @Transaction
    @Query("SELECT * FROM recipe WHERE recipeId = (:uuid)")
    LiveData<RecipeAndSteps> getStepsByRecipeId(final UUID uuid);

    @Transaction
    @Query("SELECT * FROM recipe WHERE recipeId = (:uuid)")
    LiveData<RecipeAndIngredients> getIngredientsByRecipeId(final UUID uuid);

    @Query("UPDATE recipe SET isFavorite = :isFavorite WHERE recipeId = (:uuid)")
    void updateRecipeFavoriteStatut(final boolean isFavorite, final UUID uuid);

    @Query("DELETE FROM step WHERE recipeContainerId = (:uuid)")
    void deleteStepsByRecipeId(final UUID uuid);

    @Query("DELETE FROM ingredient WHERE recipeContainerId = (:uuid)")
    void deleteIngredientsByRecipeId(final UUID uuid);

    @Query("DELETE FROM recipe WHERE recipeId = (:uuid)")
    void deleteRecipeById(final UUID uuid);

    // INSERT

    @Insert
    void insertRecipe(final Recipe recipe);

    @Insert
    void insertStep(final Step step);

    @Insert
    void insertIngredient(final Ingredient ingredient);

    // UPDATE

    @Update
    void updateRecipe(final Recipe recipe);

    // DELETE
    @Delete
    void deleteRecipe(final Recipe recipe);

    @Delete
    void deleteStep(final Step step);

    @Delete
    void deleteIngredient(final Ingredient ingredient);




}

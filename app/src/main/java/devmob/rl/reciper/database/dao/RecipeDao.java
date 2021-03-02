package devmob.rl.reciper.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.UUID;

import devmob.rl.reciper.model.Recipe;

@Dao
public interface RecipeDao {


    // SELECT

    @Query("SELECT * FROM recipe")
    LiveData<List<Recipe>> getRecipes();

    @Query("SELECT * FROM recipe WHERE id = (:uuid)")
    LiveData<Recipe> getRecipe(final UUID uuid);

    // INSERT

    @Insert
    void insert(final Recipe recipe);

    // UPDATE

    @Update
    void update(final Recipe recipe);

    // DELETE


}

package devmob.rl.reciper.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import devmob.rl.reciper.database.dao.RecipeDao;
import devmob.rl.reciper.model.Ingredient;
import devmob.rl.reciper.model.Recipe;
import devmob.rl.reciper.model.Step;

@Database(entities = {Recipe.class, Step.class, Ingredient.class}, version = 1, exportSchema = false)
@TypeConverters({ReciperTypeConverters.class})
public abstract class ReciperDatabase extends RoomDatabase {

    private static final String DB_NAME = "reciper_database";
    private static ReciperDatabase instance;

    public abstract RecipeDao recipeDao();

    /**
     * Méthode qui permet d'initialiser la DB
     * @param context l'environnement de l'application
     */
    public static void initDatabase(final Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ReciperDatabase.class, DB_NAME).build();
        }
    }

    /**
     * Méthode qui permet de récupérer l'instance de la DB
     * @return l'instance de ReciperDatabase
     */
    public static ReciperDatabase getInstance() {
        if(instance == null)
            throw new IllegalStateException("Reciper Database must be initialized");
            return instance;
    }

    /**
     * Méthode qui permet de se déconnecter de la DB
     */
    public static void disconnectDatabase() {
        instance = null;
    }

}

package devmob.rl.reciper.database.repository;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import devmob.rl.reciper.database.ReciperDatabase;
import devmob.rl.reciper.database.dao.RecipeDao;
import devmob.rl.reciper.model.Recipe;

public class RecipeRepository {

    public static RecipeRepository instance;

    private final RecipeDao recipeDao = ReciperDatabase.getInstance().recipeDao();
    /* Alors, ici je crée un objet ExecutorService car les INSERT et les UPDATE ne peuvent pas
        utiliser des LiveData, il faut donc un thread pour travailler en asynchrone (newSingleThreadExecutor)
    */
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Constructeur défini en privé pour éviter qu'il soit utilisé malencontreusement
     */
    private RecipeRepository() {};

    /**
     * Méthode qui permet d'obtenir une instance de RecipeRepository
     * @return
     */
    public static RecipeRepository getInstance() {
        if(instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    /**
     * Méthode qui permet d'obtenir les recettes présentes dans la DB
     * @return un objet LiveData
     */
    public LiveData<List<Recipe>> getRecipes() {
        return recipeDao.getRecipes();
    }

    /**
     * Méthode qui permet d'obtenir une recette de la DB grâce à un id donné
     * @param uuid objet UUID qui correspond à l'id de la recette à sortir de la DB
     * @return un objet depuis la DB dont l'id = uuid
     */
    public LiveData<Recipe> getRecipe(final UUID uuid) {
        return recipeDao.getRecipe(uuid);
    }

    /**
     * Méthode qui permet d'effectuer un insert d'une recette dans la DB
     * @param recipe objet Recipe à insérer dans la DB
     */
    public void insertRecipe(final Recipe recipe) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.insert(recipe);
            }
        });
    }

    /**
     * Méthode qui permet de update une recette de la DB
     * @param recipe objet Recipe à update dans la DB
     */
    public void updateRecipe(final Recipe recipe) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.update(recipe);
            }
        });
    }

    public void deleteRecipe(final Recipe recipe) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.delete(recipe);
            }
        });
    }



}

package devmob.rl.reciper.database.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import devmob.rl.reciper.database.EmbeddedObjects.RecipeAndIngredients;
import devmob.rl.reciper.database.EmbeddedObjects.RecipeAndSteps;
import devmob.rl.reciper.database.ReciperDatabase;
import devmob.rl.reciper.database.dao.RecipeDao;
import devmob.rl.reciper.model.Ingredient;
import devmob.rl.reciper.model.Recipe;
import devmob.rl.reciper.model.Step;

public class RecipeRepository implements IRepository{

    public static RecipeRepository instance;

    private final RecipeDao recipeDao = ReciperDatabase.getInstance().recipeDao();
    /* Alors, ici je crée un objet ExecutorService car les INSERT et les UPDATE ne peuvent pas
        utiliser des LiveData, il faut donc un thread pour travailler en asynchrone (newSingleThreadExecutor)
    */
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    /**
     * Constructeur défini en privé pour éviter qu'il soit utilisé malencontreusement
     */
    private RecipeRepository() {
    }

    ;

    /**
     * Méthode qui permet d'obtenir une instance de RecipeRepository
     *
     * @return
     */
    public static RecipeRepository getInstance() {
        if (instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    /**
     * Méthode qui permet d'obtenir les recettes présentes dans la DB
     *
     * @return un objet LiveData
     */
    @Override
    public LiveData<List<Recipe>> getRecipes() {
        return recipeDao.getRecipes();
    }

    /**
     * Méthode qui permet d'obtenir une recette de la DB grâce à un id donné
     *
     * @param uuid objet UUID qui correspond à l'id de la recette à sortir de la DB
     * @return un objet depuis la DB dont l'id = uuid
     */
    @Override
    public LiveData<Recipe> getRecipe(final UUID uuid) {
        return recipeDao.getRecipe(uuid);
    }

    /**
     * Méthode qui permet d'obtenir, depuis la DB, l'ensemble des étapes d'une recette
     * grâce à l'id de cette dernière
     *
     * @param uuid
     * @return
     */
    @Override
    public LiveData<RecipeAndSteps> getStepsByRecipeId(final UUID uuid) {
        return recipeDao.getStepsByRecipeId(uuid);
    }

    /**
     * Méthode qui permet d'obtenir, depuis la DB, l'ensemble des ingrédients d'une recette
     * grâce à l'id de cette dernière
     *
     * @param uuid
     * @return
     */
    @Override
    public LiveData<RecipeAndIngredients> getIngredientsByRecipeId(final UUID uuid) {
        return recipeDao.getIngredientsByRecipeId(uuid);
    }

    /**
     * Méthode qui permet d'effectuer un insert d'une recette dans la DB
     *
     * @param recipe objet Recipe à insérer dans la DB
     */
    @Override
    public void insertRecipe(final Recipe recipe) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.insertRecipe(recipe);
            }
        });
    }

    @Override
    public void insertStep(final Step step) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.insertStep(step);
            }
        });
    }

    @Override
    public void insertIngredient(final Ingredient ingredient) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.insertIngredient(ingredient);
            }
        });
    }

    /**
     * Méthode qui permet de update une recette de la DB
     *
     * @param recipe objet Recipe à update dans la DB
     */
    @Override
    public void updateRecipe(final Recipe recipe) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.updateRecipe(recipe);
            }
        });
    }

    public void deleteRecipe(final Recipe recipe) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.deleteRecipe(recipe);
            }
        });
    }

    public void deleteStep(final Step step) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.deleteStep(step);
            }
        });
    }

    public void deleteIngredient(final Ingredient ingredient) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.deleteIngredient(ingredient);
            }
        });
    }

    public void deleteIngredients(final UUID uuid){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.deleteIngredientByRecipeId(uuid);
            }
        });
    }

    public void deleteSteps(final UUID uuid){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.deleteStepByRecipeId(uuid);
            }
        });
    }

    @Override
    public void updateElementForRecipe(final UUID uuid, final List<Ingredient> listIngredient, final List<Step> listStep){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                recipeDao.updateElementForRecipe(uuid,listIngredient,listStep);
            }
        });
    }
}



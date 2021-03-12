package devmob.rl.reciper.recipelist;

import android.util.Log;

import androidx.lifecycle.Observer;

import java.util.List;
import java.util.UUID;

import devmob.rl.reciper.database.repository.RecipeRepository;
import devmob.rl.reciper.model.Recipe;

public class RecipeListPresenter {

    private List<Recipe> recipeList;

    private final IRecipeListScreen screen;

    /**
     * Constructeur d'un Presenter
     * @param screen
     */
    public RecipeListPresenter(final IRecipeListScreen screen) {
        this.screen = screen;
    }

    /**
     * Méthode qui permet, à l'aide d'un observer, de récupérer les recettes de la DB (oberser nécessaire car getRecipes() renvoie un LiveData d'une liste de Recipe
     */
    public void  loadRecipes() {
        Log.d("RecipeListPresenter", "loadRecipes");
        RecipeRepository.getInstance().getRecipes().observeForever(new Observer<List<Recipe>>() {
            @Override
            public void onChanged(final List<Recipe> recipes) {
                RecipeListPresenter.this.recipeList = recipes;
                screen.loadView();
            }
        });
    }

    /**
     * Méthode qui permet d'ajouter une recette à la DB
     * @return
     */
    /*public UUID addRecipe() {
        Recipe recipe = new Recipe();
        RecipeRepository.getInstance().insertRecipe(recipe);
        return recipe.getRecipeId();
    }*/

    /**
     * Méthode qui permet d'afficher la recette à une position donnée
     * @param holder interface IRecipItemScreen
     * @param position position de la recette dans la liste
     */
    public void showRecipeOn(IRecipeItemScreen holder, final int position) {
        Recipe recipe = recipeList.get(position);
        holder.showRecipe(recipe.getRecipeId(), recipe.getName());
    }

    /**
     * Méthode qui permet d'obtenir le nombre d'objets Recipe dans la liste
     * @return
     */
    public int getItemCount() {
        if(recipeList == null) {
            return 0;
        }
        return recipeList.size();
    }

}

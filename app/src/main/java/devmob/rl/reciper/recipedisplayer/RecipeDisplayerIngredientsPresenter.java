package devmob.rl.reciper.recipedisplayer;

import androidx.lifecycle.Observer;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import devmob.rl.reciper.database.EmbeddedObjects.RecipeAndIngredients;
import devmob.rl.reciper.database.repository.RecipeRepository;
import devmob.rl.reciper.model.Ingredient;

public class RecipeDisplayerIngredientsPresenter {

    private final UUID recipeId;
    private List<Ingredient> ingredientList;

    private final IRecipeDisplayerIngredientsScreen screen;

    public RecipeDisplayerIngredientsPresenter(final UUID recipeId, final IRecipeDisplayerIngredientsScreen screen) {
        this.recipeId = recipeId;
        this.screen = screen;
    }

    public void loadIngredients() {
        RecipeRepository.getInstance().getIngredientsByRecipeId(recipeId).observeForever(new Observer<RecipeAndIngredients>() {
            @Override
            public void onChanged(RecipeAndIngredients recipeAndIngredients) {
                RecipeDisplayerIngredientsPresenter.this.ingredientList = recipeAndIngredients.getIngredients();
                Collections.reverse(RecipeDisplayerIngredientsPresenter.this.ingredientList);
                screen.loadView();
            }
        });
    }

    public void showIngredientOn(IIngredientItemScreen holder, final int position) {
        Ingredient ingredient = ingredientList.get(position);
        holder.showIngredient(ingredient.getName(), ingredient.getQuantity());
    }

    public int getItemCount() {
        if(ingredientList == null) {
            return 0;
        }
        return ingredientList.size();
    }

}

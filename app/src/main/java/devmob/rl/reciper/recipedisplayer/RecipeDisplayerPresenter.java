package devmob.rl.reciper.recipedisplayer;

import androidx.lifecycle.Observer;

import java.util.UUID;

import devmob.rl.reciper.database.repository.RecipeRepository;
import devmob.rl.reciper.model.Recipe;

public class RecipeDisplayerPresenter {

    private final IRecipeDisplayerScreen screen;
    private Recipe recipe;

    public RecipeDisplayerPresenter(final IRecipeDisplayerScreen screen) {
        this.screen = screen;
    }

    public void loadRecipe(final UUID uuid) {
        RecipeRepository.getInstance().getRecipe(uuid).observeForever(new Observer<Recipe>() {
            @Override
            public void onChanged(Recipe recipe) {
                    RecipeDisplayerPresenter.this.recipe = recipe;
                    if(recipe != null) {
                        screen.showRecipe(recipe.getName(), recipe.getDescription(), Integer.toString(recipe.getNumberOfPersons()), "0 min", "Cher");
                    }
            }
        });
    }

    public Recipe getRecipe() {
        return recipe;
    }
}

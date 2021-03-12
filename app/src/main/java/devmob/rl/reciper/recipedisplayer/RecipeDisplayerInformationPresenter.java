package devmob.rl.reciper.recipedisplayer;

import androidx.lifecycle.Observer;

import java.util.UUID;

import devmob.rl.reciper.database.repository.RecipeRepository;
import devmob.rl.reciper.model.Recipe;

public class RecipeDisplayerInformationPresenter {

    private final IRecipeDisplayerInformationScreen screen;
    private Recipe recipe;

    public RecipeDisplayerInformationPresenter(final IRecipeDisplayerInformationScreen screen) {
        this.screen = screen;
    }

    public void loadRecipeInformation(final UUID uuid) {
        RecipeRepository.getInstance().getRecipe(uuid).observeForever(new Observer<Recipe>() {
            @Override
            public void onChanged(Recipe recipe) {
                RecipeDisplayerInformationPresenter.this.recipe = recipe;
                if(recipe != null) {
                    screen.showRecipeInformation(recipe.getName(), recipe.getDescription(), recipe.getTime(), Integer.toString(recipe.getNumberOfPersons()), recipe.getPrice(), recipe.getDifficulty(), Integer.toString(recipe.getNote()), recipe.getComment());
                }
            }
        });
    }

    public Recipe getRecipe() {
        return recipe;
    }
}

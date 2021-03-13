package devmob.rl.reciper.recipedisplayer;

import androidx.lifecycle.ViewModel;

import java.util.UUID;

public class RecipeDisplayerViewModel extends ViewModel {

    private UUID recipeId;

    public void setRecipeId(final UUID recipeId) {
        this.recipeId = recipeId;
    }

    public UUID getRecipeId() {
        return recipeId;
    }
}

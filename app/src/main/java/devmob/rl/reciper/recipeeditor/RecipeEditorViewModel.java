package devmob.rl.reciper.recipeeditor;

import androidx.lifecycle.ViewModel;

import java.util.UUID;

public class RecipeEditorViewModel extends ViewModel {

    private UUID recipeId;

    public void setRecipeId(final UUID recipeId) {
        this.recipeId = recipeId;
    }

    public UUID getRecipeId() {
        return recipeId;
    }
}

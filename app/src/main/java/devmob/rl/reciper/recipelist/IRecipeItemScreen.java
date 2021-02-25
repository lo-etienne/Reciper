package devmob.rl.reciper.recipelist;

import java.util.UUID;

public interface IRecipeItemScreen {
    void showRecipe(final UUID id, final int position, final String name);
}

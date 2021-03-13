package devmob.rl.reciper.recipelist;

import java.util.UUID;

public interface IRecipeItemScreen {
    void showRecipe(final UUID id, final String path, final String name, final int time, final int numberOfPerson);
}

package devmob.rl.reciper.recipedisplayer.recipedisplayerinformation;

public interface IRecipeDisplayerInformationScreen {
    void showRecipeInformation(final String pathImage,
                               final String recipeName,
                               final String recipeDescription,
                               final String recipeTime,
                               final String recipeNumberOfPerson,
                               final String recipePrice,
                               final String recipeDifficulty,
                               final String recipeNote,
                               final String recipeComment);
}

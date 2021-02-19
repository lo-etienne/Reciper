package devmob.rl.reciper.model;

import java.util.List;

public class Recipe {

    private List<Step> preparationSteps;
    private List<Step> cookingSteps;

    private List<Ingredient> ingredientList;

    private String name;
    private String description;
    private String priceLevel;
    private String comment;
    private String illustration;
    private int difficulty;
    private int numberOfPersons;
    private int note;

    public boolean isFavorite;

    public Recipe(final List<Step> preparationSteps, final List<Step> cookingSteps, final List<Ingredient> ingredientList, final String name, final String description, final String priceLevel, final String comment, final String illustration, final int difficulty, final int numberOfPersons, final int note) {

    }




}

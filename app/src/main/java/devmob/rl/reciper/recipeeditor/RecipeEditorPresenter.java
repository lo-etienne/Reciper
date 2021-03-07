package devmob.rl.reciper.recipeeditor;

import java.util.ArrayList;
import java.util.List;

import devmob.rl.reciper.model.Ingredient;
import devmob.rl.reciper.model.Recipe;
import devmob.rl.reciper.model.Step;

public class RecipeEditorPresenter {

    private String name_recipe;
    private String difficulty_recipe;
    private String price_recipe;
    private int nbPeople_recipe;
    private String description_recipe;
    private String commentary_recipe;
    private List<Ingredient> listIngredient_recipe;
    private List<Step> listStep_recipe;

    public void setInfoFragment(String name, String difficulty, String price, int nbPeople, String description, String commentary){
        String name_recipe = name;
        String difficulty_recipe = difficulty;
        String price_recipe = price;
        int nbPeople_recipe = nbPeople;
        String description_recipe = description;
        String commentary_recipe = commentary;
    }

    public void setIngredientList(List<Ingredient> listIngredient){
        if(listIngredient == null){
            listIngredient_recipe = new ArrayList<>();
        }
        listIngredient_recipe = listIngredient;
    }

    public void setStepList(List<Step> listStep){
        if(listStep == null){
            listStep_recipe = new ArrayList<>();
        }
        listStep_recipe = listStep;
    }

    public void createRecipe(){
        Recipe recipe = new Recipe();
        recipe.setName(name_recipe);
        recipe.setDifficulty(difficulty_recipe);
        //ajouter le prix d'une recette
        recipe.setNumberOfPersons(nbPeople_recipe);
        recipe.setDescription(description_recipe);
        recipe.setComment(commentary_recipe);
    }
}

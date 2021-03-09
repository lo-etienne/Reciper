package devmob.rl.reciper.recipeeditor;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import devmob.rl.reciper.R;
import devmob.rl.reciper.database.repository.RecipeRepository;
import devmob.rl.reciper.model.Ingredient;
import devmob.rl.reciper.model.Recipe;
import devmob.rl.reciper.model.Step;

public class RecipeEditorPresenter {

    private final UUID recipeUUID = UUID.randomUUID();
    private String name_recipe;
    private String description_recipe;
    private String difficulty_recipe;
    private String price_recipe;
    private int nbPeople_recipe;
    private int note;
    private String commentary_recipe;
    private List<Ingredient> listIngredient_recipe;
    private List<Step> listStep_recipe;

    public UUID getRecipeUUID() {
        return recipeUUID;
    }

    public void setInfoFragment(final String name, final String difficulty, final String price, final int nbPeople, final String description, final String commentary, final int note){
        name_recipe = name == "" ? name_recipe = "Nom par defaut" : name;
        difficulty_recipe = difficulty;
        price_recipe = price;
        nbPeople_recipe = nbPeople;
        description_recipe = description == "" ? description_recipe = "Description par defaut" : description;
        commentary_recipe = commentary == "" ? commentary_recipe = "Commentaire par defaut" : commentary;
        this.note = note;
    }

    public void setIngredientList(final List<Ingredient> listIngredient){
        if(listIngredient == null){
            listIngredient_recipe = new ArrayList<>();
        }
        listIngredient_recipe = listIngredient;
    }

    public void setStepList(final List<Step> listStep){
        if(listStep == null){
            listStep_recipe = new ArrayList<>();
        }
        listStep_recipe = listStep;
    }

    public void createRecipe(){
        Recipe recipe = new Recipe(name_recipe, description_recipe, difficulty_recipe, price_recipe, nbPeople_recipe, note, commentary_recipe, listIngredient_recipe, listStep_recipe, getDuration());
        RecipeRepository.getInstance().insertRecipe(recipe);
        Log.d("1","passage createRecipe");
    }

    private int getDuration() {
        int duration = 0;
        if(listStep_recipe == null){
        }else{
            for (Step step:listStep_recipe) {
                duration += step.getDuration();
            }
        }
        return duration;
    }
}

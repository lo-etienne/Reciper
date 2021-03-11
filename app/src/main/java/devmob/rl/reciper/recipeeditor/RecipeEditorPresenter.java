package devmob.rl.reciper.recipeeditor;

import android.util.Log;

import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import devmob.rl.reciper.database.EmbeddedObjects.RecipeAndIngredients;
import devmob.rl.reciper.database.EmbeddedObjects.RecipeAndSteps;
import devmob.rl.reciper.database.repository.RecipeRepository;
import devmob.rl.reciper.model.Ingredient;
import devmob.rl.reciper.model.Recipe;
import devmob.rl.reciper.model.Step;

public class RecipeEditorPresenter {

    private final UUID recipeUUID;
    private String name_recipe;
    private String description_recipe;
    private String difficulty_recipe;
    private String price_recipe;
    private int nbPeople_recipe;
    private int note;
    private String commentary_recipe;
    private List<Ingredient> listIngredient_recipe = new ArrayList<>();
    private List<Step> listStep_recipe = new ArrayList<>();

    public RecipeEditorPresenter(){
        this.recipeUUID = UUID.randomUUID();
    }

    public RecipeEditorPresenter(UUID uuid){
        this.recipeUUID = uuid;

        RecipeRepository.getInstance().getStepsByRecipeId(uuid).observeForever(new Observer<RecipeAndSteps>() {
            @Override
            public void onChanged(RecipeAndSteps recipeAndSteps) {
                RecipeEditorPresenter.this.listStep_recipe = recipeAndSteps.getSteps();
            }
        });

        RecipeRepository.getInstance().getIngredientByRecipeId(uuid).observeForever(new Observer<RecipeAndIngredients>() {
            @Override
            public void onChanged(RecipeAndIngredients recipeAndIngredients) {
                RecipeEditorPresenter.this.listIngredient_recipe = recipeAndIngredients.getIngredients();
            }
        });

        RecipeRepository.getInstance().getRecipe(uuid).observeForever(new Observer<Recipe>() {
            @Override
            public void onChanged(Recipe recipe) {
                RecipeEditorPresenter.this.name_recipe = recipe.getName();
                RecipeEditorPresenter.this.description_recipe = recipe.getDescription();
                RecipeEditorPresenter.this.difficulty_recipe = recipe.getDifficulty();
                RecipeEditorPresenter.this.price_recipe = recipe.getPrice();
                RecipeEditorPresenter.this.nbPeople_recipe = recipe.getNumberOfPersons();
                RecipeEditorPresenter.this.note = recipe.getNote();
                RecipeEditorPresenter.this.commentary_recipe = recipe.getComment();
            }
        });
    }

    //getteur
    public UUID getRecipeUUID() {
        return recipeUUID;
    }
    public String getName_recipe() { return name_recipe; }
    public String getDescription_recipe() { return description_recipe; }
    public String getDifficulty_recipe() { return difficulty_recipe; }
    public String getPrice_recipe() { return price_recipe; }
    public int getNbPeople_recipe() { return nbPeople_recipe; }
    public int getNote() { return note; }
    public String getCommentary_recipe() { return commentary_recipe; }
    public List<Step> getListStep(){ return this.listStep_recipe; }
    public List<Ingredient> getListIngredient(){ return this.listIngredient_recipe; }

    public void setInfoFragment(final String name, final String difficulty, final String price, final int nbPeople, final String description, final String commentary, final int note){
        name_recipe = name.equals("") ? name_recipe = "Nom par defaut" : name;
        difficulty_recipe = difficulty;
        price_recipe = price;
        nbPeople_recipe = nbPeople;
        description_recipe = description.equals("") ? description_recipe = "Description par defaut" : description;
        commentary_recipe = commentary.equals("") ? commentary_recipe = "Commentaire par defaut" : commentary;
        this.note = note;
    }

    public void setIngredientList(final List<Ingredient> listIngredient){ listIngredient_recipe = listIngredient; }
    public void setStepList(final List<Step> listStep){
        listStep_recipe = listStep;
    }

    public void createRecipe(){
        Recipe recipe = new Recipe(name_recipe, description_recipe, difficulty_recipe, price_recipe, nbPeople_recipe, note, commentary_recipe, getDuration());
        RecipeRepository.getInstance().insertRecipe(recipe);
        for (Ingredient ingredient:listIngredient_recipe) {
            RecipeRepository.getInstance().insertIngredient(ingredient);
        }
        for (Step step:listStep_recipe) {
            RecipeRepository.getInstance().insertStep(step);
        }
        Log.d("1","passage createRecipe");
    }
    public void updateData(){
        Recipe recipe = new Recipe(name_recipe, description_recipe, difficulty_recipe, price_recipe, nbPeople_recipe, note, commentary_recipe, getDuration());
        RecipeRepository.getInstance().updateRecipe(recipe);
        //RecipeRepository.getInstance().updateListIngredient(listIngredient_recipe);
        //RecipeRepository.getInstance().updateListStep(listStep_recipe);
    }

    private int getDuration() {
        int duration = 0;
        if(listStep_recipe == null){
            duration = -1;
        }else{
            for (Step step:listStep_recipe) {
                duration += step.getDuration();
            }
        }
        return duration;
    }
}

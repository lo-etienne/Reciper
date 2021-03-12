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
import devmob.rl.reciper.recipeeditor.IScreen.IScreenInfo;
import devmob.rl.reciper.recipeeditor.IScreen.IScreenIngredient;
import devmob.rl.reciper.recipeeditor.IScreen.IScreenStep;

public class RecipeEditorPresenter {

    private final UUID recipeUUID;
    private String name_recipe;
    private String description_recipe;
    private String difficulty_recipe;
    private String price_recipe;
    private int nbPeople_recipe;
    private int note;
    private String commentary_recipe;
    private String image;

    private List<Ingredient> listIngredient_recipe;
    private List<Step> listStep_recipe;

    private IScreenInfo screenInfo;
    private IScreenIngredient screenIngredient;
    private IScreenStep screenStep;

    public RecipeEditorPresenter(){
        this.recipeUUID = UUID.randomUUID();
        listIngredient_recipe = new ArrayList<>();
        listStep_recipe = new ArrayList<>();
    }

    public RecipeEditorPresenter(UUID uuid){
        this.recipeUUID = uuid;
        listIngredient_recipe = new ArrayList<>();
        listStep_recipe = new ArrayList<>();
        Log.d("RecipeEditorPresenter","passage interne");
    }

    public void setScreenInfo(IScreenInfo screenInfo) { this.screenInfo = screenInfo; }
    public void setScreenIngredient(IScreenIngredient screenIngredient) { this.screenIngredient = screenIngredient; }
    public void setScreenStep(IScreenStep screenStep) { this.screenStep = screenStep; }

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
    public String getImage() { return image; }
    public List<Step> getListStep(){ return this.listStep_recipe; }
    public List<Ingredient> getListIngredient(){ return this.listIngredient_recipe; }

    public void setInfoFragment(final String name, final String difficulty, final String price, final int nbPeople, final String description, final String commentary, final int note, final String image){
        name_recipe = name.equals("") ? name_recipe = "Nom par defaut" : name;
        difficulty_recipe = difficulty;
        price_recipe = price;
        nbPeople_recipe = nbPeople;
        description_recipe = description.equals("") ? description_recipe = "Description par defaut" : description;
        commentary_recipe = commentary.equals("") ? commentary_recipe = "Commentaire par defaut" : commentary;
        this.note = note;
        this.image = image;
    }

    public void setIngredientList(final List<Ingredient> listIngredient){ listIngredient_recipe = listIngredient; }
    public void setStepList(final List<Step> listStep){
        listStep_recipe = listStep;
    }

    public void createRecipe(){
        Recipe recipe = new Recipe(recipeUUID, name_recipe, description_recipe, difficulty_recipe, price_recipe, nbPeople_recipe, note, commentary_recipe, getDuration(),image);
        RecipeRepository.getInstance().insertRecipe(recipe);
        insertListDB();
        Log.d("1","passage createRecipe");
    }

    public void updateData(){
        Recipe recipe = new Recipe(recipeUUID, name_recipe, description_recipe, difficulty_recipe, price_recipe, nbPeople_recipe, note, commentary_recipe, getDuration(),image);
        RecipeRepository.getInstance().updateRecipe(recipe);
        RecipeRepository.getInstance().deleteIngredients(getRecipeUUID());
        RecipeRepository.getInstance().deleteSteps(getRecipeUUID());
        insertListDB();
        Log.d("1","passage updateData");
    }

    private void insertListDB(){
        for (Ingredient ingredient : listIngredient_recipe) {
            RecipeRepository.getInstance().insertIngredient(ingredient);
        }
        for (Step step : listStep_recipe) {
            RecipeRepository.getInstance().insertStep(step);
        }
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

    public void setDataInfo(UUID uuid){
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
                RecipeEditorPresenter.this.image = recipe.getIllustrationUrl();
                screenInfo.update();
                Log.d("setData", "Info " + name_recipe);
            }
        });
    }

    public void setDataIngredient(UUID uuid){
        RecipeRepository.getInstance().getIngredientsByRecipeId(uuid).observeForever(new Observer<RecipeAndIngredients>() {
            @Override
            public void onChanged(RecipeAndIngredients recipeAndIngredients) {
                RecipeEditorPresenter.this.listIngredient_recipe.clear();
                RecipeEditorPresenter.this.listIngredient_recipe.addAll(recipeAndIngredients.getIngredients());
                screenIngredient.update();
                Log.d("setData", "Ingredient " + listIngredient_recipe.size());
            }
        });
    }

    public void setDataStep(UUID uuid){
        RecipeRepository.getInstance().getStepsByRecipeId(uuid).observeForever(new Observer<RecipeAndSteps>() {
            @Override
            public void onChanged(RecipeAndSteps recipeAndSteps) {
                RecipeEditorPresenter.this.listStep_recipe.clear();
                RecipeEditorPresenter.this.listStep_recipe.addAll(recipeAndSteps.getSteps());
                screenStep.update();
                Log.d("setData", "Step " + listStep_recipe.size());
            }
        });
    }
}

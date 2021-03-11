package devmob.rl.reciper.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import devmob.rl.reciper.database.repository.RecipeRepository;

@Entity
public class Recipe {

    @PrimaryKey
    @NonNull
    private UUID recipeId;

    private String name;
    private String description;
    private String time;
    private String price;
    private String difficulty;
    private int numberOfPersons;
    private int note;
    private String comment;

    private List<Ingredient> ingredientList;
    private List<Step> stepList;

    private String illustrationUrl;

    private boolean isFavorite;




    public Recipe() {
        recipeId = UUID.randomUUID();
        name = "Nom de la recette";
        description = "Ceci est une description";
        difficulty = "Difficile";
        time = "10h";
        numberOfPersons = 10;
        note = 2;
        price = "Cher";
        comment = "Ceci est un commentaire";
        ingredientList = new ArrayList<>();
        stepList = new ArrayList<>();
        illustrationUrl = "url/directory1/directory2.png";
        isFavorite = false;

    }

    @NonNull
    public UUID getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(@NonNull UUID recipeId) {
        this.recipeId = recipeId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(final String difficulty) {
        this.difficulty = difficulty;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(final int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public int getNote() {
        return note;
    }

    public void setNote(final int note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(final List<Ingredient> ingredientList) {
        this.ingredientList.clear();
        this.ingredientList.addAll(ingredientList);
    }

    public List<Step> getStepList() {
        return stepList;
    }

    public void setStepList(final List<Step> stepList) {
        this.stepList.clear();
        this.stepList.addAll(stepList);
    }

    public String getIllustrationUrl() {
        return illustrationUrl;
    }

    public void setIllustrationUrl(final String illustrationUrl) {
        this.illustrationUrl = illustrationUrl;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(final boolean favorite) {
        isFavorite = favorite;
    }
}

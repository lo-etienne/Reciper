package devmob.rl.reciper.database.EmbeddedObjects;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import devmob.rl.reciper.model.Recipe;
import devmob.rl.reciper.model.Step;

public class RecipeAndSteps {



    @Embedded
    public Recipe recipe;
    @Relation(
            parentColumn = "recipeId",
            entityColumn = "recipeContainerId"
    )
    private List<Step> steps;

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}

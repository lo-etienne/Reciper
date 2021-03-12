package devmob.rl.reciper.database.EmbeddedObjects;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;
import java.util.logging.Logger;

import devmob.rl.reciper.model.Recipe;
import devmob.rl.reciper.model.Step;

public class RecipeAndSteps {



    @Embedded
    public Recipe recipe;
    @Relation(
            parentColumn = "id",
            entityColumn = "recipeContainerId"
    )
    private List<Step> steps;

    public List<Step> getSteps() {
        System.out.println("steps called");
        System.out.println(this.steps.size());
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}

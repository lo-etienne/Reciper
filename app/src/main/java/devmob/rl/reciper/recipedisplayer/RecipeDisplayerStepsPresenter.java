package devmob.rl.reciper.recipedisplayer;

import android.util.Log;

import androidx.lifecycle.Observer;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import devmob.rl.reciper.database.EmbeddedObjects.RecipeAndSteps;
import devmob.rl.reciper.database.repository.RecipeRepository;
import devmob.rl.reciper.model.Step;

public class RecipeDisplayerStepsPresenter {

    private final UUID recipeId;
    private List<Step> stepList;

    private final IRecipeDisplayerStepsScreen screen;

    public RecipeDisplayerStepsPresenter(final UUID recipeId, final IRecipeDisplayerStepsScreen screen) {
        this.screen = screen;
        this.recipeId = recipeId;
    }

    public void loadSteps() {
        Log.d("StepsPresenter", "LOADING STEPS");
        RecipeRepository.getInstance().getStepsByRecipeId(recipeId).observeForever(new Observer<RecipeAndSteps>() {
            @Override
            public void onChanged(RecipeAndSteps recipeAndSteps) {
                RecipeDisplayerStepsPresenter.this.stepList = recipeAndSteps.getSteps();
                Collections.reverse(RecipeDisplayerStepsPresenter.this.stepList);
                screen.loadView();
            }
        });
    }

    public void showStepOn(IStepItemScreen holder, final int postion) {
        Step step = stepList.get(postion);
        holder.showStep(postion + 1, step.getDescription());
    }

    public int getItemCount() {
        if(stepList == null) {
            return 0;
        }
        return stepList.size();
    }

}

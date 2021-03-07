package devmob.rl.reciper.recipeeditor.editorfragments.step;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

import devmob.rl.reciper.model.Step;
import devmob.rl.reciper.recipeeditor.editorfragments.Ingredient.IIngredientList;

public class StepPresenter {

    private List<Step> list;
    private IStepList screen;

    public StepPresenter(IStepList screen){
        this.screen = screen;
        this.list = new ArrayList<>();
    }

    public void loadStep() {
        Log.d("IngredientListPresenter", "loadIngredient");
        LiveData<List<Step>> ingredient = new LiveData<List<Step>>() {};
        ingredient.observeForever(new Observer<List<Step>>() {
            @Override
            public void onChanged(List<Step> ingredients) {
                StepPresenter.this.list = ingredients;
            }
        });
    }

    public void addStep(String description){
        list.add(new Step(description));
        screen.loadView();
    }

    public void showStepOn(IStepItemScreen holder, final int position) {
        Step step = list.get(position);
        holder.showStep(step);
    }

    public int getItemCount() {
        if(list == null) {
            return 0;
        }
        return list.size();
    }
}

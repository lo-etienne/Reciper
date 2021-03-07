package devmob.rl.reciper.recipeeditor.editorfragments.Ingredient;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

import devmob.rl.reciper.model.Ingredient;


public class IngredientPresenter {

    private List<Ingredient> list;
    private IIngredientList screen;

    public IngredientPresenter(){
        this.list = new ArrayList<>();
    }

    public void setScreen(IIngredientList screen) {
        this.screen = screen;
    }

    public void loadIngredient() {
        Log.d("IngredientListPresenter", "loadIngredient");
        LiveData<List<Ingredient>> ingredient = new LiveData<List<Ingredient>>() {};
        ingredient.observeForever(new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> ingredients) {
                IngredientPresenter.this.list = ingredients;
            }
        });
    }

        public void addIngredient(String name, String quantity){
        list.add(new Ingredient(name,quantity));
        screen.loadView();
    }

    public void showIngredientOn(IIngredientItemScreen holder, final int position) {
        Ingredient ingredient = list.get(position);
        holder.showIngredient(ingredient);
    }

    public int getItemCount() {
        if(list == null) {
            return 0;
        }
        return list.size();
    }
}

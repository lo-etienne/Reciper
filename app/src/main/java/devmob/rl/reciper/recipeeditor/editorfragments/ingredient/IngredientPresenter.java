package devmob.rl.reciper.recipeeditor.editorfragments.ingredient;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import devmob.rl.reciper.model.Ingredient;
import devmob.rl.reciper.recipeeditor.editorfragments.IPublisher;
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;


public class IngredientPresenter implements IPublisher {

    private List<Ingredient> list;
    private IIngredientList screen;
    private RecipeEditorPresenter presenter;

    public IngredientPresenter(IIngredientList screen, RecipeEditorPresenter presenter){
        this.list = new ArrayList<>();
        this.screen = screen;
        this.presenter = presenter;
    }

    public void loadIngredient() {
        Log.d("IngredientListPresenter", "loadIngredient");
        LiveData<List<Ingredient>> ingredient = new LiveData<List<Ingredient>>(presenter.getListIngredient()) {};
        ingredient.observeForever(new Observer<List<Ingredient>>() {
            @Override
            public void onChanged(List<Ingredient> ingredients) {
                IngredientPresenter.this.list = ingredients;
            }
        });
        screen.loadView();
    }

    public void addIngredient(String name, String quantity){
        list.add(new Ingredient(presenter.getRecipeUUID(),name,quantity));
        Log.d("IngredientPresenter", "addIngredient");
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

    @Override
    public void publish() {
        presenter.setIngredientList(list);
    }

    public void suppIngredient(UUID uuid){
        Ingredient i = null;
        for (Ingredient ingredient:list) {
            if(ingredient.getUuid() == uuid) {
                i = ingredient;
            }
        }
        if(!(i == null))list.remove(i);
        screen.loadView();
    }
}

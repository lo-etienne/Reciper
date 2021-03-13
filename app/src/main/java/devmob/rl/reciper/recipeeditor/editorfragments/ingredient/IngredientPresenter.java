package devmob.rl.reciper.recipeeditor.editorfragments.ingredient;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import devmob.rl.reciper.model.Ingredient;
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;
import devmob.rl.reciper.recipeeditor.editorfragments.IPublisher;


public class IngredientPresenter implements IPublisher {

    private List<Ingredient> list;
    private IIngredientList screen;
    private RecipeEditorPresenter presenter;

    public IngredientPresenter(IIngredientList screen, RecipeEditorPresenter presenter){
        this.list = new ArrayList<>();
        this.screen = screen;
        this.presenter = presenter;
    }

    /**
     * permet d'observer la list de l'editor presenter
     */
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

    /**
     * permet de creer et d'ajouter un ingredient dans la list
     * @param name le nom de l'ingredient
     * @param quantity la quantite de l'ingredient
     */
    public void addIngredient(String name, String quantity){
        list.add(new Ingredient(presenter.getRecipeUUID(),name,quantity));
        Log.d("IngredientPresenter", "addIngredient");
        screen.loadView();
    }

    /**
     * permet d'afficher un element de la list sur l'ecran
     * @param holder le screen qui va afficher l'element
     * @param position la position dans la list de l'element
     */
    public void showIngredientOn(IIngredientItemScreen holder, final int position) {
        Ingredient ingredient = list.get(position);
        holder.showIngredient(ingredient);
    }

    /**
     * avoir la taille de la list ou une valeur de 0 si elle n'est pas initialiser
     * @return
     */
    public int getItemCount() {
        if(list == null) {
            return 0;
        }
        return list.size();
    }

    /**
     * permet de publier au presenter la list
     */
    @Override
    public void publish() {
        presenter.setIngredientList(list);
    }

    /**
     * suppremer un element de la list
     * @param uuid element a supprimer
     */
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

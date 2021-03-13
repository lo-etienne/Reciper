package devmob.rl.reciper.recipeeditor.editorfragments.step;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import devmob.rl.reciper.model.Ingredient;
import devmob.rl.reciper.model.Step;
import devmob.rl.reciper.recipeeditor.editorfragments.IPublisher;
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;

public class StepPresenter implements IPublisher {

    private List<Step> list;
    private IStepList screen;
    private RecipeEditorPresenter presenter;

    public StepPresenter(IStepList screen, RecipeEditorPresenter presenter){
        this.screen = screen;
        this.list = new ArrayList<>();
        this.presenter = presenter;
    }

    /**
     * permet d'observer la list du presenteur
     */
    public void loadStep() {
        Log.d("IngredientListPresenter", "loadIngredient");
        LiveData<List<Step>> ingredient = new LiveData<List<Step>>(presenter.getListStep()) {};
        ingredient.observeForever(new Observer<List<Step>>() {
            @Override
            public void onChanged(List<Step> ingredients) {
                StepPresenter.this.list = ingredients;
            }
        });
        //Collections.sort(list);
        screen.loadView();
    }

    /**
     * permet d'ajouter une etape a la list
     * @param num le numero de l'etape
     * @param description la description de l'etape
     * @param duration la durree de l'etape
     */
    public void addStep(final int num, final String description,  final int duration){
        list.add(new Step(presenter.getRecipeUUID(),num,description,duration));
        Log.d("StepPresenter","addStep");
        Collections.sort(list);
        screen.loadView();
    }

    /**
     * permet d'aficher un element de la list dans un screen
     * @param holder la ou l'element va etre afficher
     * @param position la position dans la list de l'element
     */
    public void showStepOn(IStepItemScreen holder, final int position) {
        Step step = list.get(position);
        holder.showStep(step);
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
        presenter.setStepList(list);
    }

    /**
     * permet de supprimer une etape de la list
     * @param uuid uuid de l'etape a supprimer
     */
    public void suppStep(UUID uuid){
        Step i = null;
        for (Step step:list) {
            if(step.getUuid() == uuid) {
                i = step;
            }
        }
        if(!(i == null))list.remove(i);
        screen.loadView();
    }
}

package devmob.rl.reciper.recipeeditor.editorfragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.logging.Logger;

import devmob.rl.reciper.R;
import devmob.rl.reciper.recipeeditor.IFragmentPusher;
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;

public class InfoFragment extends Fragment implements IFragmentPusher {
    public static final String TITLE = "Information";
    private View view;
    private RecipeEditorPresenter presenter;
    private String name_recipe;
    private String difficulty_recipe;
    private String price_recipe;
    private int nbPeople_recipe;
    private String description_recipe;
    private String commentary_recipe;

    public InfoFragment(RecipeEditorPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recipe_editor_info_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        NumberPicker np = (NumberPicker) view.findViewById(R.id.picker_nb_personne);
        np.setMinValue(1);
        np.setMaxValue(100);
        np = (NumberPicker) view.findViewById(R.id.picker_note);
        np.setMinValue(1);
        np.setMaxValue(5);
        Bundle args = getArguments();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.recipe_editor_fragment,menu);
    }

    private String getDifficulty(int id){
        if(id == R.id.facile) return "facile";
        if(id == R.id.moyen) return "moyen";
        if(id == R.id.difficile) return "difficile";
        else throw new IllegalArgumentException();
    }

    private String getPrice(int id){
        if(id == R.id.pas_cher) return "pas cher";
        if(id == R.id.cher) return "cher";
        if(id == R.id.tres_cher) return "tres cher";
        else throw new IllegalArgumentException();
    }


    @Override
    public void push() {
        EditText ed;

        ed = (EditText)view.findViewById(R.id.nom_recette);
        String nom = ed.getText().toString();
        ed = (EditText)view.findViewById(R.id.description_contenu);
        String description = ed.getText().toString();
        ed = (EditText)view.findViewById(R.id.commentaire_contenu);
        String commentaire = ed.getText().toString();

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.layout_difficulte);
        int id_dif = radioGroup.getCheckedRadioButtonId();
        radioGroup = (RadioGroup) view.findViewById(R.id.layout_prix);
        int id_price = radioGroup.getCheckedRadioButtonId();

        NumberPicker np = (NumberPicker) view.findViewById(R.id.picker_nb_personne);
        int numberPerson = np.getValue();
        np = (NumberPicker) view.findViewById(R.id.picker_note);
        int note = np.getValue();

        presenter.setInfoFragment(nom,getDifficulty(id_dif),getPrice(id_price),numberPerson,description,commentaire,note);
        Log.d("1", "passage de push ds infofragment");
    }
}

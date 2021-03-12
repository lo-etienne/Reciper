package devmob.rl.reciper.recipeeditor.editorfragments.info;

import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import java.io.File;

import devmob.rl.reciper.R;
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;

/**
 * Class permetant de set les champ d'une view
 */
public class InfoLoader {

    /**
     * Fonction qui load les value du presenter dans la view
     * @param view la view dont les champ vont etre set
     * @param presenter la d'ou vient les valeur a set
     */
    public static void loadValue(View view, RecipeEditorPresenter presenter) {

        setEditText((EditText)view.findViewById(R.id.nom_recette),presenter.getName_recipe());
        setEditText((EditText)view.findViewById(R.id.description_contenu),presenter.getDescription_recipe());
        setEditText((EditText)view.findViewById(R.id.commentaire_contenu),presenter.getCommentary_recipe());

        setRadioGroup((RadioGroup) view.findViewById(R.id.layout_difficulte),getIdDifficulty(presenter.getDifficulty_recipe()));
        setRadioGroup((RadioGroup) view.findViewById(R.id.layout_prix),getIdPrice(presenter.getPrice_recipe()));

        setNumberPicker((NumberPicker) view.findViewById(R.id.picker_nb_personne), presenter.getNbPeople_recipe());
        setNumberPicker((NumberPicker) view.findViewById(R.id.picker_note), presenter.getNote());

        ImageView image = view.findViewById(R.id.container);
        image.setImageURI(Uri.fromFile(new File(presenter.getImage())));
    }

    /**
     * Fonction qui set un edit text avec une valeur
     * @param ed l'edit text a set
     * @param value la valeur a setter
     */
    private static void setEditText(EditText ed, String value){
        ed.setText(value);
    }

    /**
     * Fonction qui set l'element check d'un radioGroup
     * @param radioGroup le radioGroup qui va avoir un element particulier check
     * @param id l'identifient de l'element a check
     */
    private static void setRadioGroup(RadioGroup radioGroup, int id){
        radioGroup.check(id);
    }

    /**
     * Fonction qui set la valeur de depart d'un NumberPicker
     * @param np le NumberPicker qui va avoir la valeur
     * @param value la valeur a set dans le number picker
     */
    private static void setNumberPicker(NumberPicker np , int value){
        np.setValue(value);
    }

    /**
     * Fonction transformant un String en un id d'un element de RadioGroup
     * @param difficulty_recipe String qui sera transformer
     * @return int representant l'id d'un element du radioGroup Difficulty
     */
    private static int getIdDifficulty(String difficulty_recipe) {
        if(difficulty_recipe.equals("facile")) return R.id.facile;
        if(difficulty_recipe.equals("moyen")) return R.id.moyen;
        if(difficulty_recipe.equals("difficile")) return R.id.difficile;
        else throw new IllegalArgumentException();
    }

    /**
     * Fonction transformant un String en un id d'un element de RadioGroup
     * @param price_recipe String qui sera transformer
     * @return int representant l'id d'un element du radioGroup Price
     */
    private static int getIdPrice(String price_recipe) {
        if(price_recipe.equals("pas cher")) return R.id.pas_cher;
        if(price_recipe.equals("cher")) return R.id.cher;
        if(price_recipe.equals("tres cher")) return R.id.tres_cher;
        else throw new IllegalArgumentException();
    }
}

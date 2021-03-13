package devmob.rl.reciper.recipeeditor.editorfragments.info;

import android.net.Uri;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;

import java.io.File;

import devmob.rl.reciper.R;
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;

/**
 * Class permetant de set les champ d'une view et la gestion des element de la view
 */
public class InfoLoader {

    private static final int NB_PERSONNE_MIN = 1;
    private static final int NB_PERSONNE_MAX = 100;
    private static final int NOTE_MIN = 1;
    private static final int NOTE_MAX = 5;

    private EditText name;
    private EditText description;
    private EditText commentary;

    private RadioGroup difficulty;
    private RadioGroup price;

    private NumberPicker nbPersonne;
    private NumberPicker note;

    private ImageView image;

    public InfoLoader(final EditText name, final EditText description, final EditText commentary,  final RadioGroup difficulty, final RadioGroup price,
                      final NumberPicker nbPersonne, final NumberPicker note, final ImageView image){
        this.name = name;
        this.description = description;
        this.commentary = commentary;
        this.difficulty = difficulty;
        this.price = price;
        this.nbPersonne = nbPersonne;
        this.note = note;
        this.image = image;
    }

    /**
     * Fonction qui load les value du presenter dans les element de la view
     * @param presenter la d'ou vient les valeur a set
     */
    public void loadValue(RecipeEditorPresenter presenter) {

        setEditText(name,presenter.getName_recipe());
        setEditText(description,presenter.getDescription_recipe());
        setEditText(commentary,presenter.getCommentary_recipe());

        setRadioGroup(difficulty,getIdDifficulty(presenter.getDifficulty_recipe()));
        setRadioGroup(price,getIdPrice(presenter.getPrice_recipe()));

        setNumberPicker(nbPersonne, presenter.getNbPeople_recipe());
        setNumberPicker(note, presenter.getNote());

        if(presenter.getImage() != null){
            image.setImageURI(Uri.fromFile(new File(presenter.getImage())));
        }
    }

    /**
     * Fonction qui set un edit text avec une valeur
     * @param ed l'edit text a set
     * @param value la valeur a setter
     */
    private void setEditText(EditText ed, String value){
        ed.setText(value);
    }

    /**
     * Fonction qui set l'element check d'un radioGroup
     * @param radioGroup le radioGroup qui va avoir un element particulier check
     * @param id l'identifient de l'element a check
     */
    private void setRadioGroup(RadioGroup radioGroup, int id){
        radioGroup.check(id);
    }

    /**
     * Fonction qui set la valeur de depart d'un NumberPicker
     * @param np le NumberPicker qui va avoir la valeur
     * @param value la valeur a set dans le number picker
     */
    private void setNumberPicker(NumberPicker np , int value){
        np.setValue(value);
    }

    /**
     * Fonction transformant un String en un id d'un element de RadioGroup
     * @param difficulty_recipe String qui sera transformer
     * @return int representant l'id d'un element du radioGroup Difficulty
     */
    private int getIdDifficulty(@NonNull String difficulty_recipe) {
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
    private int getIdPrice(String price_recipe) {
        if(price_recipe.equals("pas cher")) return R.id.pas_cher;
        if(price_recipe.equals("cher")) return R.id.cher;
        if(price_recipe.equals("tres cher")) return R.id.tres_cher;
        else throw new IllegalArgumentException();
    }

    public void setMinMaxNumberPicker(){
        setMinMaxNumberPicker(nbPersonne, NB_PERSONNE_MIN, NB_PERSONNE_MAX);
        setMinMaxNumberPicker(note, NOTE_MIN, NOTE_MAX);
    }
    private void setMinMaxNumberPicker(final NumberPicker np , final int min, final int max){
        np.setMinValue(min);
        np.setMaxValue(max);
    }
}

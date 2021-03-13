package devmob.rl.reciper.recipeeditor.editorfragments.info;

import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

import devmob.rl.reciper.R;
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;

/**
 * Class qui permet de set les info du presenter
 */
public class InfoSetter {

    /**
     * Fonction qui permet de set les info du presenter via le view
     * @param view contenant les elements a ajouter dans le presenter
     * @param presenter la ou les information doivent etre stocker
     * @param currentPhotoPath le path absolu de la photo
     */
    public static void setInfo(View view, RecipeEditorPresenter presenter, String currentPhotoPath){

        String nom = getTextFromEditText(view.findViewById(R.id.nom_recette));
        String description = getTextFromEditText(view.findViewById(R.id.description_contenu));
        String commentaire = getTextFromEditText(view.findViewById(R.id.commentaire_contenu));

        int id_dif = getValueFromRadioGroup(view.findViewById(R.id.layout_difficulte));
        int id_price = getValueFromRadioGroup(view.findViewById(R.id.layout_prix));

        int numberPerson = getValueFromNumberPicker(view.findViewById(R.id.picker_nb_personne));
        int note = getValueFromNumberPicker(view.findViewById(R.id.picker_note));

        presenter.setInfoFragment(nom,getDifficulty(id_dif),getPrice(id_price),numberPerson,description,commentaire,note,currentPhotoPath);
    }

    /**
     * Fonction permetant d'extraitre un String d'un editText
     * @param ed edit text contenant le String
     * @return String contenue dans l'edit text
     */
    private static String getTextFromEditText(EditText ed){
        return ed.getText().toString();
    }

    /**
     * Fonction permetant d'extraitre un id en fonction du radioGroup
     * @param radioGroup radioGroup d'ou sera extrait l'id
     * @return int representant l'id de l'element selectioner
     */
    private static int getValueFromRadioGroup(RadioGroup radioGroup){
        return radioGroup.getCheckedRadioButtonId();
    }

    /**
     * Fonction permetant d'extraitre une valeur en fonction du NumberPicker
     * @param np NumberPicker d'ou sera extrait la valeur
     * @return int representant la valeur du NumberPicker
     */
    private static int getValueFromNumberPicker(NumberPicker np){
        return np.getValue();
    }

    /**
     * Fonction permetant d'avoir un String en fonction de l'id d'un RadioGroup Difficulty
     * @param id id d'un element d'un RadioGroup
     * @return String representant un element du RadioGroup
     */
    private static String getDifficulty(int id){
        if(id == R.id.facile) return "facile";
        if(id == R.id.moyen) return "moyen";
        if(id == R.id.difficile) return "difficile";
        else throw new IllegalArgumentException();
    }

    /**
     * Fonction permetant d'avoir un String en fonction de l'id d'un RadioGroup Price
     * @param id id d'un element d'un RadioGroup
     * @return String representant un element du RadioGroup
     */
    private static String getPrice(int id){
        if(id == R.id.pas_cher) return "pas cher";
        if(id == R.id.cher) return "cher";
        if(id == R.id.tres_cher) return "tres cher";
        else throw new IllegalArgumentException();
    }
}

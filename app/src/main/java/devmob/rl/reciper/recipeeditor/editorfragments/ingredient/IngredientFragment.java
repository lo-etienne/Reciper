package devmob.rl.reciper.recipeeditor.editorfragments.ingredient;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import devmob.rl.reciper.R;
import devmob.rl.reciper.recipeeditor.IFragmentPusher;
import devmob.rl.reciper.recipeeditor.IScreen.IScreenIngredient;
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;

/**
 * A fragment representing a list of Items.
 */
public class IngredientFragment extends Fragment implements View.OnClickListener, IIngredientList, IFragmentPusher, IScreenIngredient {

    public static final String TITLE = "Ingredient";
    private View view;
    private IngredientPresenter presenter;
    private RecyclerView recyclerView;
    private RecipeEditorPresenter editorPresenter;
    private final boolean newRecipe;
    private ImageButton buttonAdd;
    private EditText editIngredient;
    private EditText editQuantity;

    public IngredientFragment(final RecipeEditorPresenter presenter,final boolean newRecipe){
        this.editorPresenter = presenter;
        this.newRecipe = newRecipe;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recipe_editor_ingredient_fragment, container, false);

        if (view.findViewById(R.id.list_ingredient) instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view.findViewById(R.id.list_ingredient);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new IngredientAdapter(null));
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(newRecipe) {
            presenter = new IngredientPresenter(this, editorPresenter);
            presenter.loadIngredient();
        }else{
            presenter = new IngredientPresenter(this, editorPresenter);
            editorPresenter.setScreenIngredient(this);
            editorPresenter.setDataIngredient(editorPresenter.getRecipeUUID());
        }

        buttonAdd = (ImageButton) view.findViewById(R.id.add_ingredient);
        buttonAdd.setOnClickListener(this);

        editIngredient = (EditText) view.findViewById(R.id.ingredient_field_name);
        editIngredient.setOnClickListener(this);

        editQuantity = (EditText) view.findViewById(R.id.ingredient_field_quantite);
        editQuantity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == buttonAdd.getId()) {//extraire et mettre dans une variable le contenue de l'edit text

            String ingredient = editIngredient.getText().toString();
            String quantity = editQuantity.getText().toString();

            if(ingredient.equals("") || quantity.equals("")){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Un ou plusieur champ(s) sont vide veillez bien remplire les champs")
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {}
                        });
                builder.create().show();
            }else{
                presenter.addIngredient(ingredient, quantity);
                editIngredient.setText("");
                editQuantity.setText("");
                Log.d("IngredientFragment", "addIngredient");
            }

        }
    }

    @Override
    public void loadView() {
        recyclerView.setAdapter(new IngredientAdapter(presenter));
    }

    @Override
    public void push() {
        presenter.publish();
        Log.d("push", "passage de push dans ingredientFragment");
    }

    @Override
    public void update() {
        presenter.loadIngredient();
    }
}
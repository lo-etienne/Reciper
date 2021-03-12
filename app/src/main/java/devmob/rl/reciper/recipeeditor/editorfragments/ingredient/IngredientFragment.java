package devmob.rl.reciper.recipeeditor.editorfragments.ingredient;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import devmob.rl.reciper.R;
import devmob.rl.reciper.recipeeditor.IFragmentPusher;
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;

/**
 * A fragment representing a list of Items.
 */
public class IngredientFragment extends Fragment implements View.OnClickListener, IIngredientList, IFragmentPusher {

    public static final String TITLE = "Ingredient";
    private View view;
    private IngredientPresenter presenter;
    private RecyclerView recyclerView;
    private RecipeEditorPresenter editorPresenter;
    private final boolean newRecipe;

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
        presenter = new IngredientPresenter(this, editorPresenter);
        presenter.loadIngredient();

        ImageButton buttonAdd = (ImageButton) view.findViewById(R.id.add_ingredient);
        buttonAdd.setOnClickListener(this);

        EditText editIngredient = (EditText) view.findViewById(R.id.ingredient_field_name);
        editIngredient.setOnClickListener(this);

        EditText editQuantity = (EditText) view.findViewById(R.id.ingredient_field_quantite);
        editQuantity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_ingredient) {//extraire et mettre dans une variable le contenue de l'edit text
            EditText ed = (EditText) view.findViewById(R.id.ingredient_field_name);
            String ingredient = ed.getText().toString();

            ed = (EditText) view.findViewById(R.id.ingredient_field_quantite);
            String quantity = ed.getText().toString();

            presenter.addIngredient(ingredient, quantity);
            Log.d("1", "addIngredient du presenter appele du onClick");
        }
    }

    @Override
    public void loadView() {
        recyclerView.setAdapter(new IngredientAdapter(presenter));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.validation_button){
            presenter.publish();
            Log.d("1", "passage ds onOptionsItemSelected ingredientFragment");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void push() {
        presenter.publish();
        Log.d("push", "passage de push dans ingredientFragment");
    }
}
package devmob.rl.reciper.recipeeditor.editorfragments.Ingredient;

import android.content.Context;
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
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;

/**
 * A fragment representing a list of Items.
 */
public class IngredientFragment extends Fragment implements View.OnClickListener, IIngredientList{

    public static final String TITLE = "Ingredient";
    private View view;
    private IngredientPresenter presenter;
    private RecyclerView recyclerView;
    private RecipeEditorPresenter editorPresenter;

    public IngredientFragment(RecipeEditorPresenter presenter){
        this.editorPresenter = presenter;
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
        presenter = new IngredientPresenter();
        presenter.setScreen(this);
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
        switch(v.getId())
        {
            case R.id.add_ingredient :

                String ingredient = view.findViewById(R.id.ingredient_field_name).toString();
                String quantity = view.findViewById(R.id.ingredient_field_quantite).toString();
                presenter.addIngredient(ingredient,quantity);
                Log.d("1","test");
                break;
        }
    }

    @Override
    public void loadView() {
        recyclerView.setAdapter(new IngredientAdapter(presenter));
    }
}
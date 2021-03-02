package devmob.rl.reciper.recipedisplayer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.UUID;

import devmob.rl.reciper.R;
import devmob.rl.reciper.database.repository.RecipeRepository;
import devmob.rl.reciper.model.Recipe;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeDisplayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeDisplayerFragment extends Fragment implements IRecipeDisplayerScreen {

    private RecipeDisplayerPresenter recipeDisplayerPresenter;
    private UUID recipeId;

    private TextView recipeName;
    private TextView description;
    private TextView numberOfPersons;
    private TextView time;
    private TextView price;


    public RecipeDisplayerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RecipeDisplayerFragment newInstance(final UUID id) {
        RecipeDisplayerFragment fragment = new RecipeDisplayerFragment();
        fragment.recipeId = id;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recipeDisplayerPresenter = new RecipeDisplayerPresenter(this);
        recipeDisplayerPresenter.loadRecipe(recipeId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe, container, false);
        recipeName = view.findViewById(R.id.recipe_name);
        description = view.findViewById(R.id.recipe_description);
        numberOfPersons = view.findViewById(R.id.person_text);
        time = view.findViewById(R.id.timer_text);
        price = view.findViewById(R.id.price_text);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_recipe, menu);
    }

    @Override
    public void showRecipe(String name, String description, String numberOfPersons, String time, String price) {
        recipeName.setText(name);
        this.description.setText(description);
        this.numberOfPersons.setText(numberOfPersons);
        this.time.setText(time);
        this.price.setText(price);
    }
}
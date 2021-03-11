package devmob.rl.reciper.recipedisplayer;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

import devmob.rl.reciper.R;

/**
 * A fragment representing a list of Items.
 */
public class RecipeDisplayerIngredientsFragment extends Fragment implements IRecipeDisplayerIngredientsScreen {

    private UUID recipeId;

    private RecipeDisplayerIngredientsPresenter recipeDisplayerIngredientsPresenter;
    private RecyclerView recyclerView;

    public RecipeDisplayerIngredientsFragment() {
    }

    public static RecipeDisplayerIngredientsFragment newInstance(final UUID uuid) {
        RecipeDisplayerIngredientsFragment recipeDisplayerIngredientsFragment = new RecipeDisplayerIngredientsFragment();
        recipeDisplayerIngredientsFragment.recipeId = uuid;
        return recipeDisplayerIngredientsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_displayer_ingredients_list, container, false);
        if(view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new RecipeDisplayerIngredientsRecyclerViewAdapter(recipeDisplayerIngredientsPresenter));
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recipeDisplayerIngredientsPresenter = new RecipeDisplayerIngredientsPresenter(recipeId, this);
        recipeDisplayerIngredientsPresenter.loadIngredients();
    }

    @Override
    public void loadView() {
        recyclerView.setAdapter(new RecipeDisplayerIngredientsRecyclerViewAdapter(recipeDisplayerIngredientsPresenter));
    }
}
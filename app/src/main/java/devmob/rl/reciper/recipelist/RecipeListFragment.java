package devmob.rl.reciper.recipelist;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

import devmob.rl.reciper.R;
import devmob.rl.reciper.database.repository.RecipeRepository;
import devmob.rl.reciper.model.Ingredient;
import devmob.rl.reciper.model.Step;

/**
 * A fragment representing a list of Items.
 */
public class  RecipeListFragment extends Fragment implements IRecipeListScreen {

    private static final String TAG = "RecipeListFragment";

    private RecipeListPresenter recipeListPresenter;
    private RecyclerView recyclerView;
    private ISelectRecipe callback;

    public interface ISelectRecipe {
        void onSelectedRecipe(final UUID recipeId);
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RecipeListFragment newInstance() {
        RecipeListFragment fragment = new RecipeListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "OnCreated Called");
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new RecipeAdapter(null, callback));
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recipeListPresenter = new RecipeListPresenter(this);
        recipeListPresenter.loadRecipes();
    }

    @Override
    public void loadView() {
        recyclerView.setAdapter(new RecipeAdapter(recipeListPresenter, callback));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callback = (ISelectRecipe) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_recipe_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.new_recipe) {
            UUID id = recipeListPresenter.addRecipe();
            Step step = new Step(id,1,"Ceci est une description", 10);
            Step step1 = new Step(id,2,"Ceci est une autre description", 20);
            Ingredient ingredient = new Ingredient(id, "Poulet", "400g");
            Ingredient ingredient1 = new Ingredient(id, "Boeuf", "200g");
            Ingredient ingredient2 = new Ingredient(id, "Lapin", "1kg");
            RecipeRepository.getInstance().insertStep(step);
            RecipeRepository.getInstance().insertStep(step1);
            RecipeRepository.getInstance().insertIngredient(ingredient);
            RecipeRepository.getInstance().insertIngredient(ingredient1);
            RecipeRepository.getInstance().insertIngredient(ingredient2);
        }
        return super.onOptionsItemSelected(item);
    }


}
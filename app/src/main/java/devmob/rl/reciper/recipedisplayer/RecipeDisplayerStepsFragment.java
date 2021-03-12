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
public class RecipeDisplayerStepsFragment extends Fragment implements IRecipeDisplayerStepsScreen {

    private UUID recipeId;

    private RecipeDisplayerStepsPresenter recipeDisplayerStepsPresenter;
    private RecyclerView recyclerView;

    public RecipeDisplayerStepsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RecipeDisplayerStepsFragment newInstance(final UUID uuid) {
        RecipeDisplayerStepsFragment recipeDisplayerStepsFragment = new RecipeDisplayerStepsFragment();
        recipeDisplayerStepsFragment.recipeId = uuid;
        return recipeDisplayerStepsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_displayer_steps_list, container, false);
        if(view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new RecipeDisplayerStepsRecyclerViewAdapter(recipeDisplayerStepsPresenter));
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recipeDisplayerStepsPresenter = new RecipeDisplayerStepsPresenter(recipeId, this);
        recipeDisplayerStepsPresenter.loadSteps();
    }

    @Override
    public void loadView() {
        recyclerView.setAdapter(new RecipeDisplayerStepsRecyclerViewAdapter(recipeDisplayerStepsPresenter));
    }
}
package devmob.rl.reciper.recipedisplayer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.util.UUID;

import devmob.rl.reciper.MainActivity;
import devmob.rl.reciper.R;
import devmob.rl.reciper.RecipeEditorActivity;
import devmob.rl.reciper.database.repository.RecipeRepository;
import devmob.rl.reciper.model.Recipe;

public class RecipeDisplayerCollectionFragment extends Fragment {

    private RecipeDisplayerPagerAdapter recipeDisplayerPagerAdapter;
    private ViewPager viewPager;
    private UUID recipeId;

    /**
     * Méthode qui permet de créer une instance de RecipeDisplayerCollection
     * @param uuid id de la recette qui doit être affichée
     * @return une instance d'un objet RecipeDisplayerCollectionFragment
     */
    public static RecipeDisplayerCollectionFragment newInstance(final UUID uuid) {
        RecipeDisplayerCollectionFragment recipeDisplayerCollectionFragment = new RecipeDisplayerCollectionFragment();
        recipeDisplayerCollectionFragment.recipeId = uuid;
        return recipeDisplayerCollectionFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipe_displayer_view_pager,container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecipeDisplayerViewModel viewModel = new ViewModelProvider(this).get(RecipeDisplayerViewModel.class);
        if(viewModel.getRecipeId() == null) {
            viewModel.setRecipeId(recipeId);
        } else {
            recipeId = viewModel.getRecipeId();
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recipeDisplayerPagerAdapter = new RecipeDisplayerPagerAdapter(getChildFragmentManager(), recipeId);
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(recipeDisplayerPagerAdapter);
        TabLayout tableLayout = view.findViewById(R.id.tab_layout);
        tableLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_recipe, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*
        if(item.getItemId() == R.id.share_recipe) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra("recipe", new Gson().toJson(new Recipe()));
            startActivity(shareIntent);
            return true;
        }
         */
        if(item.getItemId() == R.id.edit_recipe){
            Intent intent = new Intent(getActivity(), RecipeEditorActivity.class);
            intent.putExtra("recipeId", recipeId.toString());
            startActivity(intent);
        }
        if(item.getItemId() == R.id.delete_recipe) {
            initDeleteRecipe(recipeId);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Méthode qui, exécutée par la méthode {@link android.app.Activity::onOptionsItemSelected}, permet d'initialiser la suppression d'une recette.
     * Une confirmation sera demandée à l'utilisateur, si il appuie sur Non ou s'il quitte la fenêtre de dialogue sans répondre alors la recette ne sera pas supprimée.
     * S'il appuie sur Oui, alors la recette sera supprimée grâce à la méthode {@link RecipeRepository::deleteElementsAndRecipe}
     * @param uuid id de la recette à supprimer
     */
    private void initDeleteRecipe(final UUID uuid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setCancelable(true);
        builder.setTitle("Supprimer ?");
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RecipeRepository.getInstance().deleteElementsAndRecipe(uuid);
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

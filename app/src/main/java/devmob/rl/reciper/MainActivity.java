package devmob.rl.reciper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.UUID;

import devmob.rl.reciper.recipeeditor.RecipeCollectionEditorFragment;
import devmob.rl.reciper.recipeeditor.editorfragments.Ingredient.IngredientFragment;
import devmob.rl.reciper.recipelist.RecipeListFragment;

public class MainActivity extends AppCompatActivity implements RecipeListFragment.ISelectRecipe{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*
        Recipe recipe = new Recipe();
        recipe.setName("Poulet au paprika");
        recipe.setNumberOfPersons(10);
        recipe.setDescription("Poulet au paprika avec pommes de terre et brocolis");
        Recipe recipe2 = new Recipe();
        recipe2.setName("Avocat fourré au saumon");
        recipe2.setNumberOfPersons(5);
        recipe2.setDescription("Avocat fourré au saumon avec de la semoule");
        RecipeRepository.getInstance().insertRecipe(recipe);
        RecipeRepository.getInstance().insertRecipe(recipe2);
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if(currentFragment == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, RecipeListFragment.newInstance()).commit();
        }
    }

    /**
     * Méthode qui permet de switch d'activité
     */
    private void swicthActivities(final UUID recipeId) {
        Intent switchActivityIntent = new Intent(this, RecipeDisplayerActivity.class);
        switchActivityIntent.putExtra("recipeId", recipeId.toString());
        startActivity(switchActivityIntent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.new_recipe) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, RecipeCollectionEditorFragment.newInstance()).addToBackStack(null).commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelectedRecipe(UUID recipeId) {
        swicthActivities(recipeId);
        // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, RecipeDisplayerFragment.newInstance(recipeId)).addToBackStack(null).commit();
    }
}
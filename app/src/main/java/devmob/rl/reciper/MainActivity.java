package devmob.rl.reciper;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.UUID;

import devmob.rl.reciper.recipelist.RecipeListFragment;

/**
 *  Cette classe correspond à l'activité principale de l'application
 */
public class MainActivity extends AppCompatActivity implements RecipeListFragment.ISelectRecipe{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if(currentFragment == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, RecipeListFragment.newInstance()).commit();
        }
    }

    /**
     * Méthode qui permet de lancer l'activité qui permet d'afficher une recette grâce à son id
     */
    private void launchRecipeDisplayerActivity(final UUID recipeId) {
        Intent switchActivityIntent = new Intent(this, RecipeDisplayerActivity.class);
        switchActivityIntent.putExtra("recipeId", recipeId.toString());
        startActivity(switchActivityIntent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.new_recipe) {
            Intent switchActivityIntent = new Intent(this, RecipeEditorActivity.class);
            startActivity(switchActivityIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelectedRecipe(UUID recipeId) {
        launchRecipeDisplayerActivity(recipeId);
    }
}
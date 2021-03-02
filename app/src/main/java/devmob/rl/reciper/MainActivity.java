package devmob.rl.reciper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;

import java.util.UUID;

import devmob.rl.reciper.recipeeditor.RecipeEditorFragment;
import devmob.rl.reciper.recipeeditor.editorfragments.CollectionEditorFragment;
import devmob.rl.reciper.recipelist.RecipeListFragment;
import devmob.rl.reciper.recipedisplayer.RecipeDisplayerFragment;

public class MainActivity extends AppCompatActivity implements RecipeListFragment.ISelectRecipe {

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.new_recipe) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, CollectionEditorFragment.newInstance()).addToBackStack(null).commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelectedRecipe(UUID recipeId) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, RecipeDisplayerFragment.newInstance()).addToBackStack(null).commit();
    }
}
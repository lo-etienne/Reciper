package devmob.rl.reciper;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.UUID;

import devmob.rl.reciper.recipeeditor.RecipeCollectionEditorFragment;

public class RecipeEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_editor);
        //getSupportActionBar().setDisplayShowTitleEnabled(true);
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_editor);
        if(currentFragment == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_editor, RecipeCollectionEditorFragment.newInstance()).commit();
        }
    }

    private UUID retrieveUuid() {
        return UUID.fromString(getIntent().getStringExtra("recipeId"));
    }
    //TODO : mettre une photo ds info, ajouter un trie au list de step, modifier la class RecipeEditorFragment pour recupere les info d'une recette
}

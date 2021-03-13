package devmob.rl.reciper;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.UUID;

import devmob.rl.reciper.database.repository.RecipeRepository;
import devmob.rl.reciper.recipeeditor.RecipeCollectionEditorFragment;

public class RecipeEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_editor);
        //getSupportActionBar().setDisplayShowTitleEnabled(true);
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_editor);
        if(currentFragment == null) {
            if(getIntent().hasExtra("recipeId")){
                Log.d("tag", "uuid " + retrieveUuid().toString());
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_editor, RecipeCollectionEditorFragment.newInstance(RecipeRepository.getInstance(),retrieveUuid())).commit();
            }else{
                getSupportFragmentManager().beginTransaction().add(R.id.fragment_editor, RecipeCollectionEditorFragment.newInstance(RecipeRepository.getInstance())).commit();
            }
        }


    }

    private UUID retrieveUuid() {
        return UUID.fromString(getIntent().getStringExtra("recipeId"));
    }
}

package devmob.rl.reciper;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import java.util.UUID;

import devmob.rl.reciper.recipedisplayer.RecipeDisplayerCollectionFragment;

public class RecipeDisplayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipedisplayer);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if(currentFragment == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, RecipeDisplayerCollectionFragment.newInstance(retrieveUuid())).commit();
        }
    }

    private UUID retrieveUuid() {
        return UUID.fromString(getIntent().getStringExtra("recipeId"));
    }
}

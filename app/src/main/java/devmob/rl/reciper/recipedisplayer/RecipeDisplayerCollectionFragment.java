package devmob.rl.reciper.recipedisplayer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.UUID;

import devmob.rl.reciper.R;

public class RecipeDisplayerCollectionFragment extends Fragment {

    private RecipeDisplayerPagerAdapter recipeDisplayerPagerAdapter;
    private ViewPager viewPager;
    private UUID recipeId;

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
}

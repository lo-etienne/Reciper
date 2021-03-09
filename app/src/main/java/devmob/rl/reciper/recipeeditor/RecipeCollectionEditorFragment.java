package devmob.rl.reciper.recipeeditor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import devmob.rl.reciper.MainActivity;
import devmob.rl.reciper.R;

public class RecipeCollectionEditorFragment extends Fragment {

    private RecipeCollectionEditorPagerAdapter collection;
    private ViewPager viewPager;
    private RecipeEditorPresenter presenter;
    private View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recipe_editor_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter = new RecipeEditorPresenter();
        TabLayout tab = view.findViewById(R.id.tab_layout);
        //avant -> collection = new CollectionEditorPagerAdapter(getChildFragmentManager());
        collection = new RecipeCollectionEditorPagerAdapter(getChildFragmentManager(),presenter);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(collection);
        tab.setupWithViewPager(this.viewPager);
    }

    public static RecipeCollectionEditorFragment newInstance() {

        RecipeCollectionEditorFragment fragment = new RecipeCollectionEditorFragment();
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.recipe_editor_fragment,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.validation_button){
            collection.pushData();
            presenter.createRecipe();
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

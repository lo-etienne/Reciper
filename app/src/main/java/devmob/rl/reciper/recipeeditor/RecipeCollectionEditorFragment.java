package devmob.rl.reciper.recipeeditor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import java.util.UUID;

import devmob.rl.reciper.MainActivity;
import devmob.rl.reciper.R;
import devmob.rl.reciper.database.repository.RecipeRepository;

public class RecipeCollectionEditorFragment extends Fragment {

    private RecipeCollectionEditorPagerAdapter collection;
    private ViewPager viewPager;
    private RecipeEditorPresenter presenter;
    private final boolean newRecipe;

    public static RecipeCollectionEditorFragment newInstance(RecipeRepository dataBase) {
        RecipeCollectionEditorFragment fragment = new RecipeCollectionEditorFragment(dataBase);
        return fragment;
    }
    public static RecipeCollectionEditorFragment newInstance(RecipeRepository dataBase, UUID uuid) {
        Log.d("RecipeCollectionEditor", "newInstance");
        RecipeCollectionEditorFragment fragment = new RecipeCollectionEditorFragment(dataBase,uuid);
        return fragment;
    }

    public RecipeCollectionEditorFragment(RecipeRepository dataBase){
        newRecipe = true;
        presenter = new RecipeEditorPresenter(dataBase);
    }
    public RecipeCollectionEditorFragment(RecipeRepository dataBase, UUID uuid){
        newRecipe = false;
        presenter = new RecipeEditorPresenter(dataBase, uuid);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipe_editor_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TabLayout tab = view.findViewById(R.id.tab_layout);
        collection = new RecipeCollectionEditorPagerAdapter(getChildFragmentManager(),presenter,newRecipe);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(collection);
        tab.setupWithViewPager(this.viewPager);
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
            if(newRecipe){
                presenter.createRecipe();
            }else{
                presenter.updateData();
            }
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

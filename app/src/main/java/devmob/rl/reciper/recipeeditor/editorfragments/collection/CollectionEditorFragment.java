package devmob.rl.reciper.recipeeditor.editorfragments.collection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import devmob.rl.reciper.R;
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;

public class CollectionEditorFragment extends Fragment {

    private CollectionEditorPagerAdapter collection;
    private ViewPager viewPager;
    private RecipeEditorPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipe_editor_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter = new RecipeEditorPresenter();
        TabLayout tab = view.findViewById(R.id.tab_layout);
        //avant -> collection = new CollectionEditorPagerAdapter(getChildFragmentManager());
        collection = new CollectionEditorPagerAdapter(getChildFragmentManager(),presenter);
        viewPager = view.findViewById(R.id.pager);
        viewPager.setAdapter(collection);
        tab.setupWithViewPager(this.viewPager);
    }

    public static CollectionEditorFragment newInstance() {

        CollectionEditorFragment fragment = new CollectionEditorFragment();
        return fragment;
    }
}

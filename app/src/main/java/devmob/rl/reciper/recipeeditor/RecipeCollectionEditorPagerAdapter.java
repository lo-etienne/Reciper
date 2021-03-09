package devmob.rl.reciper.recipeeditor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import devmob.rl.reciper.recipeeditor.editorfragments.InfoFragment;
import devmob.rl.reciper.recipeeditor.editorfragments.Ingredient.IngredientFragment;
import devmob.rl.reciper.recipeeditor.editorfragments.step.StepFragment;

public class RecipeCollectionEditorPagerAdapter extends FragmentStatePagerAdapter {
    private RecipeEditorPresenter presenter;
    private List<IFragmentPusher> list = new ArrayList<>();

    public RecipeCollectionEditorPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public RecipeCollectionEditorPagerAdapter(@NonNull FragmentManager fm, RecipeEditorPresenter presenter) {
        super(fm);
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        Fragment fragment;
        switch (position){
            case 0:
                args.putInt(InfoFragment.TITLE, position + 1);
                fragment = new InfoFragment(presenter);
                fragment.setArguments(args);
                list.add((IFragmentPusher) fragment);
                return fragment;
            case 1:
                args.putInt(IngredientFragment.TITLE, position + 1);
                fragment = new IngredientFragment(presenter);
                fragment.setArguments(args);
                list.add((IFragmentPusher) fragment);
                return fragment;
            case 2:
                args.putInt(StepFragment.TITLE, position + 1);
                fragment = new StepFragment(presenter);
                fragment.setArguments(args);
                list.add((IFragmentPusher) fragment);
                return fragment;
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Info";
            case 1:
                return "Ingredients";
            case 2:
                return "Etapes";
            default:
                return "untitled";
        }
    }

    public void pushData(){
        for (IFragmentPusher fragmentPusher:list) {
            fragmentPusher.push();
        }
    }
}

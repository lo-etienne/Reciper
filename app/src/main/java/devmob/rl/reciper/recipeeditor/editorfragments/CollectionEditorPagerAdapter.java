package devmob.rl.reciper.recipeeditor.editorfragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class CollectionEditorPagerAdapter extends FragmentStatePagerAdapter {

    public CollectionEditorPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Bundle args = new Bundle();
        Fragment fragment;
        switch (position){
            case 0:
                args.putInt(InfoFragment.TITLE, position + 1);
                fragment = new InfoFragment();
                fragment.setArguments(args);
                return fragment;
            case 1:
                args.putInt(IngredientFragment.TITLE, position + 1);
                fragment = new IngredientFragment();
                fragment.setArguments(args);
                return fragment;
            case 2:
                args.putInt(StepFragment.TITLE, position + 1);
                fragment = new StepFragment();
                fragment.setArguments(args);
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
}

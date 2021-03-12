package devmob.rl.reciper.recipedisplayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.UUID;

public class RecipeDisplayerPagerAdapter extends FragmentStatePagerAdapter {

    private final UUID recipeId;

    public RecipeDisplayerPagerAdapter(final FragmentManager fm, final UUID uuid) {
        super(fm);
        this.recipeId = uuid;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RecipeDisplayerInformationFragment.newInstance(recipeId);
            case 1:
                return RecipeDisplayerIngredientsFragment.newInstance(recipeId);
                // return RecipeDisplayerIngredientsFragment.newInstance(recipeId);
            case 2:
                return RecipeDisplayerStepsFragment.newInstance(recipeId);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Informations";
            case 1:
                return "Ingrédients";
            case 2:
                return "Étapes";
            default:
                return "Onglet";
        }
    }
}

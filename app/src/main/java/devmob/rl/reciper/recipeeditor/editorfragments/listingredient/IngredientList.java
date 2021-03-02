package devmob.rl.reciper.recipeeditor.editorfragments.listingredient;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import devmob.rl.reciper.R;
import devmob.rl.reciper.recipelist.RecipeAdapter;

public class IngredientList extends Fragment {

    private List<String> list;

    public IngredientList(List<String> list){
        this.list = list;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.testlist, container, false);
    }

}

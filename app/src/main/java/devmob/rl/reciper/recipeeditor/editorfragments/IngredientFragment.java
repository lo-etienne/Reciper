package devmob.rl.reciper.recipeeditor.editorfragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import devmob.rl.reciper.R;
import devmob.rl.reciper.recipeeditor.editorfragments.dummy.DummyContent;

/**
 * A fragment representing a list of Items.
 */
public class IngredientFragment extends Fragment {

    public static final String TITLE = "Ingredient";
    public View view;
    public List<String> list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipe_editor_ingredient_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.view = view;
        Bundle args = getArguments();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_ingredient) {
            String ingredient = view.findViewById(R.id.ingredient_field).toString();
            list.add(ingredient);
            ListView list = (ListView)view.findViewById(R.id.list_ingredient_test);
        }
        return super.onOptionsItemSelected(item);
    }
}
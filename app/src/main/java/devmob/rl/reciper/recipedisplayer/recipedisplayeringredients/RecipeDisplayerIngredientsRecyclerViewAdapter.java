package devmob.rl.reciper.recipedisplayer.recipedisplayeringredients;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import devmob.rl.reciper.R;

public class RecipeDisplayerIngredientsRecyclerViewAdapter extends RecyclerView.Adapter<RecipeDisplayerIngredientsRecyclerViewAdapter.ViewHolder> {

    private final RecipeDisplayerIngredientsPresenter presenter;

    public RecipeDisplayerIngredientsRecyclerViewAdapter(final RecipeDisplayerIngredientsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recipe_displayer_ingredient, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        presenter.showIngredientOn(holder, position);
    }

    @Override
    public int getItemCount() {
        if(presenter == null) {
            return 0;
        } else {
            return presenter.getItemCount();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements IIngredientItemScreen {

        public final View view;

        private TextView ingredientName;
        private TextView ingredientQuantity;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.ingredientName = view.findViewById(R.id.ingredient_name);
            this.ingredientQuantity = view.findViewById(R.id.ingredient_quantity);
        }

        @Override
        public String toString() {
            return "";}

        @Override
        public void showIngredient(String name, String quantity) {
            this.ingredientName.setText(name);
            this.ingredientQuantity.setText(quantity);
        }
    }
}
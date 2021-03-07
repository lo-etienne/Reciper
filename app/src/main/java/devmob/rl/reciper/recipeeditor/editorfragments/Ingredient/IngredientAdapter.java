package devmob.rl.reciper.recipeeditor.editorfragments.Ingredient;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Placeholder;
import androidx.recyclerview.widget.RecyclerView;

import devmob.rl.reciper.R;
import devmob.rl.reciper.model.Ingredient;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>{
    private IngredientPresenter presenter;

    public IngredientAdapter(IngredientPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public IngredientAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from((parent.getContext()));
        View view = layoutInflater.inflate(R.layout.recipe_item_list, parent, false);
        return new IngredientAdapter.ViewHolder(view);
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements IIngredientItemScreen{
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.ingredient);

        }

        @Override
        public void showIngredient(Ingredient ingredient) {
            Log.d("1",ingredient.getName() + " " + ingredient.getQuantity());
            textView.setText(ingredient.getName() + " " + ingredient.getQuantity());
        }
    }
}

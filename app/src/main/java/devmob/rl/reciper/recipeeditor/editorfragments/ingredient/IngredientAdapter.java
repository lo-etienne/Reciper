package devmob.rl.reciper.recipeeditor.editorfragments.ingredient;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.UUID;

import devmob.rl.reciper.R;
import devmob.rl.reciper.model.Ingredient;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder>{
    private IngredientPresenter presenter;

    public IngredientAdapter(IngredientPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from((parent.getContext()));
        View view = layoutInflater.inflate(R.layout.fragment_ingredient_item_list, parent, false);
        return new ViewHolder(view,presenter);
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements IIngredientItemScreen, View.OnClickListener{
        private final TextView textView;
        private UUID uuid;
        private IngredientPresenter presenter;

        public ViewHolder(View view,IngredientPresenter presenter) {
            super(view);
            this.presenter = presenter;
            textView = (TextView)view.findViewById(R.id.ingredient);

            ImageButton buttonDel = (ImageButton) view.findViewById(R.id.button_delete_ingredient);
            buttonDel.setOnClickListener(this);
        }

        @Override
        public void showIngredient(Ingredient ingredient) {
            this.uuid = ingredient.getUuid();
            Log.d("1","passage dans showIngredient");
            textView.setText(ingredient.getName() + " " + ingredient.getQuantity());
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.button_delete_ingredient) {
                Log.d("1","button_delete_ingredient d'un ingredient appeler");
                presenter.suppIngredient(uuid);
            }
        }
    }
}

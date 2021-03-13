package devmob.rl.reciper.recipedisplayer.recipedisplayersteps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import devmob.rl.reciper.R;

public class RecipeDisplayerStepsRecyclerViewAdapter extends RecyclerView.Adapter<RecipeDisplayerStepsRecyclerViewAdapter.ViewHolder> {

    private final RecipeDisplayerStepsPresenter presenter;

    public RecipeDisplayerStepsRecyclerViewAdapter(final RecipeDisplayerStepsPresenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_recipe_displayer_step, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        presenter.showStepOn(holder, position);
    }

    @Override
    public int getItemCount() {
        if(presenter == null) {
            return 0;
        } else {
            return presenter.getItemCount();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements IStepItemScreen {

        public final View view;

        private TextView stepNumber;
        private TextView stepDescription;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.stepNumber = view.findViewById(R.id.step_number);
            this.stepDescription = view.findViewById(R.id.step_description);
        }

        @Override
        public String toString() {
            return "";
        }

        @Override
        public void showStep(int number, String description) {
            this.stepNumber.setText(Integer.toString(number));
            this.stepDescription.setText(description);
        }
    }
}
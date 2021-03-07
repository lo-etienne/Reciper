package devmob.rl.reciper.recipeeditor.editorfragments.step;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import devmob.rl.reciper.R;
import devmob.rl.reciper.model.Step;

public class StepAdapter extends RecyclerView.Adapter<StepAdapter.ViewHolder> {
    private StepPresenter presenter;

    public StepAdapter(StepPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public StepAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from((parent.getContext()));
        View view = layoutInflater.inflate(R.layout.recipe_item_list, parent, false);
        return new StepAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final StepAdapter.ViewHolder holder, int position) {
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements IStepItemScreen {
        private TextView textView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.step);

        }

        @Override
        public void showStep(Step step) {
            Log.d("1",step.getDescription());
            textView.setText(step.getDescription());
        }
    }
}

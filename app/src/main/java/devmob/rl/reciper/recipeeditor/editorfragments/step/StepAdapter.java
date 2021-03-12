package devmob.rl.reciper.recipeeditor.editorfragments.step;

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
        View view = layoutInflater.inflate(R.layout.fragment_step_item_list, parent, false);
        return new StepAdapter.ViewHolder(view,presenter);
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

    public static class ViewHolder extends RecyclerView.ViewHolder implements IStepItemScreen, View.OnClickListener{
        private TextView textView;
        private StepPresenter presenter;
        private UUID uuid_step;

        public ViewHolder(View view, StepPresenter presenter) {
            super(view);
            this.presenter = presenter;
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.step);

            ImageButton buttonDel = (ImageButton) view.findViewById(R.id.button_delete_step);
            buttonDel.setOnClickListener(this);

        }

        @Override
        public void showStep(Step step) {
            this.uuid_step = step.getUuid();
            String str = "L'etape " + step.getNum() +"\n" + step.getDescription() + "\n+- " + step.getDuration() + " min.";
            Log.d("1","Passage dans showStep");
            textView.setText(str);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.button_delete_step) {
                Log.d("1","button_delete_ingredient d'un ingredient appeler");
                presenter.suppStep(uuid_step);
            }
        }
    }
}

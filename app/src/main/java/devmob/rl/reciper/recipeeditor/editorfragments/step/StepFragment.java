package devmob.rl.reciper.recipeeditor.editorfragments.step;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import devmob.rl.reciper.R;
import devmob.rl.reciper.recipeeditor.IFragmentPusher;
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;

public class StepFragment extends Fragment implements View.OnClickListener,IStepList, IFragmentPusher {
    public static final String TITLE = "Step";
    private View view;
    private StepPresenter presenter;
    private RecyclerView recyclerView;
    private RecipeEditorPresenter editorPresenter;
    private final boolean newRecipe;

    public StepFragment(RecipeEditorPresenter presenter,boolean newRecipe){
        this.editorPresenter = presenter;
        this.newRecipe = newRecipe;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recipe_editor_step_fragment, container, false);

        if (view.findViewById(R.id.list_step) instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view.findViewById(R.id.list_step);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new StepAdapter(null));
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter = new StepPresenter(this, editorPresenter);
        presenter.loadStep();
        ImageButton buttonAdd = (ImageButton) view.findViewById(R.id.add_step);
        buttonAdd.setOnClickListener(this);
    }

    @Override
    public void loadView() {
        recyclerView.setAdapter(new StepAdapter(presenter));
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.add_step :
                EditText ed;

                ed = (EditText) view.findViewById(R.id.numero_step);
                int num = Integer.parseInt(ed.getText().toString());

                ed = (EditText) view.findViewById(R.id.step_description);
                String description = ed.getText().toString();

                ed = (EditText) view.findViewById(R.id.step_duration);
                int duration = Integer.parseInt(ed.getText().toString());

                presenter.addStep(num, description, duration);
                Log.d("1","Passage de addStep dans StepFragment");
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.validation_button){
            presenter.publish();
            Log.d("1", "passage ds onOptionsItemSelected StepFragment");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void push() {
        presenter.publish();
        Log.d("1", "passage de push ds StepFragment");
    }
}

package devmob.rl.reciper.recipeeditor.editorfragments.step;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import devmob.rl.reciper.R;
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;

public class StepFragment extends Fragment implements View.OnClickListener,IStepList{
    public static final String TITLE = "Step";
    private View view;
    private StepPresenter presenter;
    private RecyclerView recyclerView;
    private RecipeEditorPresenter editorPresenter;

    public StepFragment(RecipeEditorPresenter presenter){
        this.editorPresenter = presenter;
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
        presenter = new StepPresenter(this);
        presenter.loadStep();
        Bundle args = getArguments();
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

                String description = view.findViewById(R.id.step_description).toString();
                Log.d("1",description);
                presenter.addStep(description);
                break;
        }
    }
}

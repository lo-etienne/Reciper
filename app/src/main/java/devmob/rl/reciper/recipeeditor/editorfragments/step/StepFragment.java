package devmob.rl.reciper.recipeeditor.editorfragments.step;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.UUID;

import devmob.rl.reciper.R;
import devmob.rl.reciper.recipeeditor.IFragmentPusher;
import devmob.rl.reciper.recipeeditor.IScreen.IScreenStep;
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;
import devmob.rl.reciper.recipeeditor.RecipeEditorViewModel;
import devmob.rl.reciper.recipeeditor.editorfragments.ingredient.IngredientPresenter;

public class StepFragment extends Fragment implements View.OnClickListener,IStepList, IFragmentPusher, IScreenStep {
    public static final String TITLE = "Step";
    private View view;
    private StepPresenter presenter;
    private RecyclerView recyclerView;
    private RecipeEditorPresenter editorPresenter;
    private boolean newRecipe;
    private UUID uuid;

    public StepFragment(){

    }
    public StepFragment(RecipeEditorPresenter presenter,boolean newRecipe){
        this.editorPresenter = presenter;
        this.newRecipe = newRecipe;
        this.uuid = editorPresenter.getRecipeUUID();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecipeEditorViewModel viewModel = new ViewModelProvider(this).get(RecipeEditorViewModel.class);
        if(viewModel.getId() == null && viewModel.getPresenter() == null){
            viewModel.setId(uuid);
            viewModel.setPresenter(editorPresenter);
        }else{
            editorPresenter = viewModel.getPresenter();
            newRecipe = editorPresenter.isNewRecipe();
        }

        if(newRecipe) {
            presenter = new StepPresenter(this, editorPresenter);
            presenter.loadStep();
        }else{
            presenter = new StepPresenter(this, editorPresenter);
            editorPresenter.setScreenStep(this);
            editorPresenter.setDataStep();
        }
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
    public void onDestroy() {
        super.onDestroy();
        presenter.publish();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageButton buttonAdd = (ImageButton) view.findViewById(R.id.add_step);
        buttonAdd.setOnClickListener(this);
    }

    @Override
    public void loadView() {
        recyclerView.setAdapter(new StepAdapter(presenter));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_step) {
            EditText edNumero_step = (EditText) view.findViewById(R.id.numero_step);
            EditText edStep_description = (EditText) view.findViewById(R.id.step_description);
            EditText edStep_duration = (EditText) view.findViewById(R.id.step_duration);

            int num = Integer.parseInt(edNumero_step.getText().toString());
            String description = edStep_description.getText().toString();
            int duration = Integer.parseInt(edStep_duration.getText().toString());

            if(num < 0 || description.equals("") || duration < 0){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Un ou plusieur champ(s) sont vide veillez bien remplire les champs")
                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {}
                        });
                builder.create().show();
            }else{
                presenter.addStep(num, description, duration);
                edNumero_step.setText("");
                edStep_description.setText("");
                edStep_duration.setText("");
            }
            Log.d("1", "Passage de addStep dans StepFragment");
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
        Log.d("push", "passage de push dans StepFragment");
    }

    @Override
    public void update() {
        presenter.loadStep();
    }
}

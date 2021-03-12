package devmob.rl.reciper.recipedisplayer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

import devmob.rl.reciper.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecipeDisplayerInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeDisplayerInformationFragment extends Fragment implements IRecipeDisplayerInformationScreen {

    private RecipeDisplayerInformationPresenter recipeDisplayerInformationPresenter;
    private UUID recipeId;

    private TextView recipeName;
    private TextView recipeDescription;
    private TextView recipeTime;
    private TextView recipeNumberOfPersons;
    private TextView recipePrice;
    private TextView recipeDifficulty;
    private TextView recipeNote;
    private TextView recipeComment;

    public RecipeDisplayerInformationFragment() {
        // Required empty public constructor
    }

    public static RecipeDisplayerInformationFragment newInstance(final UUID uuid) {
        RecipeDisplayerInformationFragment fragment = new RecipeDisplayerInformationFragment();
        fragment.recipeId = uuid;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_displayer_information, container, false);
        this.recipeName = view.findViewById(R.id.recipe_name);
        this.recipeDescription = view.findViewById(R.id.recipe_description);
        this.recipeTime = view.findViewById(R.id.timer_value);
        this.recipeNumberOfPersons = view.findViewById(R.id.person_value);
        this.recipePrice = view.findViewById(R.id.price_value);
        this.recipeDifficulty = view.findViewById(R.id.difficulty_value);
        this.recipeNote = view.findViewById(R.id.note_value);
        this.recipeComment = view.findViewById(R.id.comment_value);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recipeDisplayerInformationPresenter = new RecipeDisplayerInformationPresenter(this);
        recipeDisplayerInformationPresenter.loadRecipeInformation(recipeId);
    }

    @Override
    public void showRecipeInformation(String recipeName, String recipeDescription, String recipeTime, String recipeNumberOfPerson, String recipePrice, String recipeDifficulty, String recipeNote, String recipeComment) {
        this.recipeName.setText(recipeName);
        this.recipeDescription.setText(recipeDescription);
        this.recipeTime.setText(recipeTime);
        this.recipeNumberOfPersons.setText(recipeNumberOfPerson);
        this.recipePrice.setText(recipePrice);
        this.recipeDifficulty.setText(recipeDifficulty);
        this.recipeNote.setText(recipeNote);
        this.recipeComment.setText(recipeComment);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
}
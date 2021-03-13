package devmob.rl.reciper.recipeeditor;

import androidx.lifecycle.ViewModel;

import java.util.UUID;

public class RecipeEditorViewModel extends ViewModel {

    private UUID id;

    private RecipeEditorPresenter presenter;

    public UUID getId() { return id; }
    public void setId(UUID uuid) { this.id = uuid; }

    public RecipeEditorPresenter getPresenter() {
        return presenter;
    }

    public void setPresenter(RecipeEditorPresenter presenter) {
        this.presenter = presenter;
    }
}

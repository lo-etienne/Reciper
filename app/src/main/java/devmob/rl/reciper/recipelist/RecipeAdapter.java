package devmob.rl.reciper.recipelist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.UUID;

import devmob.rl.reciper.R;
import devmob.rl.reciper.database.repository.RecipeRepository;
import devmob.rl.reciper.model.Recipe;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private final RecipeListPresenter presenter;
    private RecipeListFragment.ISelectRecipe callBacks;

    /**
     * Constructeur d'objet RecipeAdapter
     * @param presenter
     * @param callBacks
     */
    public RecipeAdapter(RecipeListPresenter presenter, RecipeListFragment.ISelectRecipe callBacks) {
        this.presenter = presenter;
        this.callBacks = callBacks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recipe_item_list, parent, false);
        return new ViewHolder(view, callBacks);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
       presenter.showRecipeOn(holder, position);
    }

    @Override
    public int getItemCount() {
        if(presenter == null) {
            return 0;
        } else {
            return presenter.getItemCount();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, IRecipeItemScreen{
        public final View view;
        private RecipeListFragment.ISelectRecipe callBacks;
        public ImageView recipeImage;
        public TextView recipeNameTextView;
        public TextView recipeTime;
        public TextView recipeNumberOfPerson;
        public ImageButton favoriteButton;
        private UUID uuid;

        public ViewHolder(View view, RecipeListFragment.ISelectRecipe callBacks) {
            super(view);
            this.view = view;
            this.callBacks = callBacks;
            // Si on clique sur un élément de la liste, on accède à ses data (RecipeFragment)
            view.setOnClickListener(this);
            recipeImage = view.findViewById(R.id.recipe_illustration);
            recipeNameTextView = view.findViewById(R.id.recipe_name);
            recipeTime = view.findViewById(R.id.timer_text);
            recipeNumberOfPerson = view.findViewById(R.id.person_text);
            favoriteButton = view.findViewById(R.id.favorite_button);
            favoriteButton.setOnClickListener(this);

        }


        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.favorite_button) {
                if(favoriteButton.getTag() != null && favoriteButton.getTag().toString().equals("favorite")) {
                    RecipeRepository.getInstance().updateRecipeFavoriteStatut(false, uuid);
                } else if(favoriteButton.getTag() != null && favoriteButton.getTag().toString().equals("notFavorite")) {
                    RecipeRepository.getInstance().updateRecipeFavoriteStatut(true, uuid);
                }
            } else {
                callBacks.onSelectedRecipe(uuid);
            }
        }

        /**
         * Méthode qui permet d'afficher une recette selon son id
         * @param id id de la recette
         * @param name
         */
        @Override
        public void showRecipe(UUID id, String path, String name, int time, int numberOfPerson) {
            this.uuid = id;
            if (path != null) {
                recipeImage.setImageURI(Uri.fromFile(new File(path)));
            }
            recipeNameTextView.setText(name);
            recipeTime.setText(Integer.toString(time));
            recipeNumberOfPerson.setText(Integer.toString(numberOfPerson));
            final LiveData<Recipe> recipeLiveData = RecipeRepository.getInstance().getRecipe(uuid);
            recipeLiveData.observeForever(new Observer<Recipe>() {
                @Override
                public void onChanged(Recipe recipe) {
                    if(recipe.isFavorite()) {
                        ViewHolder.this.favoriteButton.setTag("favorite");
                        ViewHolder.this.favoriteButton.setImageResource(R.drawable.ic_favorite);
                    } else {
                        ViewHolder.this.favoriteButton.setTag("notFavorite");
                        ViewHolder.this.favoriteButton.setImageResource(R.drawable.ic_not_favorite);
                    }
                    recipeLiveData.removeObserver(this);
                }
            });
        }

    }
}
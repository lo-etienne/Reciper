package devmob.rl.reciper.recipelist;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
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

    /**
     * Méthode qui retourne le nombre de recette dans la DB
     * @return un entier correspondant au nombre de recettes présentes dans la DB
     */
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
        public TextView recipeNameTextView;
        public ImageButton favoriteButton;
        private UUID uuid;

        public ViewHolder(View view, RecipeListFragment.ISelectRecipe callBacks) {
            super(view);
            this.view = view;
            this.callBacks = callBacks;
            // Si on clique sur un élément de la liste, on accède à ses data (RecipeFragment)
            view.setOnClickListener(this);
            recipeNameTextView = view.findViewById(R.id.recipe_name);
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
         * Méthode qui permet d'afficher une recette
         * @param id
         * @param name
         */
        @Override
        public void showRecipe(UUID id,  String name) {
            this.uuid = id;
            recipeNameTextView.setText(name);
            RecipeRepository.getInstance().getRecipe(uuid).observeForever(new Observer<Recipe>() {
                @Override
                public void onChanged(Recipe recipe) {
                    if(recipe.isFavorite()) {
                        ViewHolder.this.favoriteButton.setTag("favorite");
                        ViewHolder.this.favoriteButton.setImageResource(R.drawable.ic_favorite);
                    } else {
                        ViewHolder.this.favoriteButton.setTag("notFavorite");
                        ViewHolder.this.favoriteButton.setImageResource(R.drawable.ic_not_favorite);
                    }
                }
            });
        }

    }
}
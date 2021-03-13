package devmob.rl.reciper.recipeditor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import devmob.rl.reciper.database.EmbeddedObjects.RecipeAndIngredients;
import devmob.rl.reciper.database.EmbeddedObjects.RecipeAndSteps;
import devmob.rl.reciper.database.repository.IRepository;
import devmob.rl.reciper.model.Ingredient;
import devmob.rl.reciper.model.Recipe;
import devmob.rl.reciper.model.Step;
import devmob.rl.reciper.recipeeditor.RecipeEditorPresenter;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RecipeEditorPresenterTest {

    @Test
    public void testConstructeur(){
        MutableLiveData<RecipeAndSteps> liveDataStep = new MutableLiveData<>();
        MutableLiveData<RecipeAndIngredients> liveDataIngredient = new MutableLiveData<>();
        Recipe recipe = mock(Recipe.class);

        IRepository repository = mock(IRepository.class);

        verify(repository, atLeastOnce()).insertRecipe(any(Recipe.class));
        verify(repository, atLeastOnce()).insertStep(any(Step.class));
        verify(repository, atLeastOnce()).insertIngredient(any(Ingredient.class));
        verify(repository, atLeastOnce()).updateRecipe(any(Recipe.class));
        verify(repository, atLeastOnce()).updateElementForRecipe(any(UUID.class), any(List.class), any(List.class));

        //when(repository.getRecipe(any(UUID.class))).thenReturn(recipe);
        when(repository.getStepsByRecipeId(any(UUID.class))).thenReturn(liveDataStep);
        when(repository.getIngredientsByRecipeId(any(UUID.class))).thenReturn(liveDataIngredient);



        /*void insertRecipe(final Recipe recipe);
        void insertStep(final Step step);
        void insertIngredient(final Ingredient ingredient);
        void updateRecipe(final Recipe recipe);
        void updateElementForRecipe(final UUID uuid, final List<Ingredient> listIngredient, final List<Step> listStep);
        LiveData<Recipe> getRecipe(final UUID uuid);
        LiveData<RecipeAndSteps> getStepsByRecipeId(final UUID uuid);
        LiveData<RecipeAndIngredients> getIngredientsByRecipeId(final UUID uuid);*/

        RecipeEditorPresenter presenter = new RecipeEditorPresenter(repository);

        List<Ingredient> listIngredient = new ArrayList<>();
        assertEquals(listIngredient, presenter.getListIngredient());
        assertTrue(presenter.getListIngredient().isEmpty());

        List<Step> listStep = new ArrayList<>();
        assertEquals(listStep, presenter.getListStep());
        assertTrue(presenter.getListStep().isEmpty());

        UUID uuidRecipe = UUID.randomUUID();

        presenter = new RecipeEditorPresenter(repository, uuidRecipe);

        assertEquals(uuidRecipe, presenter.getRecipeUUID());

        assertEquals(listIngredient, presenter.getListIngredient());
        assertTrue(presenter.getListIngredient().isEmpty());

        assertEquals(listStep, presenter.getListStep());
        assertTrue(presenter.getListStep().isEmpty());
    }
}

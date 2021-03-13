package devmob.rl.reciper.recipeditor;

import androidx.lifecycle.MutableLiveData;

import org.junit.Test;

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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
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

        RecipeEditorPresenter presenter = new RecipeEditorPresenter(repository);

        presenter.setInfoFragment("name","facile","cher",5,"description","comment",5,"image");
        assertEquals("name", presenter.getName_recipe());
    }

    @Test
    public void testsetInfo(){
        IRepository repository = mock(IRepository.class);
        RecipeEditorPresenter presenter = new RecipeEditorPresenter(repository);

        presenter.setInfoFragment("name","facile","cher",5,"description","comment",5,"image");
        assertEquals("name", presenter.getName_recipe());
    }

    @Test
    public void testcreateRecipe(){
        IRepository repository = mock(IRepository.class);
        RecipeEditorPresenter presenter = new RecipeEditorPresenter(repository);

        presenter.createRecipe();
        verify(repository, atLeastOnce()).insertRecipe(any(Recipe.class));
        verify(repository, atLeastOnce()).updateElementForRecipe(any(UUID.class), any(List.class), any(List.class));
    }

    @Test
    public void testupdateData(){
        IRepository repository = mock(IRepository.class);
        RecipeEditorPresenter presenter = new RecipeEditorPresenter(repository);

        presenter.updateData();
        verify(repository, atLeastOnce()).updateRecipe(any(Recipe.class));
        verify(repository, atLeastOnce()).updateElementForRecipe(any(UUID.class), any(List.class), any(List.class));
    }

    //Fait mais du a des erreur de git perte de la class 
}

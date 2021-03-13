package devmob.rl.reciper;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DeleteRecipeEspressoTest {

    // IN ORDER TO EXECUTE THIS USER STORY TEST, NO RECIPE HAS TO BE IN THE LIST (THE DATABASE HAS TO BE EMPTY)




    @Test
    public void recipeNotInListAfterDelete() {

        // Initialization of the Main Activity wich displays a list of recipes

        ActivityScenario.launch(MainActivity.class);
        Espresso.onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));

        // After the initialization of the Main Activity, the RecipeListFragment is displayed

        Espresso.onView(withId(R.id.list)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.list)).check(matches(isDisplayed()));

        // When the RecipeListFragment is displayed, the button + is present

        Espresso.onView(withId(R.id.new_recipe)).check(matches(isDisplayed()));

        // When a click on the button + is performed, we navigate to RecipeEditorActivity

        Espresso.onView(withId(R.id.new_recipe)).perform(click());

        // Check if we switched to RecipeEditorActivity

        Espresso.onView(withId(R.id.fragment_editor)).check(matches(isDisplayed()));

        // After the initialization of the Recipe Editor Activity, the RecipeEditorFragment is displayed

        Espresso.onView(withId(R.id.pager)).check(matches(isDisplayed()));
        // When the RecipeEditorFragment is displayed, the textfield for the recipe name is present

        Espresso.onView(withId(R.id.nom_recette)).check(matches(isDisplayed()));
        // When the RecipeEditorFragment is displayed, the validation button is present

        Espresso.onView(withId(R.id.validation_button)).check(matches(isDisplayed()));
        // Insert a text in the Recipe Name Edit Text

        Espresso.onView(withId(R.id.nom_recette)).perform(typeText("Poulet au paprika"));

        // When the creation is validated, we come back to the Main Activity

        Espresso.onView(withId(R.id.validation_button)).perform(click());
        Espresso.onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));

        // After the initialization of the Main Activity, the RecipeListFragment is displayed

        Espresso.onView(withId(R.id.list)).check(matches(isDisplayed()));

        // When the RecipeListFragment is displayed, there is a Recipe Item displayed in the list

        Espresso.onView(withText("Poulet au paprika")).check(matches(isDisplayed()));

        // When a click on the recipe is performed, we navigate to RecipeDisplayerActivity

        Espresso.onView(withText("Poulet au paprika")).perform(click());
        Espresso.onView(withId(R.id.displayer_fragment_container)).check(matches(isDisplayed()));

        // After the initialization of the Recipe Editor Activity, the RecipeDisplayerCollectionFragment is displayed

        Espresso.onView(withId(R.id.view_pager)).check(matches(isDisplayed()));

        // When the RecipeDisplayerCollectionFragment is displayed, the RecipeDisplayerInformationFragment is also displayed

        Espresso.onView(withId(R.id.fragment_recipe_displayer_information)).check(matches(isDisplayed()));

        // Check if the delete button is displayed

        Espresso.onView(withId(R.id.delete_recipe)).check(matches(isDisplayed()));

        // When we click on the delete button the recipe, an alert dialog is displayed in order to ask a confirmation

        Espresso.onView(withId(R.id.delete_recipe)).perform(click());
        Espresso.onView(withText("Supprimer ?")).inRoot(isDialog()).check(matches(isDisplayed()));

        // When the alert dialog is displayed, two buttons are displayed "Oui" and "Non"


        Espresso.onView(withText("Oui")).inRoot(isDialog()).check(matches(isDisplayed()));
        Espresso.onView(withText("Non")).inRoot(isDialog()).check(matches(isDisplayed()));


        // When we click on "Oui", we navigate to the Main Activity

        Espresso.onView(withText("Oui")).inRoot(isDialog()).perform(click());
        Espresso.onView(withId(R.id.fragment_container)).check(matches(isDisplayed()));

        // After the initialization of the Main Activity, the RecipeListFragment is displayed

        Espresso.onView(withId(R.id.list)).check(matches(isDisplayed()));

        // Because we confirmed the delete of the recipe, it is no longer present in the recipes list

        Espresso.onView(withText("Poulet au paprika")).check(doesNotExist());




    }

}

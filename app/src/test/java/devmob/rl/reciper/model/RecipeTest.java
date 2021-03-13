package devmob.rl.reciper.model;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class RecipeTest {

    
    @Test
    public void testSet(){
        UUID uuid = UUID.randomUUID();
        Recipe recipe = new Recipe(uuid, "name", "description", "moyen",
                "cher", 4 , 3, "Comment", 45, "illustration");

        UUID uuidRecipe = recipe.getRecipeId();
        recipe.setRecipeId(UUID.randomUUID());
        recipe.setName("newName");
        recipe.setDescription("newDescription");
        recipe.setDifficulty("facile");
        recipe.setDuration(100);
        recipe.setNumberOfPersons(5);
        recipe.setNote(5);
        recipe.setPrice("peu cher");
        recipe.setComment("newComment");
        recipe.setIllustrationUrl("newIllustration");
        recipe.setFavorite(true);

        assertNotEquals(uuidRecipe, recipe.getRecipeId());
        assertEquals("newName", recipe.getName());
        assertEquals("newDescription", recipe.getDescription());
        assertEquals("facile", recipe.getDifficulty());
        assertEquals(100, recipe.getDuration());
        assertEquals(5, recipe.getNumberOfPersons());
        assertEquals(5, recipe.getNote());
        assertEquals("peu cher", recipe.getPrice());
        assertEquals("newComment", recipe.getComment());
        assertEquals("newIllustration", recipe.getIllustrationUrl());
        assertTrue(recipe.isFavorite());
    }

     

    @Test
    public void testConstructeurSpecial(){
        UUID uuidRecipe = UUID.randomUUID();
        Recipe recipe = new Recipe(uuidRecipe, "name", "description", "moyen",
                "cher", 4 , 3, "Comment", 45, "illustration");

        assertEquals(uuidRecipe, recipe.getRecipeId());
        assertEquals("name", recipe.getName());
        assertEquals("description", recipe.getDescription());
        assertEquals("moyen", recipe.getDifficulty());
        assertEquals(45, recipe.getDuration());
        assertEquals(4, recipe.getNumberOfPersons());
        assertEquals(3, recipe.getNote());
        assertEquals("cher", recipe.getPrice());
        assertEquals("Comment", recipe.getComment());
        assertEquals("illustration", recipe.getIllustrationUrl());
        assertFalse(recipe.isFavorite());
    }

}

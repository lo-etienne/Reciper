package devmob.rl.reciper.model;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class RecipeTest {

    @Test
    public void testGet(){
        Recipe recipe = new Recipe();

        assertEquals("Nom de la recette", recipe.getName());
        assertEquals("Ceci est une description", recipe.getDescription());
        assertEquals("difficile", recipe.getDifficulty());
        assertEquals(300, recipe.getDuration());
        assertEquals(10, recipe.getNumberOfPersons());
        assertEquals(2, recipe.getNote());
        assertEquals("cher", recipe.getPrice());
        assertEquals("Ceci est un commentaire", recipe.getComment());
        assertEquals("", recipe.getIllustrationUrl());
        assertFalse(recipe.isFavorite());
    }

    @Test
    public void testSet(){
        Recipe recipe = new Recipe();

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

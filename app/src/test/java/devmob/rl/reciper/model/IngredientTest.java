package devmob.rl.reciper.model;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class IngredientTest {

    @Test
    public void TestGet(){
        UUID randomUUID = UUID.randomUUID();
        Ingredient ingredient = new Ingredient(randomUUID,"name", "quantity");
        assertTrue(ingredient.getUuid() instanceof UUID);
        assertEquals(randomUUID, ingredient.getRecipeContainerId());
        assertEquals("name", ingredient.getName());
        assertEquals("quantity", ingredient.getQuantity());
    }

    @Test
    public void TestSet(){
        UUID randomUUID = UUID.randomUUID();
        Ingredient ingredient = new Ingredient(randomUUID,"name", "quantity");
        assertTrue(ingredient.getUuid() instanceof UUID);
        assertEquals(randomUUID, ingredient.getRecipeContainerId());
        assertEquals("name", ingredient.getName());
        assertEquals("quantity", ingredient.getQuantity());

        UUID uuidIngredient = ingredient.getUuid();
        ingredient.setRecipeContainerId(UUID.randomUUID());
        ingredient.setUuid(UUID.randomUUID());
        ingredient.setName("newName");
        ingredient.setQuantity("newQuantity");

        assertNotEquals(uuidIngredient, ingredient.getUuid());
        assertNotEquals(randomUUID, ingredient.getRecipeContainerId());
        assertEquals("newName", ingredient.getName());
        assertEquals("newQuantity", ingredient.getQuantity());
    }
}

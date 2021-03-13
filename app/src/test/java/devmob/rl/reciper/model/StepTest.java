package devmob.rl.reciper.model;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class StepTest {

    @Test
    public void TestGet(){
        UUID randomUUID = UUID.randomUUID();
        Step step = new Step(randomUUID,1,"description",10);
        assertTrue(step.getUuid() instanceof UUID);
        assertEquals(randomUUID, step.getRecipeContainerId());
        assertEquals(1, step.getNum());
        assertEquals("description", step.getDescription());
        assertEquals(10, step.getDuration());
    }

    @Test
    public void TestSet(){
        UUID randomUUID = UUID.randomUUID();
        Step step = new Step(randomUUID,1,"description",10);
        assertTrue(step.getUuid() instanceof UUID);
        assertEquals(randomUUID, step.getRecipeContainerId());
        assertEquals(1, step.getNum());
        assertEquals("description", step.getDescription());
        assertEquals(10, step.getDuration());

        UUID uuidIngredient = step.getUuid();
        step.setUuid(UUID.randomUUID());
        step.setRecipeContainerId(UUID.randomUUID());
        step.setNum(2);
        step.setDescription("newDescription");
        step.setDuration(20);

        assertNotEquals(uuidIngredient, step.getUuid());
        assertNotEquals(randomUUID, step.getRecipeContainerId());
        assertEquals(2, step.getNum());
        assertEquals("newDescription", step.getDescription());
        assertEquals(20, step.getDuration());
    }

    @Test
    public void TestCompareTo(){
        Step step = new Step(UUID.randomUUID(),1,"description",10);
        Step step2 = new Step(UUID.randomUUID(),2,"autreDescription",20);

        assertEquals(-1,step.compareTo(step2));
        assertEquals(1,step2.compareTo(step));
        assertEquals(0,step.compareTo(step));
    }
}

package devmob.rl.reciper.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Ingredient {

    @PrimaryKey
    @NonNull
    private UUID uuid;
    private UUID recipeContainerId;
    private String name;
    private String quantity;

    public Ingredient(final UUID recipeContainerId, final String name, final String quantity) {
        uuid = UUID.randomUUID();
        this.recipeContainerId = recipeContainerId;
        this.name = name;
        this.quantity = quantity;
    }

    @NonNull
    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(@NonNull final UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    /*public void setName(String name) {
        this.name = name;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
     */

    public String getQuantity() {
        return quantity;
    }

    public UUID getRecipeContainerId() {
        return recipeContainerId;
    }
}

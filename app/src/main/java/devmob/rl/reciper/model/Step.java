package devmob.rl.reciper.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Step {

    @PrimaryKey
    @NonNull
    private UUID uuid;
    private String description;

    private UUID recipeContainerId;

    public Step(final String description, final UUID recipeContainerId) {
        uuid = UUID.randomUUID();
        this.description = description;
        this.recipeContainerId = recipeContainerId;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(@NonNull final UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getRecipeContainerId() {
        return recipeContainerId;
    }

    public void setRecipeContainerId(UUID recipeContainerId) {
        this.recipeContainerId = recipeContainerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}

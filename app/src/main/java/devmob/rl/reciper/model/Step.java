package devmob.rl.reciper.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class Step implements Comparable<Step>{

    @PrimaryKey
    @NonNull
    private UUID uuid;
    private UUID recipeContainerId;
    private int num;
    private String description;
    private int duration;

    public Step(final UUID recipeContainerId, final int num, final String description, final int duration) {
        uuid = UUID.randomUUID();
        this.recipeContainerId = recipeContainerId;
        this.num = num;
        this.description = description;
        this.duration = duration;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(@NonNull final UUID uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) { this.description = description; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public int getNum() { return num; }
    public void setNum(int num) { this.num = num; }

    public UUID getRecipeContainerId() { return recipeContainerId; }
    public void setRecipeContainerId(UUID recipeContainerId) { this.recipeContainerId = recipeContainerId; }

    @Override
    public int compareTo(Step o) {
        return (this.num - o.getNum());
    }
}

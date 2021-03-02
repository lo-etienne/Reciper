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

    public Step(final String description) {
        uuid = UUID.randomUUID();
        this.description = description;
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

    public void setDescription(final String description) {
        this.description = description;
    }
}

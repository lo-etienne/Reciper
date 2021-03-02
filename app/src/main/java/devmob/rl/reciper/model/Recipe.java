package devmob.rl.reciper.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;
@Entity
public class Recipe {

    @PrimaryKey
    @NonNull
    private UUID id;

    private int position;

    private String name;
    private int numberOfPersons;

    private boolean isFavorite;


    public Recipe(final int position) {
        this.position = position;
        id = UUID.randomUUID();
        name = "Nom temporaire";
        numberOfPersons = 0;
        isFavorite = false;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    public void setId(@NonNull final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(final int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(final boolean favorite) {
        isFavorite = favorite;
    }

    /**
     *
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }
}

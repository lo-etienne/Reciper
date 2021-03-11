package devmob.rl.reciper.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Entity
public class Recipe {

    @PrimaryKey
    @NonNull
    private UUID id;

    private String name;
    private String description;
    private String difficulty;
    private String price;
    private int numberOfPersons;
    private int note;
    private String comment;
    private int duration;//en min

    private String illustrationUrl;

    private boolean isFavorite;

    public Recipe(final String name, final String description, final String difficulty, final String price, final int numberOfPersons, final int note,
                  final String comment, final int duration){
        id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
        this.price = price;
        this.numberOfPersons = numberOfPersons;
        this.note = note;
        this.comment = comment;
        this.duration = duration;
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

    public String getDescription() {
        return description;
    }
    public void setDescription(final String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(final String difficulty) {
        this.difficulty = difficulty;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }
    public void setNumberOfPersons(final int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public int getNote() {
        return note;
    }
    public void setNote(final int note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(final String comment) {
        this.comment = comment;
    }

    public String getIllustrationUrl() {
        return illustrationUrl;
    }
    public void setIllustrationUrl(final String illustrationUrl) { this.illustrationUrl = illustrationUrl; }

    public boolean isFavorite() {
        return isFavorite;
    }
    public void setFavorite(final boolean favorite) {
        isFavorite = favorite;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    public String getPrice() {
        return price;
    }

    public void setDuration(int duration) { this.duration = duration; }
    public int getDuration() { return duration; }
}

package devmob.rl.reciper.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import devmob.rl.reciper.model.Ingredient;
import devmob.rl.reciper.model.Step;

/**
 * La DB ne peut contenir des objets, c'est pourquoi il faut les convertir. Cette classe servira à gerer les différentes conversions nécessaires !
 */
public class ReciperTypeConverters {

    private Gson gson = new Gson();

    /**
     * Permet de convertir un objet UUID en chaine de caractères
     * @param uuid
     * @return
     */
    @TypeConverter
    public String fromUuid(final UUID uuid) {
        return uuid.toString();
    }

    /**
     * Permet de convertir une chaine de caractères en UUID
     * @param uuid
     * @return
     */
    @TypeConverter
    public UUID toUuid(final String uuid) {
        return UUID.fromString(uuid);
    }

    @TypeConverter
    public String fromIngredientsList(final List<Ingredient> ingredients) {
        return gson.toJson(ingredients);
    }

    @TypeConverter
    public List<Ingredient> toIngredientsList(final String ingredients) {
        if(ingredients.isEmpty()) {
            return new ArrayList<>();
        } else {
            Type list = new TypeToken<List<Ingredient>>() {}.getType();
            return gson.fromJson(ingredients, list);
        }
    }

    @TypeConverter
    public String fromStepsList(final List<Step> steps) {
        return gson.toJson(steps);
    }

    @TypeConverter
    public List<Step> toStepsList(final String steps) {
        if(steps.isEmpty()) {
            return new ArrayList<>();
        } else {
            Type list = new TypeToken<List<Step>>() {}.getType();
            return gson.fromJson(steps, list);
        }
    }
}

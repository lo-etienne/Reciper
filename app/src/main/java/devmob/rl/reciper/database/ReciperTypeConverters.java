package devmob.rl.reciper.database;

import androidx.room.TypeConverter;

import java.util.UUID;

/**
 * La DB ne peut contenir des objets, c'est pourquoi il faut les convertir. Cette classe servira à gerer les différentes conversions nécessaires !
 */
public class ReciperTypeConverters {

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

}

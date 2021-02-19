package devmob.rl.reciper.model;

import java.io.IOException;

public class Ingredient {

    private String name;
    private String quantity;

    public Ingredient(final String name, final String quantity) throws IOException {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}

package devmob.rl.reciper.model;

import java.io.IOException;

public class Step {

    private int id;
    private String description;
    private String time;

    public Step(final String description, final String time, final int id) throws IOException {
        this.description = description;
        this.time = time;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(final String time) {
        this.time = time;
    }
}

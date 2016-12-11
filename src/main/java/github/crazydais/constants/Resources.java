package github.crazydais.constants;

/**
 * Created by dave on 10/12/16.
 */
public enum Resources {

    APPLICATION("src/main/resources/application.properties");

    private final String file;

    Resources(String file) {

        this.file = file;
    }

    public String file() {

        return file;
    }


}

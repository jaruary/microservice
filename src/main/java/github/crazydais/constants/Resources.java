package github.crazydais.constants;

public enum Resources {

    APPLICATION("src/main/resources/application.properties");

    private final String file;

    Resources (String file) {

        this.file = file;
    }

    public String file () {

        return file;
    }


}

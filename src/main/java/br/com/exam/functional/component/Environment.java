package br.com.exam.functional.component;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Environment extends Properties {
    private static Environment ENV = null;
    public static Environment getEnviroment() {
        if (ENV == null) {
            ENV = new Environment();
            try {
                ENV.load(new FileReader(System.getProperty("user.dir") + "/src/test/resources/" + System.getProperty("profile") + ".properties"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return ENV;
    }
}

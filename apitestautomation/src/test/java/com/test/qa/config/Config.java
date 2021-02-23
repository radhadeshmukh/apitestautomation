package com.test.qa.config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class Config {

    public Settings init() {

        String environment = System.getenv("TEST_ENV");
        if (environment == null || environment.isEmpty()) {
            System.out.println("Test environment is missing, default set to dev");
            environment = "dev";
        }

        Yaml yaml = new Yaml(new Constructor(Settings.class));
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("config/" + environment + ".yaml");

        return yaml.load(inputStream);
    }
}

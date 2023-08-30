package org.example.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyUtils {

    private static final PropertyUtils INSTANCE = new PropertyUtils();

    private final Properties props;

    private PropertyUtils() {
        props = load();
    }

    public static PropertyUtils getInstance() {
        return INSTANCE;
    }

    private Properties load() {
        Properties newProps = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream("./main.properties");
            newProps.load(inputStream);
            inputStream.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return newProps;
    }

    public String getStringProperty(String key) {
        return  props.getProperty(key);
    }

}

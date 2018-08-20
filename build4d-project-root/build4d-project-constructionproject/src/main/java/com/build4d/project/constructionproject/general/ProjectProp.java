package com.build4d.project.constructionproject.general;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProjectProp {

    private static Properties propertie;
    private static final String filePath = "Project.properties";

    static{
        propertie = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try(InputStream resourceStream = loader.getResourceAsStream(filePath)) {
                propertie.load(resourceStream);
            }
            /*FileInputStream inputFile = new FileInputStream(filePath);
            propertie.load(inputFile);
            inputFile.close();*/
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getValue(String key) {
        if (propertie.containsKey(key)) {
            String value = propertie.getProperty(key);
            return value;
        } else
            return "";
    }
}

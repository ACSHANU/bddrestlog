package Utils;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Duvvuri on 26/12/2018.
 */
public class ReadConfigPropertiesFile {

    static final Logger logger = LogManager.getLogger(ReadConfigPropertiesFile.class.getName());
    private Properties properties;
    private final String propertyFilePath= "src/test/resources/Config/"+ System.getProperty("env") + ".properties";
    public static String scheme;




    public String getProperty(String propName){
        String propValue = properties.getProperty(propName);
        if(propValue!= null) return propValue;
        else throw new RuntimeException( propName + " not specified in the Configuration.properties file.");
    }

    public static void main(String[] arge)
    {
        System.out.println(new ReadConfigPropertiesFile().getProperty("hostname"));
    }
}

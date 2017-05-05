package com.intertec.userlistapp.util;

import java.io.*;
import java.util.Properties;

/**
 * Created by Santiago Lazo on 5/4/17.
 */
public class PropertiesUtil {

    private  Properties properties = new Properties();

    public  PropertiesUtil(){
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            try{
                InputStream resourceStream = loader.getResourceAsStream("config.properties");
                properties.load(resourceStream);
            }catch(Exception e){
                System.out.println("[ERROR]  ->  " + e.getMessage());
            }
    }

    public  Properties getProperties() {
        return properties;
    }
}

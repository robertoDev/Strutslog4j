package it.blackcat.springlog4j;

/**
 * Created by PGB on 29/11/13.
 */
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class AppProperties {

    private static Logger logger = Logger.getLogger(AppProperties.class.getName());

    private Properties config = null;
    public static Boolean IsJUnit = false;

    private String propFile = "app.properties";


    public String getPropFile() {
        return propFile;
    }

    public void setPropFile(String propFile) {
        this.propFile = propFile;

        config = new Properties();
        try {
            logger.debug("trying to load etaplusconfig properties");
            if (IsJUnit){
                config.load(new FileInputStream(propFile));
            }else{
                config.load(new FileInputStream(propFile));
            }
            logger.debug("etaplusconfig properties file loaded successfully");
        } catch (Throwable t) {
            logger.fatal(t.getMessage(), t);
            config = null;
        }


    }

    private AppProperties() {


    }

     private String getProperties(String key) {
        return config.getProperty(key);

     }

    /**
     * ritorna il valore Boolean mappato sul file di configurazione corrispondente alla chiave
     * @param key chiave della proprietà
     * @param defaultValue valore restituito nel caso in cui la proprietà non è stata trovata nel file di configurazione
     * @return il valore mappato o defaultValue se la proprietà non è stata trovata
     */
    public Boolean getBooleanValue(String key) {
        String val = getProperties(key);
        if(val != null) {
            return Boolean.valueOf(val.trim());
        }
        return null;
    }

    /**
     * ritorna il valore Boolean mappato sul file di configurazione corrispondente alla chiave
     * @param key chiave della proprietà
     * @param defaultValue valore restituito nel caso in cui la proprietà non è stata trovata nel file di configurazione
     * @return il valore mappato o defaultValue se la proprietà non è stata trovata
     */
    public Boolean getBooleanValue(String key, Boolean defaultValue) {
        if(!config.containsKey(key)) {
            return defaultValue;
        }
        return getBooleanValue(key);
    }

    /**
     * ritorna il valore String mappato sul file di configurazione corrispondente alla chiave
     * @param key chiave della proprietà
     * @return il valore mappato o null se la proprietà non è stata trovata
     */
    public String getStringValue(String key) {
        return getProperties(key);
    }

    /**
     * ritorna il valore String mappato sul file di configurazione corrispondente alla chiave
     * @param key chiave della proprietà
     * @param defaultValue valore restituito nel caso in cui la proprietà non è stata trovata nel file di configurazione
     * @return il valore mappato o defaultValue se la proprietà non è stata trovata
     */
    public  String getStringValue(String key, String defaultValue) {
        if(!config.containsKey(key)) {
            return defaultValue;
        }
        return getStringValue(key);
    }

    /**
     * ritorna il valore Integer mappato sul file di configurazione corrispondente alla chiave
     * @param key chiave della proprietà
     * @return il valore mappato o null se la proprietà non è stata trovata
     */
    public  Integer getIntegerValue(String key) throws NumberFormatException {
        String val =getProperties(key);
        if(val != null) {
            return Integer.parseInt(val.trim());
        }
        return null;
    }

    /**
     * ritorna il valore Integer mappato sul file di configurazione corrispondente alla chiave
     * @param key chiave della proprietà
     * @param defaultValue valore restituito nel caso in cui la proprietà non è stata trovata nel file di configurazione
     * @return il valore mappato o defaultValue se la proprietà non è stata trovata
     */
    public  Integer getIntegerValue(String key, Integer defaultValue) throws NumberFormatException {
        if(!config.containsKey(key)) {
            return defaultValue;
        }
        return getIntegerValue(key);
    }

    /**
     * ritorna il valore Long mappato sul file di configurazione corrispondente alla chiave
     * @param key chiave della proprietà
     * @return il valore mappato o null se la proprietà non è stata trovata
     */
    public Long getLongValue(String key) throws NumberFormatException {
        String val = getProperties(key);
        if(val != null) {
            return Long.parseLong(val.trim());
        }
        return null;
    }

    /**
     * ritorna il valore Long mappato sul file di configurazione corrispondente alla chiave
     * @param key chiave della proprietà
     * @param defaultValue valore restituito nel caso in cui la proprietà non è stata trovata nel file di configurazione
     * @return il valore mappato o defaultValue se la proprietà non è stata trovata
     */
    public Long getLongValue(String key, Long defaultValue) throws NumberFormatException {
        if(!config.containsKey(key)) {
            return defaultValue;
        }
        return getLongValue(key);
    }

    /**
     * ritorna il valore Float mappato sul file di configurazione corrispondente alla chiave
     * @param key chiave della proprietà
     * @return il valore mappato o null se la proprietà non è stata trovata
     */
    public Float getFloatValue(String key) throws NumberFormatException {
        String val =getProperties(key);
        if(val != null) {
            return Float.parseFloat(val);
        }
        return null;
    }

    /**
     * ritorna il valore Float mappato sul file di configurazione corrispondente alla chiave
     * @param key chiave della proprietà
     * @param defaultValue valore restituito nel caso in cui la proprietà non è stata trovata nel file di configurazione
     * @return il valore mappato o defaultValue se la proprietà non è stata trovata
     */
    public Float getFloatValue(String key, Float defaultValue) throws NumberFormatException {
        if(!config.containsKey(key)) {
            return defaultValue;
        }
        return getFloatValue(key);
    }

    /**
     * ritorna il valore Double mappato sul file di configurazione corrispondente alla chiave
     * @param key chiave della proprietà
     * @return il valore mappato o null se la proprietà non è stata trovata
     */
    public Double getDoubleValue(String key) throws NumberFormatException {
        String val = getProperties(key);
        if(val != null) {
            return Double.parseDouble(val.trim());
        }
        return null;
    }

    /**
     * ritorna il valore Double mappato sul file di configurazione corrispondente alla chiave
     * @param key chiave della proprietà
     * @param defaultValue valore restituito nel caso in cui la proprietà non è stata trovata nel file di configurazione
     * @return il valore mappato o defaultValue se la proprietà non è stata trovata
     */
    public  Double getDoubleValue(String key, Double defaultValue) throws NumberFormatException {
        if(config.containsKey(key)) {
            return defaultValue;
        }
        return getDoubleValue(key);
    }

    public List reload() {

        Properties oldConfig = new Properties(config);
        try {
            config.clear();
            logger.debug("trying to reload properties");
            config.load(new FileInputStream(propFile));
            logger.debug(" properties file loaded successfully");

            return getConfiguration();
        } catch (Throwable t) {
            logger.fatal(t.getMessage(), t);
            config = new Properties(oldConfig);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List getConfiguration() {

        List conf = null;

        Properties props = config;
        if(props != null) {
            conf = new ArrayList(props.size());
            Enumeration<String> keys = (Enumeration<String>)props.propertyNames();
            String key = null;
            while(keys.hasMoreElements()) {
                key = keys.nextElement();
                conf.add( props.getProperty(key));
            }
        }

        return conf;
    }
}
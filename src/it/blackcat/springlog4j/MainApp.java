package it.blackcat.springlog4j;

/**
 * Created with IntelliJ IDEA.
 * User: PGB
 * Date: 29/11/13
 * Time: 9.52
 * To change this template use File | Settings | File Templates.
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.apache.log4j.Logger;

public class MainApp {

    static Logger log = Logger.getLogger(MainApp.class.getName());

    public static void main(String[] args) {
        ApplicationContext mainCtx = new ClassPathXmlApplicationContext("beans.xml");
        ApplicationContext propCtx =  new ClassPathXmlApplicationContext("properties.xml");

// hello world :

        log.info("Going to create HelloWord Obj");
        HelloWorld obj = (HelloWorld) mainCtx.getBean("helloWorld");
        log.info("Hello world message:"+ obj.getMessage());


// get properties from spring bean:

        log.info("Getting property from spring xml:");
        SpringProperties sp = (SpringProperties) propCtx.getBean("springProperties");
        log.info("ExportPath: " + sp.getExportPath());


// get properties from properties file:
        log.info("Getting property from properties file:");
        AppProperties appProperties = (AppProperties) mainCtx.getBean("appProperties");
        log.info("country: "+appProperties.getStringValue("country"));

        log.info("Exiting the program");


    }
}
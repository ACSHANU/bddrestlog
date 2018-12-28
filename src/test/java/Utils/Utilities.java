package Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stepdefs.ServiceHooks;

/**
 * Created by Duvvuri on 23/12/2018.
 */
public class Utilities {

    static ReadConfigPropertiesFile readConfigPropertiesFile;
    public static EnvironmentProperties envProps;
    static final Logger logger = LogManager.getLogger(Utilities.class.getName());
    public static void writeHTML(RestEvidence oResEvi)
    {
        writeHTML(oResEvi.request,oResEvi.response,oResEvi.getUrl(), oResEvi.resHeaders,oResEvi.getStatusCode(),oResEvi.getReqHeaders());

    }

    public static void writeHTML(String req, String res , String url, String resHeaders, String status , String reqHeaders)
    {
        StringBuilder str= new StringBuilder();
        str.append("<Table border=1 width=100%>");
        str.append("<TR>");
        str.append("<TD>");
        str.append("Status Code");
        str.append("</TD>");
        str.append("<TD>");
        str.append(status);
        str.append("</TD>");
        str.append("</TR>");
        str.append("<TR>");
        str.append("<TD>");
        str.append("URL");
        str.append("</TD>");
        str.append("<TD>");
        str.append(url);
        str.append("</TD>");
        str.append("</TR>");
        str.append("<TR>");
        str.append("<TD>");
        str.append("Request");
        str.append("</TD>");
        str.append("<TD>");
        str.append(req);
        str.append("</TD>");
        str.append("</TR>");
        str.append("<TR>");
        str.append("<TD>");
        str.append("Response");
        str.append("</TD>");
        str.append("<TD>");
        str.append(res);
        str.append("</TD>");
        str.append("</TR>");
        str.append("<TR>");
        str.append("<TD>");
        str.append("Response Headers");
        str.append("</TD>");
        str.append("<TD>");
        str.append(resHeaders);
        str.append("</TD>");
        str.append("</TR>");

        str.append("<TR>");
        str.append("<TD>");
        str.append("Request Headers");
        str.append("</TD>");
        str.append("<TD>");
        str.append(reqHeaders);
        str.append("</TD>");
        str.append("</TR>");
        str.append("</Table>");

        ServiceHooks.scenario.embed(str.toString().getBytes(),"text/html");
    }

    public static void InitializeProps()
    {
        if(readConfigPropertiesFile==null) {
            readConfigPropertiesFile = new ReadConfigPropertiesFile();
            envProps = new EnvironmentProperties();
            envProps.setHostnme(readConfigPropertiesFile.getProperty("hostname"));
            envProps.setScheme(readConfigPropertiesFile.getProperty("scheme"));
            envProps.setPortnumber(readConfigPropertiesFile.getProperty("portnumber"));
            envProps.setPath(readConfigPropertiesFile.getProperty("path"));
            logger.info("Hostname:" + envProps.getHostnme());
            logger.info("Scheme:" + envProps.getScheme());
            logger.info("Portnumber:" + envProps.getPortnumber());
            logger.info("Path:" + envProps.getPath());




        }


    }
}

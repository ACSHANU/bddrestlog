package trybeforeapply;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import POJO.GistsPublic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import stepdefs.StepDefinitions;

/**
 * Created by Duvvuri on 25/12/2018.
 */
public class Sample {
    static final Logger logger = LogManager.getLogger(Sample.class.getName());
    public static void main(String[] args) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, true);

        List<GistsPublic> listCar = objectMapper.readValue(new File("src/test/resources/JSON/GistsPublic.json"), new TypeReference<List<GistsPublic>>(){});
        for ( GistsPublic opub : listCar) {
            /*logger.info(opub.getForksUrl());
            logger.info(opub.getOwner().getAvatarUrl());*/
            //logger.info(opub.isTruncated());
            logger.info(objectMapper.writeValueAsString(opub.getOwner()));
        }
      logger.info(listCar.size());
    }

}

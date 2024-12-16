package com.drjautosel.util;

import com.drjautosel.tests.vendorportal.model.VendorPortalTestData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class JasonUtil {
    private static final Logger log = LoggerFactory.getLogger(JasonUtil.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T>T getTestData(String path, Class<T> type) throws IOException {

        try(InputStream stream = ResourceLoader.getResource(path)){
            return  mapper.readValue(stream, type);
        }catch (Exception e){
            log.error("unable to read test data {}", path,e);
        }
            return null;
    }
}

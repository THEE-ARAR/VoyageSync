package edu.famu.voyage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.cloud.firestore.GeoPoint;

public class CustomObjectMapper extends ObjectMapper {
    public CustomObjectMapper() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(GeoPoint.class, new GeoPointDeserializer());
        registerModule(module);
    }
}

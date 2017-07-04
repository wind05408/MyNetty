package com.dk.common.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA
 * FasterJsonTool
 *
 * @author dk
 * @date 2017/7/4 17:44
 */
public class FasterJsonTool {

    private static final ObjectMapper OBJECT_MAPPER = createObjectMapper();

    public static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new AfterburnerModule());
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return objectMapper;
    }

    public static String writeListValueAsString(Collection<? extends Jsonable> jsonableList) {
        List<Map<String, Object>> list = new LinkedList<>();
        for(Jsonable jsonable : jsonableList) {
            list.add(jsonable.getMap4Json());
        }
        return writeValueAsString(list);
    }

    public static String writeValueAsString(Jsonable jsonable) {
        return writeValueAsString(jsonable.getMap4Json());
    }

    /**
     * JSON序列化
     */
    public static String writeValueAsString(Object obj) {
        if(obj instanceof Jsonable) {
            return writeValueAsString((Jsonable) obj);
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch(IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    /**
     * JSON反序列化
     */
    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch(IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    /**
     * JSON反序列化
     */
    public static ArrayList<Map<String, Object>> readValue2List(String json) {
        ArrayList<Map<String, Object>> list;
        try {
            list = OBJECT_MAPPER.readValue(json, TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, Map.class));
        } catch(IOException ex) {
            throw new IllegalArgumentException(ex);
        }
        return list;
    }

    /**
     * JSON反序列化
     */
    public static <T> ArrayList<T> readValue2List(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, clazz));
        } catch(IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    /**
     * JSON反序列化
     */
    public static <T> T readValue(String json, TypeReference<T> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch(IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    /**
     * JSON反序列化
     */
    public static <T> ArrayList<T> readValue2List(String json, TypeReference<List<T>> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch(IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public static <K, V> Map<K, V> readValue2Map(String json, Class<K> keyClazz, Class<V> valueClazz) {
        try {
            return OBJECT_MAPPER.readValue(json, TypeFactory.defaultInstance().constructMapType(Map.class, keyClazz, valueClazz));
        } catch(IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public static <T, K> T readValue(String json, Class<T> clazz, Class<K> parameterClasses) {
        try {
            return OBJECT_MAPPER.readValue(json, TypeFactory.defaultInstance().constructParametrizedType(clazz, clazz, parameterClasses));
        } catch(IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public static <T, K, V> T readValue(String json, Class<T> clazz, Class<K> parametersFor, Class<V> parameterClasses) {
        try {
            return OBJECT_MAPPER.readValue(json, TypeFactory.defaultInstance().constructParametrizedType(clazz, parametersFor, parameterClasses));
        } catch(IOException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }

}

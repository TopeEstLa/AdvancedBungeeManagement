package me.topeestla.abm.utils;

/*
 *  * @Created on 2021 - 13:13
 *  * @Author Jimmy / vSKAH
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public interface IDataSerialisables<T> {

    default T load(File file, Class<T> tClass) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(file, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    default T convertValue(Object object, TypeReference<T> typeReference) {
        return new ObjectMapper().convertValue(object, typeReference);
    }

    default void save(File file, Object object) {
        try {
            new ObjectMapper().writeValue(file, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
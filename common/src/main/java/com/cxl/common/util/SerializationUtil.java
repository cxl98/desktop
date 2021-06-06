package com.cxl.common.util;

import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cxl
 */
public class SerializationUtil {
    private static Map<Class<?>, Schema<?>> cached=new ConcurrentHashMap<>();
    private static Objenesis objenesis=new ObjenesisStd();
    private static <T> Schema<T> getSchema(Class<T> tClass){
        Schema<T> schema= (Schema<T>) cached.get(tClass);
        if (null==schema){
            schema= RuntimeSchema.createFrom(tClass);
        }
        return schema;
    }
}

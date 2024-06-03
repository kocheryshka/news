package com.example.news.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;

@UtilityClass
public class BeanUtils {

    @SneakyThrows
    public static void copyNonNullProperties(Object source, Object target){
        Class<?> clazz = source.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field :fields){
            field.setAccessible(true);
            Object value = field.get(source);
            System.out.println(field.getName());
            if (value != null){
                field.set(target, value);
            }

        }
    }

}

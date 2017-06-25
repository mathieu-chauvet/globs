package org.globsframework.metamodel.utils;

import org.globsframework.metamodel.Field;
import org.globsframework.model.Glob;
import org.globsframework.model.Key;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface Annotations {
   static Annotations EMPTY = new DefaultAnnotations();

    boolean hasAnnotation(Key key);

    Glob getAnnotation(Key key);

    Glob findAnnotation(Key key);

    Collection<Glob> list();

   default <T> T getValueOrDefault(Key key, Field field, T defaultValue){
      Glob annotation = findAnnotation(key);
      if (annotation != null){
         return (T)annotation.getValue(field);
      }
      return defaultValue;
   }
}

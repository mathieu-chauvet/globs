package org.globsframework.metamodel.utils;

import org.globsframework.utils.collections.MultiMap;

import java.lang.reflect.Field;
import java.util.List;

public class FieldProcessorServiceImpl implements FieldProcessorService {
   private MultiMap<Class, FieldProcessor> fieldProcessor = new MultiMap<>();
   public List<FieldProcessor> get(Field field) {
      return fieldProcessor.get(field.getType());
   }

   public <T> void add(Class<T> type, FieldProcessor<T> processor){
      fieldProcessor.put(type, processor);
   }
}

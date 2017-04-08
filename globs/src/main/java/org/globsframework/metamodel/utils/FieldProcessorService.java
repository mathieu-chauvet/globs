package org.globsframework.metamodel.utils;

import java.lang.reflect.Field;
import java.util.List;

public interface FieldProcessorService {
   List<FieldProcessor> get(Field field);
}

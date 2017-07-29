package org.globsframework.model;

import org.globsframework.metamodel.Field;

public interface MutableGlob extends Glob, FieldSetter<MutableGlob> {
   MutableGlob setValue(Field field, Object value);
}

package org.globsframework.metamodel.utils;

import org.globsframework.metamodel.GlobType;

import java.lang.annotation.Annotation;

public interface FieldProcessor<T> {
   T getValue(GlobType type, Annotations annotations, Annotation[] nativeAnnotations);
}

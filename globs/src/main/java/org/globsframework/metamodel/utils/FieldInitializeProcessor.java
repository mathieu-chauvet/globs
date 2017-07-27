package org.globsframework.metamodel.utils;

import org.globsframework.metamodel.GlobType;

import java.lang.annotation.Annotation;

public interface FieldInitializeProcessor<T> {
   T getValue(GlobType type, Annotations annotations, Annotation[] nativeAnnotations);
}

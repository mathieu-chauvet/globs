package org.globsframework.metamodel;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.utils.Annotations;

import java.lang.annotation.Annotation;

public interface FieldInitializeProcessor<T> {
   T getValue(GlobType type, Annotations annotations, Annotation[] nativeAnnotations);
}

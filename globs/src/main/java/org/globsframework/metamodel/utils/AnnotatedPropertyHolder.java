package org.globsframework.metamodel.utils;

import org.globsframework.metamodel.Annotations;
import org.globsframework.metamodel.impl.DefaultAnnotations;
import org.globsframework.model.Glob;
import org.globsframework.model.Key;

import java.util.Collection;

public abstract class AnnotatedPropertyHolder<T> extends DefaultPropertyHolder<T> implements Annotations {
  private Annotations annotations = new DefaultAnnotations();

  public boolean hasAnnotation(Key annotation) {
    return annotations.hasAnnotation(annotation);
  }

  public Glob getAnnotation(Key key) {
    return annotations.getAnnotation(key);
  }

  public Collection<Glob> list() {
    return annotations.list();
  }

  public Glob findAnnotation(Key key) {
    return annotations.findAnnotation(key);
  }
}

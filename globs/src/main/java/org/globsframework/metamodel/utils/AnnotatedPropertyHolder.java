package org.globsframework.metamodel.utils;

import org.globsframework.model.Glob;
import org.globsframework.model.Key;
import org.globsframework.model.format.GlobPrinter;
import org.globsframework.utils.exceptions.ItemNotFound;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

public abstract class AnnotatedPropertyHolder<T> extends DefaultPropertyHolder<T> implements Annotations {
  private Annotations annotations;

  public AnnotatedPropertyHolder(Annotations annotations) {
    this.annotations = annotations;
  }

  public boolean hasAnnotation(Key annotation) {
    return annotations.hasAnnotation(annotation);
  }

  public Glob getAnnotation(Key key) {
    return annotations.getAnnotation(key);
  }

  public List<Glob> list() {
    return annotations.list();
  }

  public Glob findAnnotation(Key key) {
    return annotations.findAnnotation(key);
  }
}

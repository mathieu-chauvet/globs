package org.globsframework.metamodel.impl;

import org.globsframework.metamodel.Annotations;
import org.globsframework.metamodel.utils.MutableAnnotations;
import org.globsframework.model.Glob;
import org.globsframework.model.Key;
import org.globsframework.utils.exceptions.ItemNotFound;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DefaultAnnotations implements MutableAnnotations {
    Map<Key, Glob> annotations = new HashMap<Key, Glob>();

   public DefaultAnnotations() {
   }

   public DefaultAnnotations(Glob[] annotations) {
      for (Glob annotation : annotations) {
         addAnnotation(annotation);
      }
   }

   public DefaultAnnotations(Annotations annotations) {
      for (Glob annotation : annotations.list()) {
         this.annotations.put(annotation.getKey(), annotation);
      }
   }

   public MutableAnnotations addAnnotation(Glob glob) {
       if (glob != null) {
          annotations.put(glob.getKey(), glob);
       }
      return this;
    }

    public boolean hasAnnotation(Key key) {
        return annotations.containsKey(key);
    }

    public Glob getAnnotation(Key key) {
      Glob annotation = annotations.get(key);
      if (annotation == null) {
        throw new ItemNotFound(key == null ? "null" : key.toString());
      }
      return annotation;
    }

    public Glob findAnnotation(Key key) {
        return annotations.get(key);
    }

    public Collection<Glob> list() {
        return annotations.values();
    }
}

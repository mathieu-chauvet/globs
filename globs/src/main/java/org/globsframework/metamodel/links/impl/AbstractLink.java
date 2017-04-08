package org.globsframework.metamodel.links.impl;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.links.Link;
import org.globsframework.metamodel.utils.Annotations;
import org.globsframework.metamodel.utils.DefaultAnnotations;

public abstract class AbstractLink extends DefaultAnnotations implements Link {
   protected final String modelName;
   protected final String name;

   public AbstractLink(String modelName, String name, Annotations annotations) {
      super(annotations);
      this.modelName = modelName;
      this.name = name;
   }

   public String getLinkModelName() {
       return modelName;
   }

   public String getName() {
       return name;
   }

   public String toString() {
      return toString(name, getSourceType(), getTargetType());
   }

   public static String toString(String name, GlobType sourceType, GlobType targetType) {
      return name + "[" + sourceType.getName() + " => " +
             targetType.getName() + "]";
   }

}

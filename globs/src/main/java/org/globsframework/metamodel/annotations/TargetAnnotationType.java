package org.globsframework.metamodel.annotations;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.model.Glob;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

public class TargetAnnotationType {
  public static GlobType TYPE;

  public static Glob create(Target target){
    return TYPE.instantiate();
  }

  static {
    GlobTypeLoader.init("globs", TargetAnnotationType.class, "targetAnnotation");
  }

}

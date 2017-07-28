package org.globsframework.metamodel.annotations;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.GlobTypeLoaderFactory;
import org.globsframework.model.Glob;

public class TargetAnnotationType {
  public static GlobType TYPE;

  public static Glob create(Target target){
    return TYPE.instantiate();
  }

  static {
     GlobTypeLoaderFactory.create(TargetAnnotationType.class, "globs", "targetAnnotation");
  }

}

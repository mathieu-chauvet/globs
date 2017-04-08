package org.globsframework.metamodel.annotations;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.metamodel.utils.GlobTypeLoaderFactory;
import org.globsframework.model.Glob;

public class NoObfuscationAnnotationType {
  public static GlobType TYPE;

  public static Glob create(NoObfuscation target){
    return TYPE.instantiate();
  }

  static {
     GlobTypeLoaderFactory.create(NoObfuscationAnnotationType.class, "globs", "noObfuscationAnnotation");
  }

}

package org.globsframework.metamodel.annotations;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.model.Glob;
import org.globsframework.model.Key;

public class RequiredAnnotationType {
  public static GlobType TYPE;

  @InitUniqueKey
  public static Key UNIQUE_KEY;

  public static Glob create(Required target){
    return TYPE.instantiate();
  }

  static {
    GlobTypeLoader.init("globs", RequiredAnnotationType.class, "requiredAnnotation");
  }

}

package org.globsframework.sqlstreams.annotations;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.annotations.GlobCreateFromAnnotation;
import org.globsframework.metamodel.annotations.InitUniqueGlob;
import org.globsframework.metamodel.annotations.InitUniqueKey;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.metamodel.utils.GlobTypeLoaderFactory;
import org.globsframework.model.Glob;
import org.globsframework.model.Key;

public class AutoIncrementAnnotationType {
    public static GlobType TYPE;

   @InitUniqueKey
   public static Key KEY;

   @InitUniqueGlob
   public static Glob INSTANCE;

    static {
       GlobTypeLoaderFactory.create(AutoIncrementAnnotationType.class)
          .register(GlobCreateFromAnnotation.class, annotation -> INSTANCE)
          .load();
    }
}

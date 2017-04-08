package org.globsframework.metamodel.annotations;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.fields.BooleanField;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.metamodel.utils.GlobTypeLoaderFactory;
import org.globsframework.model.Glob;
import org.globsframework.model.Key;

public class ContainmentLinkAnnotationType {
    public static GlobType DESC;

   @InitUniqueKey
   public static Key UNIQUE_KEY;

   @InitUniqueGlob
   public static Glob UNIQUE_GLOB;

    static {
       GlobTypeLoader loader = GlobTypeLoaderFactory.create(ContainmentLinkAnnotationType.class);
       loader.register(GlobCreateFromAnnotation.class, annotation -> UNIQUE_GLOB)
       .load();
    }
}

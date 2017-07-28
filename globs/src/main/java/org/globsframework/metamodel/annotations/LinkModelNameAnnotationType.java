package org.globsframework.metamodel.annotations;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.fields.StringField;
import org.globsframework.metamodel.GlobTypeLoaderFactory;
import org.globsframework.model.Glob;
import org.globsframework.model.Key;

public class LinkModelNameAnnotationType {
   public static GlobType TYPE;

   public static StringField NAME;

   @InitUniqueKey
   public static Key UNIQUE_KEY;

   @InitUniqueGlob
   public static Glob UNIQUE_GLOB;

   static {
      GlobTypeLoaderFactory.create(LinkModelNameAnnotationType.class)
         .register(GlobCreateFromAnnotation.class, annotation -> UNIQUE_GLOB)
      .load();
   }
}

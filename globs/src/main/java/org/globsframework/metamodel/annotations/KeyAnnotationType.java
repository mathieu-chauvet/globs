package org.globsframework.metamodel.annotations;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.fields.IntegerField;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.metamodel.utils.GlobTypeLoaderFactory;
import org.globsframework.model.Glob;
import org.globsframework.model.Key;

public class KeyAnnotationType {
   public static GlobType TYPE;

   public static IntegerField INDEX;

   @InitUniqueKey
   public static Key UNIQUE_KEY;

   @KeyIndex(1)
   public static Glob ONE;

   @KeyIndex(2)
   public static Glob TWO;

   @KeyIndex(3)
   public static Glob THREE;

   @KeyIndex(4)
   public static Glob FOUR;

   public static Glob create(int count) {
      switch (count) {
         case 1:
            return ONE;
         case 2:
            return TWO;
         case 3:
            return THREE;
         case 4:
            return FOUR;
         default:
            return TYPE.instantiate().set(INDEX, count);
      }
   }

   static {
      GlobTypeLoaderFactory.create(KeyAnnotationType.class)
      .load();
   }
}

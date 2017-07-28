package org.globsframework.metamodel.annotations;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.fields.IntegerField;
import org.globsframework.metamodel.impl.DefaultFieldLoaderFactory;
import org.globsframework.metamodel.impl.DefaultGlobType;
import org.globsframework.model.Glob;
import org.globsframework.model.Key;
import org.globsframework.model.KeyBuilder;

public class KeyAnnotationType {
   public static GlobType TYPE;

   public static IntegerField INDEX;

   @InitUniqueKey
   public static Key UNIQUE_KEY;

   @KeyIndex(-1)
   public static Glob UNINITIALIZED;

   @KeyIndex(1)
   public static Glob ONE;

   @KeyIndex(2)
   public static Glob TWO;

   @KeyIndex(3)
   public static Glob THREE;

   @KeyIndex(4)
   public static Glob FOUR;

   public static Glob create(int indexOfKeyField) {
      switch (indexOfKeyField) {
         case 1:
            return ONE;
         case 2:
            return TWO;
         case 3:
            return THREE;
         case 4:
            return FOUR;
         default:
            return TYPE.instantiate().set(INDEX, indexOfKeyField);
      }
   }

   static {
      DefaultGlobType globType = new DefaultGlobType("KeyAnnotation");
      DefaultFieldLoaderFactory factory = new DefaultFieldLoaderFactory(globType);
      TYPE = globType;
      INDEX = factory.addInteger("INDEX", false, 0, 0, null);
      UNIQUE_KEY = KeyBuilder.newEmptyKey(TYPE);
      UNINITIALIZED = globType.instantiate().set(INDEX, -1);
      ONE = globType.instantiate().set(INDEX, 1);
      TWO = globType.instantiate().set(INDEX, 2);
      THREE = globType.instantiate().set(INDEX, 3);
      FOUR = globType.instantiate().set(INDEX, 4);
   }
}

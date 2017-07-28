package org.globsframework.metamodel;

import org.globsframework.metamodel.annotations.KeyAnnotationType;
import org.globsframework.metamodel.fields.*;
import org.globsframework.model.Glob;

public interface GlobTypeBuilder {
   GlobTypeBuilder addStringField(String fieldName, Glob... annotations);

   GlobTypeBuilder addIntegerField(String fieldName, Glob... annotations);

   GlobTypeBuilder addDoubleField(String fieldName, Glob... annotations);

   GlobTypeBuilder addLongField(String fieldName, Glob... annotations);

   GlobTypeBuilder addBooleanField(String fieldName, Glob... annotations);

   GlobTypeBuilder addBlobField(String fieldName, Glob... annotations);

   StringField declareStringField(String fieldName, Glob... annotations);

   IntegerField declareIntegerField(String fieldName, Glob... annotations);

   BooleanField declareBooleanField(String fieldName, Glob... annotations);

   DoubleField declareDoubleField(String fieldName, Glob... annotations);

   LongField declareLongField(String fieldName, Glob... annotations);

   BlobField declareBlobField(String fieldName, Glob... annotations);

   <T> void register(Class<T> klass, T t);

   GlobType get();

   default GlobTypeBuilder addIntegerKey(String fieldName) {
      addIntegerField(fieldName, KeyAnnotationType.UNINITIALIZED);
      return this;
   }
}

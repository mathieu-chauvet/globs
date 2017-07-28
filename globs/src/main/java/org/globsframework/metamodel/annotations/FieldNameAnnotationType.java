package org.globsframework.metamodel.annotations;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.fields.StringField;
import org.globsframework.metamodel.fields.impl.DefaultStringField;
import org.globsframework.metamodel.impl.DefaultFieldLoaderFactory;
import org.globsframework.metamodel.impl.DefaultGlobType;
import org.globsframework.model.Glob;
import org.globsframework.model.Key;
import org.globsframework.model.KeyBuilder;
import org.globsframework.model.MutableGlob;

public class FieldNameAnnotationType {
   public static GlobType DESC;

   public static StringField NAME;

   public static Key UNIQUE_KEY;

   public static Glob create(FieldNameAnnotation nameAnnotation) {
      return create(nameAnnotation.value());
   }

   public static MutableGlob create(String value) {
      return DESC.instantiate().set(NAME, value);
   }

   static {
      DefaultGlobType globType = new DefaultGlobType("fieldNameAnnotation");
      DefaultFieldLoaderFactory factory = new DefaultFieldLoaderFactory(globType);
      DESC = globType;
      DefaultStringField defaultStringField = factory.addString("name", false, 0, 0, null);
      NAME = defaultStringField;
      UNIQUE_KEY = KeyBuilder.newEmptyKey(DESC);
      defaultStringField.addAnnotation(create("name"));
      globType.register(GlobCreateFromAnnotation.class, annotation -> create((FieldNameAnnotation)annotation));
   }
}

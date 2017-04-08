package org.globsframework.metamodel;

import org.globsframework.metamodel.annotations.KeyField;
import org.globsframework.metamodel.annotations.MaxSize;
import org.globsframework.metamodel.annotations.NamingField;
import org.globsframework.metamodel.fields.IntegerField;
import org.globsframework.metamodel.fields.StringField;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.metamodel.utils.GlobTypeLoaderFactory;

public class DummyObjectWithMaxSizeString {
  public static GlobType TYPE;

  @KeyField
  public static IntegerField ID;

  @NamingField
  @MaxSize(10)
  public static StringField TEXT;

  static {
     GlobTypeLoaderFactory.create(DummyObjectWithMaxSizeString.class).load();
  }
}

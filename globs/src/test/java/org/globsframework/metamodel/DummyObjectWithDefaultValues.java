package org.globsframework.metamodel;

import org.globsframework.metamodel.annotations.*;
import org.globsframework.metamodel.fields.*;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.metamodel.utils.GlobTypeLoaderFactory;

public class DummyObjectWithDefaultValues {
  public static GlobType TYPE;

  @KeyField
  @AutoIncrement
  public static IntegerField ID;

  @DefaultInteger(7)
  public static IntegerField INTEGER;

  @DefaultLong(5l)
  public static LongField LONG;

  @DefaultDouble(3.14159265)
  public static DoubleField DOUBLE;

  @DefaultBoolean(true)
  public static BooleanField BOOLEAN;

  @Target(DummyObject.class)
  public static IntegerField LINK;

  @DefaultString("Hello")
  public static StringField STRING;

  static {
     GlobTypeLoaderFactory.create(DummyObjectWithDefaultValues.class).load();
  }
}

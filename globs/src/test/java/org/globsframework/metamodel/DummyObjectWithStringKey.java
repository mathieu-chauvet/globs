package org.globsframework.metamodel;

import org.globsframework.metamodel.annotations.KeyField;
import org.globsframework.metamodel.fields.StringField;
import org.globsframework.metamodel.utils.GlobTypeLoaderFactory;

public class DummyObjectWithStringKey {
  public static GlobType TYPE;

  @KeyField
  public static StringField ID;

  static {
     GlobTypeLoaderFactory.create(DummyObjectWithStringKey.class).load();
  }
}

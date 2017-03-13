package org.globsframework.metamodel;

import org.globsframework.metamodel.annotations.KeyField;
import org.globsframework.metamodel.annotations.NamingField;
import org.globsframework.metamodel.fields.IntegerField;
import org.globsframework.metamodel.fields.StringField;
import org.globsframework.metamodel.utils.GlobTypeLoader;

public class DummyObjectWithTripleKey {

  public static GlobType TYPE;

  @KeyField
  public static IntegerField ID1;
  @KeyField
  public static IntegerField ID2;
  @KeyField
  public static IntegerField ID3;

  @NamingField
  public static StringField NAME;

  static {
    GlobTypeLoader.init(DummyObjectWithTripleKey.class);
  }
}
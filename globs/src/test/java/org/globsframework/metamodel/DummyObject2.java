package org.globsframework.metamodel;

import org.globsframework.metamodel.annotations.KeyField;
import org.globsframework.metamodel.annotations.DoublePrecision;
import org.globsframework.metamodel.fields.IntegerField;
import org.globsframework.metamodel.fields.StringField;
import org.globsframework.metamodel.fields.DoubleField;
import org.globsframework.metamodel.utils.GlobTypeLoader;

public class DummyObject2 {

  public static GlobType TYPE;

  @KeyField
  public static IntegerField ID;

  public static StringField LABEL;

  @DoublePrecision(4)
  public static DoubleField VALUE;

  static {
    GlobTypeLoader.init(DummyObject2.class);
  }
}

package org.globsframework.metamodel;

import org.globsframework.metamodel.annotations.KeyField;
import org.globsframework.metamodel.annotations.MultiLineText;
import org.globsframework.metamodel.fields.IntegerField;
import org.globsframework.metamodel.fields.StringField;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.metamodel.utils.GlobTypeLoaderFactory;

public class DummyObjectWithMultiLineText {
  public static GlobType TYPE;

  @KeyField
  public static IntegerField ID;

  @MultiLineText
  public static StringField COMMENT;

  static {
     GlobTypeLoaderFactory.create(DummyObjectWithMultiLineText.class).load();
  }
}

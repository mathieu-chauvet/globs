package org.globs.samples.blog.model;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.metamodel.fields.IntegerField;
import org.globsframework.metamodel.fields.StringField;
import org.globsframework.metamodel.annotations.KeyField;
import org.globsframework.metamodel.annotations.NamingField;
import org.globsframework.metamodel.utils.GlobTypeLoaderFactory;

public class Category {
  public static GlobType TYPE;

  @KeyField
  public static IntegerField ID;

  @NamingField
  public static StringField NAME;

  static {
    GlobTypeLoaderFactory.create(Category.class).load();
  }
}

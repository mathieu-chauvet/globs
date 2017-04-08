package org.globs.samples.addressbook.model;

import org.globsframework.metamodel.fields.IntegerField;
import org.globsframework.metamodel.fields.StringField;
import org.globsframework.metamodel.annotations.KeyField;
import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.metamodel.utils.GlobTypeLoaderFactory;

public class Contact {
  public static GlobType TYPE;

  @KeyField
  public static IntegerField ID;

  public static StringField FIRST_NAME;
  public static StringField LAST_NAME;
  public static StringField PHONE;
  public static StringField EMAIL;

  static {
    GlobTypeLoaderFactory.create(Contact.class).load();
  }
}

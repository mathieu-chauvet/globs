package org.globs.samples.blog.model;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.metamodel.fields.*;
import org.globsframework.metamodel.annotations.*;

public class Post {
  public static GlobType TYPE;

  @KeyField
  public static IntegerField ID;

  @NamingField
  public static StringField TITLE;

  @MultiLineText
  public static StringField CONTENT;

  public static DateField PUBLICATION_DATE;

  @DefaultBoolean(false)
  public static BooleanField PUBLISHED;

  @Target(Category.class)
  public static IntegerField CATEGORY;

  static {
    GlobTypeLoader.init(Post.class);
  }
}

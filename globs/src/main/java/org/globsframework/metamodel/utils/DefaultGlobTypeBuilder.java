package org.globsframework.metamodel.utils;

import org.globsframework.metamodel.GlobType;
import org.globsframework.model.Glob;
import org.globsframework.model.Key;
import org.globsframework.utils.Utils;

import java.util.Collections;
import java.util.Map;

public class DefaultGlobTypeBuilder implements GlobTypeBuilder {
  private DefaultGlobType type;
  private DefaultFieldFactory factory;

  public static GlobTypeBuilder init(String typeName) {
    return new DefaultGlobTypeBuilder(typeName);
  }

  public DefaultGlobTypeBuilder(String typeName) {
    type = new DefaultGlobType(typeName, Collections.EMPTY_MAP);
    factory = new DefaultFieldFactory(type);
  }

  public GlobTypeBuilder addIntegerKey(String fieldName, Glob... annotations) {
    factory.addInteger(fieldName, true, adaptAnnotation(annotations));
    return this;
  }

  private Map<Key, Glob> adaptAnnotation(Glob[] annotations) {
    return Utils.adapt(annotations, new Utils.MapAdapter<Key, Glob, Glob>() {
      public Key getKey(Glob glob) {
        return glob.getKey();
      }

      public Glob getValue(Glob glob) {
        return glob;
      }
    });
  }

  public GlobTypeBuilder addStringField(String fieldName, Glob... annotations) {
    factory.addString(fieldName, false, adaptAnnotation(annotations));
    return this;
  }

  public GlobTypeBuilder addIntegerField(String fieldName, Glob... annotations) {
    factory.addInteger(fieldName, false, adaptAnnotation(annotations));
    return this;
  }

  public GlobTypeBuilder addDoubleField(String fieldName, Glob... annotations) {
    factory.addDouble(fieldName, false, adaptAnnotation(annotations));
    return this;
  }

  public GlobTypeBuilder addLongField(String fieldName, Glob... annotations) {
    factory.addLong(fieldName, false, adaptAnnotation(annotations));
    return this;
  }

  public GlobTypeBuilder addBooleanField(String fieldName, Glob... annotations) {
    factory.addBoolean(fieldName, false, adaptAnnotation(annotations));
    return this;
  }

  public GlobTypeBuilder addBlobField(String fieldName, Glob... annotations) {
    factory.addBlob(fieldName, false, adaptAnnotation(annotations));
    return this;
  }

  public GlobTypeBuilder addLinkField(String fieldName, Glob... annotations) {
    factory.addLink(fieldName, false, adaptAnnotation(annotations));
    return this;
  }

  public GlobType get() {
    type.completeInit();
    return type;
  }
}

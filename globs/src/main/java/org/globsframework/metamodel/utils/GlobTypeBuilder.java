package org.globsframework.metamodel.utils;

import org.globsframework.metamodel.GlobType;
import org.globsframework.model.Glob;

public interface GlobTypeBuilder {
    GlobTypeBuilder addIntegerKey(String fieldName, Glob... annotations);

    GlobTypeBuilder addStringField(String fieldName, Glob... annotations);

    GlobTypeBuilder addIntegerField(String fieldName, Glob... annotations);

    GlobTypeBuilder addDoubleField(String fieldName, Glob... annotations);

    GlobTypeBuilder addLongField(String fieldName, Glob... annotations);

    GlobTypeBuilder addBooleanField(String fieldName, Glob... annotations);

    GlobTypeBuilder addBlobField(String fieldName, Glob... annotations);

    GlobTypeBuilder addLinkField(String fieldName, Glob... annotations);

    GlobType get();
}

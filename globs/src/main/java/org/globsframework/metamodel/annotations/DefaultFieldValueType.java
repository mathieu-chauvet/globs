package org.globsframework.metamodel.annotations;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.model.Key;

public class DefaultFieldValueType {
    public static GlobType TYPE;
    public static Key key;

    static {
        GlobTypeLoader.init(DefaultFieldValueType.class);
    };
}

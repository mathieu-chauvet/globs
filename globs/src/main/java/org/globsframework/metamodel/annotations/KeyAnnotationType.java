package org.globsframework.metamodel.annotations;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.model.Key;

public class KeyAnnotationType {
    static GlobType TYPE;

    @InitUniqueKey
    public static Key UNIQUE_KEY;

    static {
        GlobTypeLoader.init(KeyAnnotationType.class);
    }
}

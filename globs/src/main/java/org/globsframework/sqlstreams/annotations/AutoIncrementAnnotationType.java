package org.globsframework.sqlstreams.annotations;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.annotations.InitUniqueKey;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.model.Key;

public class AutoIncrementAnnotationType {
    public static GlobType TYPE;

    @InitUniqueKey
    public static Key KEY;

    static {
        GlobTypeLoader.init(AutoIncrementAnnotationType.class);
    }
}

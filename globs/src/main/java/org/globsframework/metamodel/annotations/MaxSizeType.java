package org.globsframework.metamodel.annotations;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.fields.IntegerField;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.model.Glob;
import org.globsframework.model.Key;

public class MaxSizeType {
    static public GlobType TYPE;
    static public Key KEY;

    @DefaultFieldValue
    static public IntegerField VALUE;

    static {
        GlobTypeLoader.init(MaxSizeType.class);
    }
}

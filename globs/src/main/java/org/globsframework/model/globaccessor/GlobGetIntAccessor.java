package org.globsframework.model.globaccessor;

import org.globsframework.model.Glob;

public interface GlobGetIntAccessor extends GlobGetAccessor {
   int get(Glob glob, int defaultValueIfNull);

   Integer get(Glob glob);
}

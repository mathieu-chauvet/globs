package org.globsframework.model.globaccessor;

import org.globsframework.model.Glob;

public interface GlobSetDoubleAccessor extends GlobSetAccessor {

   void set(Glob glob, double value);

   void set(Glob glob, Double value);

}

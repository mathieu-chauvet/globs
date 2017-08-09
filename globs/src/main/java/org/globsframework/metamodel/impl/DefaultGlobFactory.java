package org.globsframework.metamodel.impl;

import org.globsframework.metamodel.Field;
import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.fields.IntegerField;
import org.globsframework.model.GlobFactory;
import org.globsframework.model.MutableGlob;
import org.globsframework.model.globaccessor.GlobSetAccessor;
import org.globsframework.model.globaccessor.GlobSetIntAccessor;
import org.globsframework.model.impl.DefaultGlob;

class DefaultGlobFactory implements GlobFactory {
   private GlobType type;

   public DefaultGlobFactory(GlobType type) {
      this.type = type;
   }

   public MutableGlob create() {
      return new DefaultGlob(type);
   }

   public GlobSetAccessor get(Field field) {
      return null;
   }

   public GlobSetIntAccessor get(IntegerField field) {
      return null;
   }
}

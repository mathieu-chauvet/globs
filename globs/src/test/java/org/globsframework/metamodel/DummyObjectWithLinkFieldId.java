package org.globsframework.metamodel;

import org.globsframework.metamodel.annotations.KeyField;
import org.globsframework.metamodel.annotations.LinkModelName;
import org.globsframework.metamodel.annotations.Target;
import org.globsframework.metamodel.fields.IntegerField;
import org.globsframework.metamodel.links.Link;
import org.globsframework.metamodel.utils.GlobTypeLoader;
import org.globsframework.metamodel.utils.GlobTypeLoaderFactory;

public class DummyObjectWithLinkFieldId {
   public static GlobType TYPE;

   @KeyField
   public static IntegerField LINK_ID;

   @LinkModelName("ANY")
   @Target(DummyObject.class)
   public static Link LINK;

   static {
      GlobTypeLoader loader = GlobTypeLoaderFactory.create(DummyObjectWithLinkFieldId.class);
      loader.register(MutableGlobLinkModel.LinkRegister.class,
                      (linkModel) ->
                         LINK = linkModel.getDirectLinkBuilder(LINK).add(LINK_ID, DummyObject.ID).publish())
      .load();
   }
}

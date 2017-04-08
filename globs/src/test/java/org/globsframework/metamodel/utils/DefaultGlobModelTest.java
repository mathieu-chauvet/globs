package org.globsframework.metamodel.utils;

import junit.framework.TestCase;
import org.globsframework.metamodel.*;
import org.globsframework.metamodel.annotations.KeyField;
import org.globsframework.metamodel.annotations.Required;
import org.globsframework.metamodel.annotations.Target;
import org.globsframework.metamodel.fields.IntegerField;
import org.globsframework.metamodel.links.Link;
import org.globsframework.utils.Strings;
import org.globsframework.utils.TestUtils;
import org.globsframework.utils.exceptions.InvalidData;

public class DefaultGlobModelTest extends TestCase {
   private GlobModel inner = new DefaultGlobModel(DummyObject.TYPE);
   private GlobModel model = new DefaultGlobModel(inner, DummyObject2.TYPE);

   public void testStandardCase() throws Exception {
      TestUtils.assertSetEquals(inner.getAll(), DummyObject.TYPE);
      TestUtils.assertSetEquals(model.getAll(), DummyObject.TYPE, DummyObject2.TYPE);

      assertEquals(DummyObject.TYPE, inner.getType(DummyObject.TYPE.getName()));
      assertEquals(DummyObject.TYPE, model.getType(DummyObject.TYPE.getName()));
      assertEquals(DummyObject2.TYPE, model.getType(DummyObject2.TYPE.getName()));
   }

   public void testPropertyIdAllocationIsDelegatedToTheInnerModel() throws Exception {
      assertEquals(0, inner.createFieldProperty("prop1").getId());
      assertEquals(1, inner.createFieldProperty("prop2").getId());
      assertEquals(2, model.createFieldProperty("prop3").getId());

      assertEquals(0, inner.createGlobTypeProperty("prop1").getId());
      assertEquals(1, inner.createGlobTypeProperty("prop2").getId());
      assertEquals(2, model.createGlobTypeProperty("prop3").getId());
   }

   public void testIncludesConstantsFromInnerModel() throws Exception {
      inner = new DefaultGlobModel(DummyObjectWithConstants.TYPE);
      model = new DefaultGlobModel(inner, DummyObject.TYPE);

      TestUtils.assertSetEquals(model.getConstants(),
                                DummyObjectWithConstants.CONSTANT_1.getGlob(),
                                DummyObjectWithConstants.CONSTANT_2.getGlob());
   }

   public static class LargeLinkCycle1 {
      public static GlobType TYPE;

      @KeyField
      public static IntegerField ID;

      @Target(LargeLinkCycle2.class)
      public static IntegerField LINK_ID;

      public static Link LINK;

      static {
         GlobTypeLoaderFactory.create(LargeLinkCycle1.class)
            .register(MutableGlobLinkModel.LinkRegister.class, mutableGlobLinkModel ->
               LINK = mutableGlobLinkModel.getLinkBuilder(LINK)
                  .add(LINK_ID, LargeLinkCycle2.ID)
                  .publish())
            .load();
      }
   }

   public static class LargeLinkCycle2 {
      public static GlobType TYPE;

      @KeyField
      public static IntegerField ID;

      @Target(LargeLinkCycle3.class)
      public static IntegerField LINK_ID;

      public static Link LINK;

      static {
         GlobTypeLoaderFactory.create(LargeLinkCycle2.class)
            .register(MutableGlobLinkModel.LinkRegister.class, mutableGlobLinkModel ->
               LINK = mutableGlobLinkModel.getLinkBuilder(LINK)
                  .add(LINK_ID, LargeLinkCycle3.ID)
                  .publish())
            .load();
      }
   }

   public static class LargeLinkCycle3 {
      public static GlobType TYPE;

      @KeyField
      public static IntegerField ID;

      @Target(LargeLinkCycle1.class)
      public static IntegerField LINK_ID;

      public static Link LINK;

      static {
         GlobTypeLoaderFactory.create(LargeLinkCycle3.class)
            .register(MutableGlobLinkModel.LinkRegister.class, mutableGlobLinkModel ->
               LINK = mutableGlobLinkModel.getLinkBuilder(LINK)
                  .add(LINK_ID, LargeLinkCycle1.ID)
                  .publish())
            .load();
      }
   }

   public void testDependencies() throws Exception {
      GlobModel model =
         GlobModelBuilder.init(LargeLinkCycle1.TYPE, LargeLinkCycle2.TYPE, LargeLinkCycle3.TYPE).get();
      GlobTypeDependencies dependencies = model.getDependencies();
      TestUtils.assertEquals(dependencies.getCreationSequence(), LargeLinkCycle3.TYPE, LargeLinkCycle2.TYPE,
                             LargeLinkCycle1.TYPE);
      TestUtils.assertEquals(dependencies.getUpdateSequence(), LargeLinkCycle3.TYPE, LargeLinkCycle2.TYPE,
                             LargeLinkCycle1.TYPE);
      TestUtils.assertEquals(dependencies.getDeletionSequence(), LargeLinkCycle1.TYPE, LargeLinkCycle2.TYPE,
                             LargeLinkCycle3.TYPE);

      assertTrue(dependencies.needsPostUpdate(LargeLinkCycle3.TYPE));
      assertFalse(dependencies.needsPostUpdate(LargeLinkCycle1.TYPE));
      assertFalse(dependencies.needsPostUpdate(LargeLinkCycle2.TYPE));
   }

   public void testDependenciesWithInnerModel() throws Exception {
      GlobModel inner = GlobModelBuilder.init(LargeLinkCycle1.TYPE).get();
      GlobModel model = GlobModelBuilder.init(inner, LargeLinkCycle2.TYPE, LargeLinkCycle3.TYPE).get();

      GlobTypeDependencies dependencies = model.getDependencies();
      TestUtils.assertEquals(dependencies.getCreationSequence(), LargeLinkCycle3.TYPE, LargeLinkCycle2.TYPE,
                             LargeLinkCycle1.TYPE);
      TestUtils.assertEquals(dependencies.getUpdateSequence(), LargeLinkCycle3.TYPE, LargeLinkCycle2.TYPE,
                             LargeLinkCycle1.TYPE);
      TestUtils.assertEquals(dependencies.getDeletionSequence(), LargeLinkCycle1.TYPE, LargeLinkCycle2.TYPE,
                             LargeLinkCycle3.TYPE);

      assertTrue(dependencies.needsPostUpdate(LargeLinkCycle3.TYPE));
      assertFalse(dependencies.needsPostUpdate(LargeLinkCycle1.TYPE));
      assertFalse(dependencies.needsPostUpdate(LargeLinkCycle2.TYPE));
   }

   public static class LargeLinkCycleWithRequiredFieldError1 {
      public static GlobType TYPE;

      @KeyField
      public static IntegerField ID;

      @Required
      @Target(LargeLinkCycleWithRequiredFieldError2.class)
      public static IntegerField LINK_ID;

      public static Link LINK;

      static {
         GlobTypeLoaderFactory.create(LargeLinkCycleWithRequiredFieldError1.class)
            .register(MutableGlobLinkModel.LinkRegister.class, mutableGlobLinkModel ->
               LINK = mutableGlobLinkModel.getLinkBuilder(LINK)
                  .add(LINK_ID, LargeLinkCycleWithRequiredFieldError2.ID)
                  .publish())
            .load();
      }
   }

   public static class LargeLinkCycleWithRequiredFieldError2 {
      public static GlobType TYPE;

      @KeyField
      public static IntegerField ID;

      @Required
      @Target(LargeLinkCycleWithRequiredFieldError3.class)
      public static IntegerField LINK_ID;

      public static Link LINK;

      static {
         GlobTypeLoaderFactory.create(LargeLinkCycleWithRequiredFieldError2.class)
            .register(MutableGlobLinkModel.LinkRegister.class, mutableGlobLinkModel ->
               LINK = mutableGlobLinkModel.getLinkBuilder(LINK)
                  .add(LINK_ID, LargeLinkCycleWithRequiredFieldError3.ID)
                  .publish())
            .load();
      }
   }

   public static class LargeLinkCycleWithRequiredFieldError3 {
      public static GlobType TYPE;

      @KeyField
      public static IntegerField ID;

      @Required
      @Target(LargeLinkCycleWithRequiredFieldError1.class)
      public static IntegerField LINK_ID;

      public static Link LINK;

      static {
         GlobTypeLoaderFactory.create(LargeLinkCycleWithRequiredFieldError3.class)
            .register(MutableGlobLinkModel.LinkRegister.class, mutableGlobLinkModel ->
               LINK = mutableGlobLinkModel.getLinkBuilder(LINK)
                  .add(LINK_ID, LargeLinkCycleWithRequiredFieldError1.ID)
                  .publish())
            .load();
      }
   }

   public void testDependenciesWithLinkCycleWithRequiredFieldError() throws Exception {
      try {
         GlobModelBuilder.init(LargeLinkCycleWithRequiredFieldError1.TYPE,
                               LargeLinkCycleWithRequiredFieldError2.TYPE,
                               LargeLinkCycleWithRequiredFieldError3.TYPE)
            .get();
         fail();
      }
      catch (InvalidData e) {
         assertEquals("Cycles found with required fields:" + Strings.LINE_SEPARATOR
                      + "'largeLinkCycleWithRequiredFieldError1' = 'largeLinkCycleWithRequiredFieldError1.linkId'"
                      + Strings.LINE_SEPARATOR
                      + "'largeLinkCycleWithRequiredFieldError2' = 'largeLinkCycleWithRequiredFieldError2.linkId'"
                      + Strings.LINE_SEPARATOR
            , e.getMessage());
      }
   }
}

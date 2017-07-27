package org.globsframework.metamodel.utils;

import org.globsframework.metamodel.Field;
import org.globsframework.metamodel.GlobModel;
import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.annotations.KeyField;
import org.globsframework.metamodel.fields.IntegerField;
import org.globsframework.metamodel.properties.Property;
import org.globsframework.utils.TestUtils;
import org.globsframework.utils.exceptions.UnexpectedApplicationState;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultGlobTypeTest {
   private GlobType globType;
   private Field field;
   private GlobModel globModel;

   @Test
   public void testGlobTypeProperty() throws Exception {
      initGlobType();

      Property<GlobType, Object> globTypeProperty = globModel.createGlobTypeProperty("globType info");
      assertEquals("globType info", globTypeProperty.getName());
      assertEquals(0, globTypeProperty.getId());

      globType.updateProperty(globTypeProperty, 3);
      assertEquals(3, globType.getProperty(globTypeProperty));

      globType.updateProperty(globTypeProperty, 4);
      assertEquals(4, globType.getProperty(globTypeProperty));

      Property<Field, Object> property = globModel.createFieldProperty("field info");
      assertEquals(0, property.getId());
      field.updateProperty(property, 2);
      assertEquals(2, field.getProperty(property));

      field.updateProperty(property, 4);
      assertEquals(4, field.getProperty(property));
      assertEquals("field info", property.getName());
   }

   @Test
   public void testFields() {
      initGlobType();
      assertEquals("type", globType.getName());
      assertNotNull(globType.findField("field1"));
      assertNull(globType.findField("Field1"));
      assertNotNull(field);
      TestUtils.assertFails(() -> globType.getFields(), UnexpectedApplicationState.class);
   }

   @Test
   public void testFieldProperty() throws Exception {
      initGlobType();
      Property<Field, Object> property = globModel.createFieldProperty("field property");
      field.updateProperty(property, 3);
      assertEquals(3, field.getProperty(property));
   }

   private static class Type {
      public static GlobType TYPE;

      @KeyField
      public static IntegerField FIELD1;
   }

   private void initGlobType() {
      Type.TYPE = null;
      GlobTypeLoader loader = GlobTypeLoaderFactory.create(Type.class).load();
      globType = loader.getType();
      field = globType.getField("field1");
      globModel = new DefaultGlobModel(globType);
   }
}

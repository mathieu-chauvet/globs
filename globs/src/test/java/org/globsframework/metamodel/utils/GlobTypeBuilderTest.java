package org.globsframework.metamodel.utils;

import junit.framework.TestCase;
import org.globsframework.metamodel.Field;
import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.annotations.DefaultDoubleAnnotationType;
import org.globsframework.metamodel.annotations.NamingFieldAnnotationType;
import org.globsframework.metamodel.fields.*;
import org.globsframework.utils.exceptions.InvalidParameter;
import org.globsframework.utils.exceptions.ItemAlreadyExists;

public class GlobTypeBuilderTest extends TestCase {
  public void test() throws Exception {
    GlobType type = DefaultGlobTypeBuilder.init("aType")
      .addIntegerKey("id")
      .addStringField("string")
      .addIntegerField("int")
      .addLongField("long")
      .addDoubleField("double")
      .addBlobField("blob")
      .addBooleanField("boolean")
      .get();

    assertEquals("aType", type.getName());

    Field[] keyFields = type.getKeyFields();
    assertEquals(1, keyFields.length);
    Field key = keyFields[0];
    assertTrue(key instanceof IntegerField);
    assertEquals("id", key.getName());

    checkField(type, "string", StringField.class);
    checkField(type, "int", IntegerField.class);
    checkField(type, "long", LongField.class);
    checkField(type, "double", DoubleField.class);
    checkField(type, "blob", BlobField.class);
    checkField(type, "boolean", BooleanField.class);
    checkField(type, "date", DateField.class);
    checkField(type, "timestamp", TimeStampField.class);
  }

  private void checkField(GlobType type, String fieldName, Class<? extends Field> fieldClass) {
    Field field = type.getField(fieldName);
    assertTrue(fieldClass.isAssignableFrom(field.getClass()));
  }

  public void testCannotUseTheSameNameTwice() throws Exception {
    try {
      DefaultGlobTypeBuilder.init("aType")
        .addIntegerKey("id")
        .addStringField("field")
        .addIntegerField("field");
      fail();
    }
    catch (ItemAlreadyExists e) {
      assertEquals("Field field declared twice for type aType", e.getMessage());
    }
  }

  public void testAtLeastOneKeyMustBeDefined() throws Exception {
    try {
      DefaultGlobTypeBuilder.init("type").get();
      fail();
    }
    catch (InvalidParameter e) {
      assertEquals("GlobType type has no key field", e.getMessage());
    }
  }

  public void testNamingField() throws Exception {
    GlobType type = DefaultGlobTypeBuilder.init("aType")
      .addIntegerKey("id")
      .addStringField("name", NamingFieldAnnotationType.create())
      .get();

    StringField field = GlobTypeUtils.findNamingField(type);
    assertNotNull(field);
    assertEquals("name", field.getName());
  }

  public void testWithAnnotations() throws Exception {
    DefaultGlobTypeBuilder.init("aType")
            .addDoubleField("aDouble", DefaultDoubleAnnotationType.create(2.2));

  }
}

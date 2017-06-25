package org.globsframework.model;

import org.globsframework.metamodel.Field;
import org.globsframework.metamodel.fields.*;

import java.util.Date;

public interface MutableGlob extends Glob {

  MutableGlob set(IntegerField field, Integer value);

  MutableGlob set(LongField field, Long value);

  MutableGlob set(DoubleField field, Double value);

  MutableGlob set(StringField field, String value);

  MutableGlob set(BooleanField field, Boolean value);

  MutableGlob set(BlobField field, byte[] value);

  MutableGlob setObject(Field field, Object value);

  MutableGlob setValues(FieldValues values);
}

package org.globsframework.model;

import org.globsframework.metamodel.Field;
import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.fields.*;
import org.globsframework.model.impl.FourFieldKey;
import org.globsframework.model.impl.ThreeFieldKey;
import org.globsframework.model.impl.TwoFieldKey;
import org.globsframework.utils.exceptions.InvalidState;
import org.globsframework.utils.exceptions.ItemNotFound;

public abstract class AbstractKey implements Key, FieldValues {

  public static Key create(GlobType type, Object singleFieldValue) {
    return KeyBuilder.newKey(type, singleFieldValue);
  }

  public static Key create(Field field1, Object value1, Field field2, Object value2) {
    return new TwoFieldKey(field1, value1, field2, value2);
  }

  public static Key create(Field field1, Object value1, Field field2, Object value2, Field field3, Object value3) {
    return new ThreeFieldKey(field1, value1, field2, value2, field3, value3);
  }

  public static Key create(Field field1, Object value1, Field field2, Object value2, Field field3, Object value3, Field field4, Object value4) {
    return new FourFieldKey(field1, value1, field2, value2, field3, value3, field4, value4);
  }

  public static KeyBuilder create(GlobType type) {
    return KeyBuilder.init(type);
  }

  public Boolean get(BooleanField field, boolean defaultIfNull) {
    Boolean value = get(field);
    if (value == null) {
      return defaultIfNull;
    }
    return value;
  }

  public double get(DoubleField field, double valueIfNull) throws ItemNotFound {
    Double value = get(field);
    if (value == null) {
      return valueIfNull;
    }
    return value;
  }

  public int get(IntegerField field, int valueIfNull) throws ItemNotFound {
    Integer value = get(field);
    return value == null ? valueIfNull : value;
  }

  public byte[] get(BlobField field) {
     checkIsKeyField(field);
     return (byte[])getSwitchValue(field);
  }

  public Boolean get(BooleanField field) {
     checkIsKeyField(field);
     return (Boolean)getSwitchValue(field);
  }

  public Double get(DoubleField field) {
     checkIsKeyField(field);
     return (Double)getSwitchValue(field);
  }

   public boolean isNull(Field field) throws ItemNotFound {
      return getValue(field) == null;
   }

   public Object getValue(Field field) {
     checkIsKeyField(field);
     return getSwitchValue(field);
  }

  public Integer get(IntegerField field) {
     checkIsKeyField(field);
     return (Integer)getSwitchValue(field);
  }

  public Long get(LongField field) {
     checkIsKeyField(field);
     return (Long)getSwitchValue(field);
  }

  public long get(LongField field, long valueIfNull) throws ItemNotFound {
    Long value = get(field);
    return value == null ? valueIfNull : value;
  }

  protected final void checkIsKeyField(Field field) {
    if (getGlobType() != field.getGlobType()){
      throw new InvalidState("For " + field.getName() + " tpye are differents : " + field.getGlobType() + " != " + getGlobType());
    }
    if (!field.isKeyField()) {
      throw new ItemNotFound("'" + field.getName() + "' is not a key of type " + getGlobType().getName());
    }
  }

  public String get(StringField field) {
     checkIsKeyField(field);
     return (String)getSwitchValue(field);
  }

  public boolean isTrue(BooleanField field) {
     return isTrue(field);
  }

   public boolean contains(Field field) {
      return field.getGlobType() == getGlobType() && field.isKeyField();
   }

   public void applyOnKeyField(Functor functor) throws Exception {
      apply(functor);
   }

   public void safeApplyOnKeyField(Functor functor) {
      safeApply(functor);
   }

   public boolean containsKey(Field field) {
      return field.isKeyField();
   }

   public FieldValues asFieldValues() {
      return this;
   }

   protected abstract Object getSwitchValue(Field field);
}

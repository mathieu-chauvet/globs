package org.globsframework.model.impl;

import org.globsframework.metamodel.Field;
import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.fields.*;
import org.globsframework.model.FieldValue;
import org.globsframework.model.Key;
import org.globsframework.utils.exceptions.ItemNotFound;

import java.util.Date;

public class EmptyKey implements Key {
   private final GlobType type;

   public EmptyKey(GlobType type) {
      this.type = type;
   }

   public GlobType getGlobType() {
      return type;
   }

   public Object getValue(Field field) throws ItemNotFound {
      throw new RuntimeException("Empty key '" + field.getFullName() + " not available");
   }

   public Double get(DoubleField field) throws ItemNotFound {
      throw new RuntimeException("Empty key '" + field.getFullName() + " not available");
   }

   public Double get(DoubleField field, double valueIfNull) throws ItemNotFound {
      throw new RuntimeException("Empty key '" + field.getFullName() + " not available");
   }

   public Date get(DateField field) throws ItemNotFound {
      throw new RuntimeException("Empty key '" + field.getFullName() + " not available");
   }

   public Date get(DateField field, Date valueIfNull) throws ItemNotFound {
      throw new RuntimeException("Empty key '" + field.getFullName() + " not available");
   }

   public Date get(TimeStampField field) throws ItemNotFound {
      throw new RuntimeException("Empty key '" + field.getFullName() + " not available");
   }

   public Integer get(IntegerField field) throws ItemNotFound {
      throw new RuntimeException("Empty key '" + field.getFullName() + " not available");
   }

   public int get(IntegerField field, int valueIfNull) throws ItemNotFound {
      throw new RuntimeException("Empty key '" + field.getFullName() + " not available");
   }

   public String get(StringField field) throws ItemNotFound {
      throw new RuntimeException("Empty key '" + field.getFullName() + " not available");
   }

   public Boolean get(BooleanField field) throws ItemNotFound {
      throw new RuntimeException("Empty key '" + field.getFullName() + " not available");
   }

   public Boolean get(BooleanField field, boolean defaultIfNull) {
      throw new RuntimeException("Empty key '" + field.getFullName() + " not available");
   }

   public boolean isTrue(BooleanField field) throws ItemNotFound {
      throw new RuntimeException("Empty key '" + field.getFullName() + " not available");
   }

   public Long get(LongField field) throws ItemNotFound {
      throw new RuntimeException("Empty key '" + field.getFullName() + " not available");
   }

   public byte[] get(BlobField field) throws ItemNotFound {
      throw new RuntimeException("Empty key '" + field.getFullName() + " not available");
   }

   public boolean contains(Field field) {
      return false;
   }

   public int size() {
      return 0;
   }

   public void apply(Functor functor) throws Exception {
   }

   public void safeApply(Functor functor) {
   }

   public FieldValue[] toArray() {
      return new FieldValue[0];
   }

   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      EmptyKey key = (EmptyKey)o;

      if (type != null ? !type.equals(key.type) : key.type != null) {
         return false;
      }

      return true;
   }

   public int hashCode() {
      return type != null ? type.hashCode() : 0;
   }

   public String toString() {
      return "EmptyKey/" + type.getName();
   }
}

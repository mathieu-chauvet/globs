package org.globsframework.metamodel.fields.impl;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.fields.BooleanField;
import org.globsframework.metamodel.fields.FieldValueVisitor;
import org.globsframework.metamodel.fields.FieldVisitor;
import org.globsframework.metamodel.utils.Annotations;
import org.globsframework.utils.exceptions.UnexpectedApplicationState;

public class DefaultBooleanField extends AbstractField implements BooleanField {
   public DefaultBooleanField(String name, GlobType globType, int index, boolean isKeyField, Boolean defaultValue) {
      super(name, globType, Boolean.class, index, isKeyField, defaultValue);
   }

   public void visit(FieldVisitor visitor) throws Exception {
      visitor.visitBoolean(this);
   }

   public void safeVisit(FieldVisitor visitor) {
      try {
         visitor.visitBoolean(this);
      }
      catch (RuntimeException e) {
         throw new RuntimeException("On " + this, e);
      }
      catch (Exception e) {
         throw new UnexpectedApplicationState("On " + this, e);
      }
   }

   public void safeVisit(FieldValueVisitor visitor, Object value) {
      try {
         visitor.visitBoolean(this, (Boolean)value);
      }
      catch (RuntimeException e) {
         throw e;
      }
      catch (Exception e) {
         throw new UnexpectedApplicationState(e);
      }
   }
}

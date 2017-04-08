package org.globsframework.metamodel.fields.impl;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.fields.DateField;
import org.globsframework.metamodel.fields.FieldValueVisitor;
import org.globsframework.metamodel.fields.FieldVisitor;
import org.globsframework.utils.exceptions.UnexpectedApplicationState;

import java.util.Date;

public class DefaultDateField extends AbstractField implements DateField {
   public DefaultDateField(String name, GlobType globType, int index, boolean isKeyField) {
      super(name, globType, Date.class, index, isKeyField, null);
   }

   public void visit(FieldVisitor visitor) throws Exception {
      visitor.visitDate(this);
   }

   public void safeVisit(FieldVisitor visitor) {
      try {
         visitor.visitDate(this);
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
         visitor.visitDate(this, (Date)value);
      }
      catch (RuntimeException e) {
         throw new RuntimeException("On " + this, e);
      }
      catch (Exception e) {
         throw new UnexpectedApplicationState("On " + this, e);
      }
   }
}

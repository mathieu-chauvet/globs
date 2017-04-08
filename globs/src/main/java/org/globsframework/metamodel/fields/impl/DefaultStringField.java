package org.globsframework.metamodel.fields.impl;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.fields.FieldValueVisitor;
import org.globsframework.metamodel.fields.FieldVisitor;
import org.globsframework.metamodel.fields.StringField;
import org.globsframework.metamodel.utils.Annotations;
import org.globsframework.utils.exceptions.UnexpectedApplicationState;

public class DefaultStringField extends AbstractField implements StringField {

   public DefaultStringField(String name, GlobType globType, int index, boolean isKeyField, String defaultValue) {
      super(name, globType, String.class, index, isKeyField, defaultValue);
   }

   public void visit(FieldVisitor visitor) throws Exception {
      visitor.visitString(this);
   }

   public void safeVisit(FieldVisitor visitor) {
      try {
         visitor.visitString(this);
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
         visitor.visitString(this, (String)value);
      }
      catch (RuntimeException e) {
         throw new RuntimeException("On " + this, e);
      }
      catch (Exception e) {
         throw new UnexpectedApplicationState("On " + this, e);
      }
   }
}

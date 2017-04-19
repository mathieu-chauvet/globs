package org.globsframework.metamodel.fields.impl;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.annotations.MaxSize;
import org.globsframework.metamodel.annotations.MaxSizeType;
import org.globsframework.metamodel.fields.BlobField;
import org.globsframework.metamodel.fields.FieldValueVisitor;
import org.globsframework.metamodel.fields.FieldVisitor;
import org.globsframework.metamodel.type.DataType;
import org.globsframework.metamodel.utils.Annotations;
import org.globsframework.utils.exceptions.UnexpectedApplicationState;

import java.util.Arrays;

public class DefaultBlobField extends AbstractField implements BlobField {

   public DefaultBlobField(String name, GlobType globType, int index) {
      super(name, globType, byte[].class, index, false, null, DataType.Bytes);
   }

   public void visit(FieldVisitor visitor) throws Exception {
      visitor.visitBlob(this);
   }

   public void safeVisit(FieldVisitor visitor) {
      try {
         visitor.visitBlob(this);
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
         visitor.visitBlob(this, (byte[])value);
      }
      catch (RuntimeException e) {
         throw new RuntimeException("On " + this, e);
      }
      catch (Exception e) {
         throw new UnexpectedApplicationState("On " + this, e);
      }
   }

   public boolean valueEqual(Object o1, Object o2) {
      return Arrays.equals(((byte[])o1), (byte[])o2);
   }
}

package org.globsframework.metamodel.fields;

public interface FieldVisitor {
    void visitInteger(IntegerField field) throws Exception;

    void visitDouble(DoubleField field) throws Exception;

    void visitString(StringField field) throws Exception;

    void visitBoolean(BooleanField field) throws Exception;

    void visitLong(LongField field) throws Exception;

   void visitBlob(BlobField field) throws Exception;


    class AbstractFieldVisitor implements FieldVisitor {

        public void visitInteger(IntegerField field) throws Exception {
        }

        public void visitDouble(DoubleField field) throws Exception {
        }

        public void visitString(StringField field) throws Exception {
        }

        public void visitBoolean(BooleanField field) throws Exception {
        }

        public void visitLong(LongField field) throws Exception {
        }

       public void visitBlob(BlobField field) throws Exception {
        }
    }
}


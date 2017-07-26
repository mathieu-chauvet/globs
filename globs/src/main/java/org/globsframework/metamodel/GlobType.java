package org.globsframework.metamodel;

import org.globsframework.metamodel.index.Index;
import org.globsframework.metamodel.index.MultiFieldIndex;
import org.globsframework.metamodel.links.Link;
import org.globsframework.metamodel.properties.PropertyHolder;
import org.globsframework.metamodel.utils.Annotations;
import org.globsframework.model.Glob;
import org.globsframework.model.GlobList;
import org.globsframework.model.Key;
import org.globsframework.model.MutableGlob;
import org.globsframework.utils.exceptions.ItemNotFound;

import java.util.Collection;
import java.util.function.BiFunction;

public interface GlobType extends PropertyHolder<GlobType>, Annotations {

   String getName();

   Field getField(String name) throws ItemNotFound;

   Field findField(String name);

   boolean hasField(String name);

   Field[] getFields();

   Field getField(int index);

   int getFieldCount();

   Field[] getKeyFields();

   Field findFieldWithAnnotation(Key key);

   Field getFieldWithAnnotation(Key key) throws ItemNotFound;

   Collection<Field> getFieldsWithAnnotation(Key key);

   Collection<Index> getIndices();

   Collection<MultiFieldIndex> getMultiFieldIndices();

   MutableGlob instantiate();

   <T> T getRegistered(Class<T> klass);
}

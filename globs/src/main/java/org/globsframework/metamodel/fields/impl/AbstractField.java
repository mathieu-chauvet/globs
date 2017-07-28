package org.globsframework.metamodel.fields.impl;

import org.globsframework.metamodel.Field;
import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.annotations.RequiredAnnotationType;
import org.globsframework.metamodel.properties.Property;
import org.globsframework.metamodel.properties.PropertyHolder;
import org.globsframework.metamodel.type.DataType;
import org.globsframework.metamodel.Annotations;
import org.globsframework.metamodel.utils.MutableAnnotations;
import org.globsframework.model.Glob;
import org.globsframework.model.Key;
import org.globsframework.utils.Utils;
import org.globsframework.utils.exceptions.InvalidParameter;
import org.globsframework.utils.exceptions.ItemNotFound;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

abstract public class AbstractField implements Field, MutableAnnotations, PropertyHolder<Field> {
   private final int index;
   private final boolean keyField;
   private final GlobType globType;
   private final Map<Key, Glob> annotations = new HashMap<Key, Glob>();
   private final String name;
   private final Class valueClass;
   private final int keyIndex;
   private final Object defaultValue;
   private final DataType dataType;
   private static Object NULL_OBJECT = new Object();
   private volatile Object properties[] = new Object[]{NULL_OBJECT, NULL_OBJECT};

   protected AbstractField(String name, GlobType globType,
                           Class valueClass, int index, int keyIndex, boolean isKeyField,
                           Object defaultValue, DataType dataType) {
      this.keyIndex = keyIndex;
      this.defaultValue = defaultValue;
      this.name = name;
      this.keyField = isKeyField;
      this.index = index;
      this.globType = globType;
      this.valueClass = valueClass;
      this.dataType = dataType;
   }

   public Object normalize(Object value) {
      return value;
   }

   public String getName() {
      return name;
   }

   public String getFullName() {
      return globType.getName() + "." + name;
   }

   public GlobType getGlobType() {
      return globType;
   }

   public int getIndex() {
      return index;
   }

   public int getKeyIndex() {
      return keyIndex;
   }

   public boolean isKeyField() {
      return keyField;
   }

   public boolean isRequired() {
      return hasAnnotation(RequiredAnnotationType.UNIQUE_KEY);
   }

   public DataType getDataType() {
      return dataType;
   }

   public void checkValue(Object object) throws InvalidParameter {
      if ((object != null) && (!valueClass.equals(object.getClass()))) {
         throw new InvalidParameter("Value '" + object + "' (" + object.getClass().getName()
                                    + ") is not authorized for field: " + getName() +
                                    " (expected " + valueClass.getName() + ")");
      }
   }

   public Class getValueClass() {
      return valueClass;
   }

   public Object getDefaultValue() {
      return defaultValue;
   }

   public String toString() {
      return globType.getName() + "." + name;
   }

   public boolean valueEqual(Object o1, Object o2) {
      return Utils.equal(o1, o2);
   }

   synchronized public <D> void updateProperty(Property<Field, D> key, D value) {
      if (properties.length < key.getId()) {
         Object[] tmp = properties;
         properties = new Object[key.getId() + 2];
         int i;
         for (i = 0; i < tmp.length; i++) {
            properties[i] = tmp[i];

         }
         for (; i < properties.length; i++) {
            properties[i] = NULL_OBJECT;
         }
      }
      properties[key.getId()] = value;
   }

   synchronized public <D> D getProperty(Property<Field, D> key) throws ItemNotFound {
      Object value = properties[key.getId()];
      if (value == NULL_OBJECT) {
         throw new ItemNotFound("No property '" + key.getName() + "' on " + getName());
      }
      return (D)value;
   }

   synchronized public <D> D getProperty(Property<Field, D> key, D returnValueIfUnset) {
      Object value = properties[key.getId()];
      if (value == NULL_OBJECT) {
         return returnValueIfUnset;
      }
      return (D)value;
   }

   public MutableAnnotations addAnnotation(Glob glob) {
      if (glob != null){
         annotations.put(glob.getKey(), glob);
      }
      return this;
   }

   public void addAll(Annotations annotations){
      for (Glob glob : annotations.list()) {
         this.addAnnotation(glob);
      }
   }

   public boolean hasAnnotation(Key key) {
      return annotations.containsKey(key);
   }

   public Glob getAnnotation(Key key) {
      Glob annotation = annotations.get(key);
      if (annotation == null) {
         throw new ItemNotFound(key == null ? "null" : key.toString());
      }
      return annotation;
   }

   public Glob findAnnotation(Key key) {
      return annotations.get(key);
   }

   public Collection<Glob> list() {
      return annotations.values();
   }


   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      AbstractField other = (AbstractField)o;
      return globType.equals(other.globType) && name.equals(other.name);
   }

   public int hashCode() {
      int result;
      result = name.hashCode();
      result = 31 * result + globType.hashCode();
      return result;
   }
}

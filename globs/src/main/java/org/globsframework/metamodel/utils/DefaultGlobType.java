package org.globsframework.metamodel.utils;

import org.globsframework.metamodel.Field;
import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.fields.impl.AbstractField;
import org.globsframework.metamodel.index.Index;
import org.globsframework.metamodel.index.MultiFieldIndex;
import org.globsframework.metamodel.properties.Property;
import org.globsframework.metamodel.properties.PropertyHolder;
import org.globsframework.model.Glob;
import org.globsframework.model.GlobList;
import org.globsframework.model.Key;
import org.globsframework.model.MutableGlob;
import org.globsframework.model.impl.DefaultGlob;
import org.globsframework.model.impl.ReadOnlyGlob;
import org.globsframework.utils.exceptions.InvalidState;
import org.globsframework.utils.exceptions.ItemAlreadyExists;
import org.globsframework.utils.exceptions.ItemNotFound;
import org.globsframework.utils.exceptions.TooManyItems;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

public class DefaultGlobType implements MutableGlobType, MutableAnnotations, PropertyHolder<GlobType> {
   private Map<String, Field> fieldsByName = new TreeMap<String, Field>();
   private Field[] fields;
   private Field[] keyFields = new Field[0];
   private String name;
   private GlobList constants = new GlobList();
   private Map<String, Index> indices = new HashMap<String, Index>(2, 1);
   private Map<String, MultiFieldIndex> multiFieldIndices = new HashMap<String, MultiFieldIndex>(2, 1);
   private Map<Class, Object> registered = new ConcurrentHashMap<>();
   private static Object NULL_OBJECT = new Object();
   private final Map<Key, Glob> annotations = new HashMap<Key, Glob>();
   private Object properties[] = new Object[]{NULL_OBJECT, NULL_OBJECT};


   public DefaultGlobType(String name) {
      this.name = name;
   }

   public int getFieldCount() {
      return fieldsByName.size();
   }

   public Field getField(String name) throws ItemNotFound {
      Field field = fieldsByName.get(name);
      if (field == null) {
         throw new ItemNotFound("Field '" + name + "' not found in type: " + this.name);
      }
      return field;
   }

   public boolean hasField(String name) {
      return findField(name) != null;
   }

   public Field findField(String name) {
      return fieldsByName.get(name);
   }

   public Field[] getFields() {
      return fields;
   }

   public Field getField(int index) {
      return fields[index];
   }

   public String getName() {
      return name;
   }

   synchronized public <D> void updateProperty(Property<GlobType, D> key, D value) {
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

   synchronized public <D> D getProperty(Property<GlobType, D> key) throws ItemNotFound {
      Object value = properties[key.getId()];
      if (value == NULL_OBJECT) {
         throw new ItemNotFound("No property '" + key.getName() + "' on " + getName());
      }
      return (D)value;
   }

   synchronized public <D> D getProperty(Property<GlobType, D> key, D returnValueIfUnset) {
      Object value = properties[key.getId()];
      if (value == NULL_OBJECT) {
         return returnValueIfUnset;
      }
      return (D)value;
   }


   public void addField(AbstractField field) {
      if (hasField(field.getName())) {
         throw new ItemAlreadyExists("Field " + field.getName() +
                                     " declared twice for type " + getName());
      }
      if (field.getIndex() != fieldsByName.size()) {
         throw new InvalidState(field + " should be at index " + field.getIndex() + " but is at" + fieldsByName.size());
      }
      fieldsByName.put(field.getName(), field);
   }

   public void addKey(Field field) {
      Field[] tmp = new Field[keyFields.length + 1];
      System.arraycopy(keyFields, 0, tmp, 0, keyFields.length);
      keyFields = tmp;
      keyFields[keyFields.length - 1] = field;
   }

   public Field[] getKeyFields() {
      return keyFields;
   }

   public Field getFieldWithAnnotation(Key key) throws ItemNotFound {
      Field foundField = findFieldWithAnnotation(key);
      if (foundField != null) {
         return foundField;
      }
      throw new ItemNotFound("no field found with " + key + " under " + this);
   }

   public Field findFieldWithAnnotation(Key key) {
      Field foundField = null;
      for (Field field : fields) {
         if (field.hasAnnotation(key)) {
            if (foundField != null) {
               throw new TooManyItems("Found multiple field with " + key + " => " + field + " and " + foundField);
            }
            foundField = field;
         }
      }
      return foundField;
   }

   public Collection<Field> getFieldsWithAnnotation(Key key) {
      List<Field> annotations = new ArrayList<>();
      for (Field field : fields) {
         if (field.hasAnnotation(key)) {
            annotations.add(field);
         }
      }
      return annotations;
   }

   public void publishFieldWithAnnotations(Key key, BiFunction<Field, Glob, Void> function) {
      for (Field field : fields) {
         Glob annotation = field.findAnnotation(key);
         if (annotation != null) {
            function.apply(field, annotation);
         }
      }
   }

   public void addConstant(ReadOnlyGlob glob) {
      constants.add(glob);
   }

   public GlobList getConstants() {
      return new GlobList(constants);
   }

   public String toString() {
      return name;
   }

   public void completeInit() {
      fields = new Field[fieldsByName.size()];
      for (Field field : fieldsByName.values()) {
         fields[field.getIndex()] = field;
      }
   }

   public void addIndex(Index index) {
      indices.put(index.getName(), index);
   }

   public void addIndex(MultiFieldIndex index) {
      multiFieldIndices.put(index.getName(), index);
   }

   public Collection<Index> getIndices() {
      return indices.values();
   }

   public Collection<MultiFieldIndex> getMultiFieldIndices() {
      return multiFieldIndices.values();
   }

   public MutableGlob instantiate() {
      return new DefaultGlob(this);
   }

   public Index getIndex(String name) {
      return indices.get(name);
   }

   public MultiFieldIndex getMultiFieldIndex(String name) {
      return multiFieldIndices.get(name);
   }

   public <T> void register(Class<T> klass, T t) {
      registered.put(klass, t);
   }

   public <T> T getRegistered(Class<T> klass) {
      return (T)registered.get(klass);
   }

   public MutableAnnotations addAnnotation(Glob glob) {
      if (glob != null) {
         annotations.put(glob.getKey(), glob);
      }
      return this;
   }

   public void addAll(Annotations annotations) {
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

}

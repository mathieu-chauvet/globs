package org.globsframework.metamodel.impl;

import org.globsframework.metamodel.*;
import org.globsframework.metamodel.links.impl.DefaultMutableGlobLinkModel;
import org.globsframework.metamodel.properties.Property;
import org.globsframework.metamodel.utils.GlobTypeDependencies;
import org.globsframework.metamodel.utils.IdProperty;
import org.globsframework.model.GlobList;
import org.globsframework.utils.exceptions.ItemNotFound;

import java.util.*;

public class DefaultGlobModel implements MutableGlobModel {
   private Map<String, GlobType> typesByName = new HashMap<String, GlobType>();
   private int objectTypeSpecificCount;
   private int fieldSpecificCount;
   private GlobModel innerModel;
   private GlobTypeDependencies dependencies;
   DefaultMutableGlobLinkModel globLinkModel;

   public DefaultGlobModel(GlobType... types) {
      this(null, types);
   }

   public DefaultGlobModel(GlobModel innerModel, GlobType... types) {
      add(types);
      this.innerModel = innerModel;
   }

   public GlobType getType(String name) throws ItemNotFound {
      GlobType globType = typesByName.get(name);
      if (globType != null) {
         return globType;
      }
      if (innerModel != null) {
         return innerModel.getType(name);
      }
      throw new ItemNotFound("No object type found with name: " + name);
   }

   public Collection<GlobType> getAll() {
      Set<GlobType> result = new HashSet<GlobType>();
      result.addAll(typesByName.values());
      if (innerModel != null) {
         result.addAll(innerModel.getAll());
      }
      return result;
   }

   public Iterator<GlobType> iterator() {
      return getAll().iterator();
   }

   public GlobTypeDependencies getDependencies() {
      return dependencies;
   }

   public <T> Property<Field, T> createFieldProperty(String name) {
      if (innerModel != null) {
         return innerModel.createFieldProperty(name);
      }
      return new IdProperty<Field, T>(name, fieldSpecificCount++) {
      };
   }

   public GlobLinkModel getLinkModel() {
      return globLinkModel;
   }

   public <T> Property<GlobType, T> createGlobTypeProperty(String name) {
      if (innerModel != null) {
         return innerModel.createGlobTypeProperty(name);
      }
      return new IdProperty<GlobType, T>(name, objectTypeSpecificCount++) {
      };
   }

   private void add(GlobType... types) {
      Arrays.stream(types).forEach(this::add);
   }

   public void add(GlobType type) {
      typesByName.put(type.getName(), type);
   }

   public void complete() {
      globLinkModel = new DefaultMutableGlobLinkModel(this);
      this.dependencies = new GlobTypeDependencies(getAll(), globLinkModel);
   }
}

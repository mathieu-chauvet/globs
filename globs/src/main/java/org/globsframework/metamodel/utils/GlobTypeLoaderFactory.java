package org.globsframework.metamodel.utils;

import org.globsframework.metamodel.annotations.InitUniqueGlob;
import org.globsframework.metamodel.annotations.InitUniqueKey;
import org.globsframework.metamodel.annotations.KeyAnnotationType;
import org.globsframework.metamodel.annotations.KeyIndex;
import org.globsframework.metamodel.links.DirectLink;
import org.globsframework.metamodel.links.Link;
import org.globsframework.metamodel.links.impl.UnInitializedLink;
import org.globsframework.model.Glob;
import org.globsframework.model.Key;
import org.globsframework.model.KeyBuilder;

import java.lang.annotation.Annotation;

public class GlobTypeLoaderFactory {
   static Object LOCK = new Object();
   static FieldInitializeProcessorService processorService;

   public static void createAndLoad(Class<?> targetClass) {
      create(targetClass, null, null).load();
   }

   public static GlobTypeLoader create(Class<?> targetClass) {
      return create(targetClass, null, null);
   }

   public static GlobTypeLoader create(Class<?> targetClass, String name) {
      return create(targetClass, null, name);
   }

   public static GlobTypeLoader create(Class<?> targetClass, String modelName, String name) {
      initProcessorService();
      return new GlobTypeLoader(targetClass, modelName, name, processorService);
   }

   static public FieldInitializeProcessorService getProcessorService() {
      if (processorService == null) {
         FieldInitializeProcessorServiceImpl service = new FieldInitializeProcessorServiceImpl();
         addProcessorService(service);
         processorService = service;
      }
      return processorService;
   }

   private static void initProcessorService() {
      if (processorService == null) {
         FieldInitializeProcessorServiceImpl newProcessorService = null;
         synchronized (LOCK) {
            if (processorService == null) {
               newProcessorService = new FieldInitializeProcessorServiceImpl();
               addProcessorService(newProcessorService);
            }
         }
         if (processorService == null && newProcessorService != null) {
            processorService = newProcessorService;
         }
      }
   }

   private static void addProcessorService(FieldInitializeProcessorServiceImpl newProcessorService) {
      newProcessorService.add(Link.class, UnInitializedLink::new);
      newProcessorService.add(DirectLink.class, UnInitializedLink::new);
      newProcessorService.add(Key.class, (type, annotations, nativeAnnotations) -> {
         for (Annotation annotation : nativeAnnotations) {
            if (annotation.annotationType().equals(InitUniqueKey.class)){
               return KeyBuilder.newEmptyKey(type);
            }
         }
         return null;
      });
      newProcessorService.add(Glob.class, (type, annotations, nativeAnnotations) -> {
         for (Annotation annotation : nativeAnnotations) {
            if (annotation.annotationType().equals(InitUniqueGlob.class)){
               return type.instantiate();
            }
         }
         return null;
      });
      newProcessorService.add(Glob.class, (type, annotations, nativeAnnotations) -> {
         for (Annotation annotation : nativeAnnotations) {
            if (annotation.annotationType().equals(KeyIndex.class)){
               return type.instantiate().set(KeyAnnotationType.INDEX, ((KeyIndex)annotation).value());
            }
         }
         return null;
      });
   }
}

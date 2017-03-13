package org.globsframework.metamodel;

import org.globsframework.metamodel.index.Index;
import org.globsframework.metamodel.index.MultiFieldIndex;
import org.globsframework.metamodel.properties.PropertyHolder;
import org.globsframework.metamodel.utils.Annotations;
import org.globsframework.model.GlobList;
import org.globsframework.model.Key;
import org.globsframework.model.MutableGlob;
import org.globsframework.utils.exceptions.ItemNotFound;

import java.lang.annotation.Annotation;
import java.util.Collection;

public interface GlobType extends PropertyHolder<GlobType>, Annotations {

    String getName();

    Field getField(String name) throws ItemNotFound;

    Field findField(String name);

    boolean hasField(String name);

    Field[] getFields();

    Field getField(int index);

    int getFieldCount();

    Field[] getKeyFields();

    Link getOutboundLink(String name);

    Link findOutboundLink(String name);

    Link[] getOutboundLinks();

    Link[] getInboundLinks();

    Field[] getFieldsWithAnnotation(Class<? extends Annotation> annotationClass);

    Field getFieldWithAnnotation(Key key);

    Field[] getFieldsWithAnnotation(Key key);

    GlobList getConstants();

    Collection<Index> getIndices();

    Collection<MultiFieldIndex> getMultiFieldIndices();

    MutableGlob instantiate();

}

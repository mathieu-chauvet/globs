package org.globsframework.metamodel.utils;

import org.globsframework.model.Glob;
import org.globsframework.model.GlobList;
import org.globsframework.model.Key;

import java.util.List;

public interface Annotations {

    boolean hasAnnotation(Key key);

    Glob getAnnotation(Key key);

    Glob findAnnotation(Key key);

    List<Glob> list();
}

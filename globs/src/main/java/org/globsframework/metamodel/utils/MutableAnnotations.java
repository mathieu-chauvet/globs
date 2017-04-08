package org.globsframework.metamodel.utils;

import org.globsframework.model.Glob;

public interface MutableAnnotations<T extends MutableAnnotations> extends Annotations {

   T addAnnotation(Glob glob);
}

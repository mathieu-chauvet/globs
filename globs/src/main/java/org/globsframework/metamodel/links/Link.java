package org.globsframework.metamodel.links;

import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.utils.Annotations;
import org.globsframework.model.Glob;
import org.globsframework.model.Key;

// introduce typeded link => containmentLink, 1->1, 1->N, M->N, ... hierarchical.
public interface Link extends Annotations {

    GlobType getSourceType();

    GlobType getTargetType();

    String getLinkModelName();

    String getName();

   boolean isRequired();

    <T extends FieldMappingFunction> T apply(T functor);

    boolean isContainment();

   Key getTargetKey(Glob source);
}

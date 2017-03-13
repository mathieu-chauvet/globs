package org.globsframework.metamodel;

import org.globsframework.metamodel.links.FieldMappingFunctor;
import org.globsframework.metamodel.utils.Annotations;

// introduce typeded link => containmentLink, 1->1, 1->N, M->N, ... hierarchical.
public interface Link extends Annotations {

    GlobType getSourceType();

    GlobType getTargetType();

    String getLinkModelName();

    String getName();

    String getFullName();

    boolean isRequired();

    void apply(FieldMappingFunctor functor);

    boolean isContainment();
}

package org.globsframework.metamodel.link;

import org.globsframework.metamodel.Field;
import org.globsframework.metamodel.GlobType;
import org.globsframework.metamodel.Link;
import org.globsframework.metamodel.links.FieldMappingFunctor;
import org.globsframework.metamodel.utils.DefaultAnnotations;

public class DefaultDirectLink extends DefaultAnnotations implements Link {
    private final Field directField;
    private final Field targetField;
    private final String modelName;
    private final String name;
    private final String fullName;

    public DefaultDirectLink(Field directField, Field targetField, String modelName, String name, String fullName) {
        this.directField = directField;
        this.targetField = targetField;
        this.modelName = modelName;
        this.name = name;
        this.fullName = fullName;
    }

    public GlobType getSourceType() {
        return directField.getGlobType();
    }

    public GlobType getTargetType() {
        return targetField.getGlobType();
    }

    public String getLinkModelName() {
        return modelName;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean isRequired() {
        return false;
    }

    public void apply(FieldMappingFunctor functor) {
        functor.process(directField, targetField);
    }

    public boolean isContainment() {
        return false;
    }
}

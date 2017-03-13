package org.globsframework.metamodel;

public class EmptyGlobLinkModel implements GlobLinkModel {
    public static final GlobLinkModel EMPTY = new EmptyGlobLinkModel();
    public Link[] getLinks(GlobType globType) {
        return new Link[0];
    }
}

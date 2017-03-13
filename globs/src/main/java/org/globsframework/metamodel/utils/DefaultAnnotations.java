package org.globsframework.metamodel.utils;

import org.globsframework.model.Glob;
import org.globsframework.model.Key;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultAnnotations implements Annotations {
    Map<Key, Glob> annotations = new HashMap<Key, Glob>();

    public void add(Glob glob){
        annotations.put(glob.getKey(), glob);
    }

    public boolean hasAnnotation(Key key) {
        return false;
    }

    public Glob getAnnotation(Key key) {
        return null;
    }

    public Glob findAnnotation(Key key) {
        return null;
    }

    public List<Glob> list() {
        return null;
    }
}

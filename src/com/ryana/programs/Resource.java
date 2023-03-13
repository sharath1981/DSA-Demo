package com.ryana.programs;

import java.util.Objects;

public class Resource implements Cloneable {
    private static volatile Resource resource;

    private Resource() {
    }

    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning is not allowed for singleton class");
    }

    public static Resource getInstance() {

        if (Objects.isNull(resource)) {
            synchronized (Resource.class) {
                if (Objects.isNull(resource)) {
                    resource = new Resource();
                }
            }
        }
        return resource;
    }
}

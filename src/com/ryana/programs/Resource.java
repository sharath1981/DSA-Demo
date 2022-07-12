package com.ryana.programs;

import java.util.Objects;

public class Resource {
    private static volatile Resource resource;

    private Resource() {
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

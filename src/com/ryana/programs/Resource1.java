package com.ryana.programs;

//Bill Pughs implementation of Singleton with static holder pattern
public class Resource1 implements Cloneable {

    private Resource1() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning is not allowed for singleton class");
    }

    // using static holder class
    static final class Holder {
        static final Resource1 INSTANCE = new Resource1();
    }

    // using holder interface
    static interface Holder1 {
        Resource1 INSTANCE = new Resource1();
    }
}

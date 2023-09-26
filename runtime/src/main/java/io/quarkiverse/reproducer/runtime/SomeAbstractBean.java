package io.quarkiverse.reproducer.runtime;

import org.jboss.logging.Logger;

import io.quarkiverse.reproducer.SomeInterface;

public abstract class SomeAbstractBean implements SomeInterface {
    private final Logger log = Logger.getLogger(SomeAbstractBean.class);
    private final String value;

    protected SomeAbstractBean(String value) {
        this.value = value;
    }

    @Override
    public void doSomething() {
        log.info(value);
    }
}

package io.quarkiverse.reproducer.runtime;

import io.quarkus.runtime.StartupEvent;
import jakarta.annotation.Priority;
import jakarta.enterprise.event.Observes;
import org.jboss.logging.Logger;

import io.quarkiverse.reproducer.SomeInterface;

public abstract class SomeAbstractBean implements SomeInterface {
    private final Logger log = Logger.getLogger(SomeAbstractBean.class);
    private final String value;

    protected SomeAbstractBean(String value) {
        this.value = value;
    }

    void onStart(@Observes @Priority(150) StartupEvent ev) {
        doSomething();
    }

    @Override
    public void doSomething() {
        log.info(value);
    }
}

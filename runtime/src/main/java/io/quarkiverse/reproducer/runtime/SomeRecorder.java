package io.quarkiverse.reproducer.runtime;

import java.util.function.Supplier;

import io.quarkiverse.reproducer.SomeInterface;
import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class SomeRecorder {
    public Supplier<SomeInterface> createASomeInterface() {
        return ABean::new;
    }

    public Supplier<SomeInterface> createBSomeInterface() {
        return BBean::new;
    }
}

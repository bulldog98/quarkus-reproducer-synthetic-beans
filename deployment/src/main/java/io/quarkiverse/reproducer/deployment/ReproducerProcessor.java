package io.quarkiverse.reproducer.deployment;

import jakarta.inject.Singleton;

import io.quarkiverse.reproducer.runtime.SomeRecorder;
import io.quarkus.arc.deployment.SyntheticBeanBuildItem;
import io.quarkus.arc.processor.DotNames;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.annotations.Record;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class ReproducerProcessor {

    private static final String FEATURE = "reproducer";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    void configureSyntheticBeans(
            BuildProducer<SyntheticBeanBuildItem> syntheticBeansProducer,
            SomeRecorder someRecorder) {
        syntheticBeansProducer.produce(
                SyntheticBeanBuildItem.configure(io.quarkiverse.reproducer.SomeInterface.class)
                        .scope(Singleton.class)
                        .addQualifier().annotation(DotNames.NAMED).addValue("value", "Other").done()
                        .supplier(someRecorder.createASomeInterface())
                        .setRuntimeInit()
                        .done());
        syntheticBeansProducer.produce(
                SyntheticBeanBuildItem.configure(io.quarkiverse.reproducer.SomeInterface.class)
                        .scope(Singleton.class)
                        .alternative(true)
                        .supplier(someRecorder.createBSomeInterface())
                        .setRuntimeInit()
                        .done());
    }
}

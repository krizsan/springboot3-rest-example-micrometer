package se.ivankrizsan.restexample;

import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.aop.ObservedAspect;
import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.annotation.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Configuration related to tracing.
 *
 */
@Configuration(proxyBeanMethods = false)
public class TracingConfiguration {
    /**
     * Aspect required in order to be able to use the @Observed annotation.
     *
     * @param inObservationRegistry Registry for managing state of observations.
     * @return Aspect for intercepting calls to classes and methods annotated with @Observed.
     */
    @Bean
    ObservedAspect observedAspect(final ObservationRegistry inObservationRegistry) {
        return new ObservedAspect(inObservationRegistry);
    }

    /**
     * Parser that parses information from @NewSpan annotations.
     *
     * @return NewSpan parser.
     */
    @Bean
    NewSpanParser newSpanParser() {
        return new DefaultNewSpanParser();
    }

    /**
     * Processor that processes invocations of methods annotated with @NewSpan.
     *
     * @param inNewSpanParser Parser that parses information from @NewSpan annotations.
     * @param inTracer Tracer.
     * @param inBeanFactory Spring bean factory.
     * @return @NewSpan method invocation processor.
     */
    @Bean
    MethodInvocationProcessor methodInvocationProcessor(
        final NewSpanParser inNewSpanParser, final Tracer inTracer, final BeanFactory inBeanFactory) {
        return new ImperativeMethodInvocationProcessor(
            inNewSpanParser, inTracer, inBeanFactory::getBean, inBeanFactory::getBean);
    }

    /**
     * Aspect required in order to be able to use the @NewSpan and @ContinueSpan annotations.
     *
     * @param inMethodInvocationProcessor Processes invocations of annotated methods.
     * @return Aspect for intercepting calls to methods annotated with @NewSpan and @ContinueSpan.
     */
    @Bean
    SpanAspect spanAspect(final MethodInvocationProcessor inMethodInvocationProcessor) {
        return new SpanAspect(inMethodInvocationProcessor);
    }
}

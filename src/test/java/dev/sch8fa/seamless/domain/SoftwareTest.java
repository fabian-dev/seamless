package dev.sch8fa.seamless.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

class SoftwareTest {

    private Software sut;

    @BeforeEach
    void setUp() {
        sut = new Software("some software");
    }

    @Test
    void newObservedCompatibilityIsAdded() {

        sut.observeCompatibility(new NewCompatibility("some software", "1.0.0", "some component", "3.2.1"));

        assertThat(sut.getCompatibilities()).hasSize(1);

        var actual = sut.getCompatibilities().get(0);
        assertThat(actual.getSoftwareVersion()).isEqualTo("1.0.0");
        assertThat(actual.getComponentName()).isEqualTo("some component");
        assertThat(actual.getComponentVersion()).isEqualTo("3.2.1");
        assertThat(actual.getObservedAt()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
    }

    @Test
    void againObservedCompatibilityHasNewTimestamp() {

        sut.observeCompatibility(new NewCompatibility("some software", "1.0.0", "some component", "3.2.1"));
        sut.getCompatibilities().get(0).setObservedAt(LocalDateTime.of(2000, 1, 1, 0, 0));

        sut.observeCompatibility(new NewCompatibility("some software", "1.0.0", "some component", "3.2.1"));

        var actual = sut.getCompatibilities().get(0);
        assertThat(actual.getSoftwareVersion()).isEqualTo("1.0.0");
        assertThat(actual.getComponentName()).isEqualTo("some component");
        assertThat(actual.getComponentVersion()).isEqualTo("3.2.1");
        assertThat(actual.getObservedAt()).isCloseTo(LocalDateTime.now(), within(1, ChronoUnit.SECONDS));
    }

}
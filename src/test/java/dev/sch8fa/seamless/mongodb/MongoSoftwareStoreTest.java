package dev.sch8fa.seamless.mongodb;

import dev.sch8fa.seamless.Software;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static com.google.common.collect.Lists.newArrayList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MongoSoftwareStoreTest {

    private MongoSoftwareStore sut;

    private MongoSoftwareRepository repository;

    @BeforeEach
    void setUp() {
        repository = mock(MongoSoftwareRepository.class);

        sut = new MongoSoftwareStore(repository);
    }

    @Test
    void findsExisting() {

        var toFind = new Software("some name");

        when(repository.findByNameIgnoreCase("some name")).thenReturn(newArrayList(toFind));

        var actual = sut.find("some name");

        Assertions.assertThat(actual).isEqualTo(toFind);
    }

    @Test
    void createsNewIfCannotFind() {

        var actual = sut.find("unknown");

        Assertions.assertThat(actual.getId()).isNull();
    }
}
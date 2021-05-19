package dev.sch8fa.seamless.mongodb;

import dev.sch8fa.seamless.Software;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
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

        var toFind = new Software("some id", "some name", newArrayList());

        when(repository.findByNameIgnoreCase("some name")).thenReturn(newArrayList(toFind));

        var actual = sut.findOrCreate("some name");

        assertThat(actual).isEqualTo(toFind);
    }

    @Test
    void createsNewIfCannotFind() {

        var actual = sut.findOrCreate("unknown");

        assertThat(actual.getId()).isNull();
        assertThat(actual.getName()).isEqualTo("unknown");
    }
}
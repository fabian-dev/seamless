package dev.sch8fa.seamless.mongodb;

import dev.sch8fa.seamless.domain.Software;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

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
    void createsNewIfNotFound() {

        var actual = sut.findOrCreate("unknown");

        assertThat(actual.getId()).isNull();
        assertThat(actual.getName()).isEqualTo("unknown");
    }

    @Test
    void delegatesSave() {

        sut.save(new Software("some id", "some name", newArrayList()));

        verify(repository, times(1)).save(argThat(s -> s.getId().equals("some id")));
    }
}
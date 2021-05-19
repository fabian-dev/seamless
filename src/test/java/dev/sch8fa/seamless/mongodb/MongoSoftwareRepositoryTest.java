package dev.sch8fa.seamless.mongodb;

import dev.sch8fa.seamless.Software;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataMongoTest
class MongoSoftwareRepositoryTest {

    @Container
    private static final MongoDBContainer mongoDb = new MongoDBContainer("mongo:4.4");

    @SuppressWarnings("unused")
    @DynamicPropertySource
    private static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDb::getReplicaSetUrl);
    }

    @Autowired
    private MongoSoftwareRepository sut;

    @Test
    void savesAndFinds() {

        var software = new Software("some name");

        assertThat(software.getId()).isNull();

        sut.save(software);

        var actual = sut.findByNameIgnoreCase("SOME NAME");

        assertThat(actual).hasSize(1);
        assertThat(actual.get(0).getId()).isNotBlank();
    }
}
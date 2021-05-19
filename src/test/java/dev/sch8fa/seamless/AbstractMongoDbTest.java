package dev.sch8fa.seamless;

import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class AbstractMongoDbTest {

    @Container
    private static final MongoDBContainer mongoDb = new MongoDBContainer("mongo:4.4");

    @SuppressWarnings("unused")
    @DynamicPropertySource
    private static void configure(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDb::getReplicaSetUrl);
    }

}
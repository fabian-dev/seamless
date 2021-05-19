package dev.sch8fa.seamless.mongodb;

import dev.sch8fa.seamless.AbstractMongoDbTest;
import dev.sch8fa.seamless.domain.Software;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class MongoSoftwareRepositoryTest extends AbstractMongoDbTest {

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
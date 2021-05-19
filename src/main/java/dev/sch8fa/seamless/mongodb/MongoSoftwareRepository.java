package dev.sch8fa.seamless.mongodb;

import dev.sch8fa.seamless.domain.Software;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MongoSoftwareRepository extends MongoRepository<Software, String> {

    List<Software> findByNameIgnoreCase(String name);
}

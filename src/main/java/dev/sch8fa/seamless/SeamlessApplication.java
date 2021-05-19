package dev.sch8fa.seamless;

import dev.sch8fa.seamless.mongodb.MongoSoftwareRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.MoreCollectors.onlyElement;

@SpringBootApplication
public class SeamlessApplication implements ApplicationRunner {

    private final MongoSoftwareRepository repository;

    public SeamlessApplication(MongoSoftwareRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SeamlessApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {

        repository.deleteAll();

        repository.save(new Software("lza"));

        var lza = repository.findByNameIgnoreCase("lza").stream().collect(onlyElement());

        lza.getCompatibilities().add(new Compatibility(LocalDateTime.now(), "JBoss", "7.3.5", "10.0.0"));
        lza.getCompatibilities().add(new Compatibility(LocalDateTime.now(), "JDK", "Oracle JDK 11", "10.0.0"));

        repository.save(lza);

        lza = repository.findByNameIgnoreCase("lza").stream().collect(onlyElement());

        lza.getCompatibilities().remove(0);
        lza.getCompatibilities().get(0).observed();

        repository.save(lza);

    }
}

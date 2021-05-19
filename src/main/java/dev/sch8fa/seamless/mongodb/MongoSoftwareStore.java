package dev.sch8fa.seamless.mongodb;

import dev.sch8fa.seamless.Software;
import dev.sch8fa.seamless.SoftwareStore;
import org.springframework.stereotype.Component;

@Component
public class MongoSoftwareStore implements SoftwareStore {

    private final MongoSoftwareRepository repository;

    public MongoSoftwareStore(MongoSoftwareRepository repository) {
        this.repository = repository;
    }

    @Override
    public Software find(String name) {
        var found = repository.findByNameIgnoreCase(name);
        if (found.size() == 1) {
            return found.get(0);
        }
        return new Software(name);
    }

    @Override
    public void save(Software software) {
        repository.save(software);
    }
}

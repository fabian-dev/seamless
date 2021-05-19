package dev.sch8fa.seamless.mongodb;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import javax.annotation.PostConstruct;

@Configuration
public class MongoConfiguration {

    private final MappingMongoConverter mappingMongoConverter;

    public MongoConfiguration(MappingMongoConverter mappingMongoConverter) {
        this.mappingMongoConverter = mappingMongoConverter;
    }

    @PostConstruct
    public void noClassColumn() {
        // see https://stackoverflow.com/a/65853283
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
    }

}

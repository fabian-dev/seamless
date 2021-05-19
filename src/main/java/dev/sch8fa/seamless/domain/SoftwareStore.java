package dev.sch8fa.seamless.domain;

public interface SoftwareStore {

    Software findOrCreate(String name);

    void save(Software software);

}

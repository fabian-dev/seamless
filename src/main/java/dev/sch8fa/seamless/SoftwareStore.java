package dev.sch8fa.seamless;

public interface SoftwareStore {

    Software findOrCreate(String name);

    void save(Software software);

}

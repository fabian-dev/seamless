package dev.sch8fa.seamless;

public interface SoftwareStore {

    Software find(String name);

    void save(Software software);

}

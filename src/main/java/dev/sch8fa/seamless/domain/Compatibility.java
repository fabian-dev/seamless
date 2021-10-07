package dev.sch8fa.seamless.domain;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
public class Compatibility {
    @NonNull
    private LocalDateTime observedAt;
    @NonNull
    private String componentName;
    @NonNull
    private String componentVersion;
    @NonNull
    private String softwareVersion;

    public static Compatibility from(NewCompatibility newCompatibility) {
        return new Compatibility(LocalDateTime.now(), newCompatibility.componentName(), newCompatibility.componentVersion(), newCompatibility.softwareVersion());
    }
}

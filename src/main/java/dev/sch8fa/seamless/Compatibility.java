package dev.sch8fa.seamless;

import lombok.*;

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

    public void observed() {
        observedAt = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
    }
}

package dev.sch8fa.seamless.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import javax.validation.constraints.NotNull;

// annotation can be removed after spring boot bumps jackson-databind to at least 2.12.0
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record NewCompatibility(@NotNull String softwareName,
                               @NotNull String softwareVersion,
                               @NotNull String componentName,
                               @NotNull String componentVersion) {
}

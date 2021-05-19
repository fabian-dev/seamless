package dev.sch8fa.seamless;

import javax.validation.constraints.NotNull;

public record NewCompatibility(@NotNull String componentName, @NotNull String componentVersion,
                               @NotNull String softwareVersion) {
}

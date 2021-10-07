package dev.sch8fa.seamless.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public record NewCompatibility(@NotNull @NotEmpty String softwareName,
                               @NotNull @NotEmpty String softwareVersion,
                               @NotNull @NotEmpty String componentName,
                               @NotNull @NotEmpty String componentVersion) {
}

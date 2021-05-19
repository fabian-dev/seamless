package dev.sch8fa.seamless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/api")
public class Api {

    private static final Logger logger = LoggerFactory.getLogger(Api.class);

    private final SoftwareStore store;

    public Api(SoftwareStore store) {
        this.store = store;
    }

    @PutMapping("/{softwareName}")
    public void compatibilityObserved(@RequestBody @Valid NewCompatibility newCompatibility,
                                      @PathVariable @NotNull @NotEmpty String softwareName) {

        var software = store.findOrCreate(softwareName);

        software.observeCompatibility(newCompatibility);

        store.save(software);

        logger.info("Updated {} with new compatibility {}", softwareName, newCompatibility.toString());
    }

}

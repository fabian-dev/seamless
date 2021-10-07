package dev.sch8fa.seamless;

import dev.sch8fa.seamless.domain.Compatibility;
import dev.sch8fa.seamless.domain.NewCompatibility;
import dev.sch8fa.seamless.domain.SoftwareStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class Api {

    private static final Logger logger = LoggerFactory.getLogger(Api.class);

    private final SoftwareStore store;

    public Api(SoftwareStore store) {
        this.store = store;
    }

    @GetMapping("/{name}")
    public List<Compatibility> forSoftwareName(@PathVariable String name) {
        return store.findOrCreate(name).getCompatibilities();
    }

    @PostMapping("/")
    public void compatibilityObserved(@RequestBody @Valid NewCompatibility newCompatibility) {

        var software = store.findOrCreate(newCompatibility.softwareName());

        software.observeCompatibility(newCompatibility);

        store.save(software);

        logger.info("Updated {} with new compatibility {}", newCompatibility.softwareName(), newCompatibility);
    }

}

package dev.sch8fa.seamless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class Api {

    private static final Logger logger = LoggerFactory.getLogger(Api.class);

    private final SoftwareStore store;

    public Api(SoftwareStore store) {
        this.store = store;
    }

    @PutMapping("/")
    public void compatibilityObserved(@RequestBody @Valid NewCompatibility newCompatibility) {

        var software = store.find("lza");

        software.observeCompatibility(newCompatibility);

        store.save(software);

        logger.info("Updated {} with new compatibility {}", "lza", newCompatibility.toString());

    }

}

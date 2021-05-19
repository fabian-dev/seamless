package dev.sch8fa.seamless.domain;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Value
@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
public class Software {
    @Id
    String id;
    @NonNull
    String name;
    @NonNull
    List<Compatibility> compatibilities;

    public Software(String name) {
        this(null, name, newArrayList());
    }

    public void observeCompatibility(NewCompatibility newCompatibility) {
        // TODO
    }
}

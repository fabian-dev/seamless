package dev.sch8fa.seamless.domain;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;

import java.time.LocalDateTime;
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

        var alreadyObserved = compatibilities.stream().filter(c -> areSame(c, newCompatibility)).findFirst();

        if (alreadyObserved.isPresent()) {
            alreadyObserved.get().setObservedAt(LocalDateTime.now());
        } else {
            compatibilities.add(Compatibility.from(newCompatibility));
        }
    }

    private static boolean areSame(Compatibility compatibility, NewCompatibility newCompatibility) {
        return compatibility.getSoftwareVersion().equals(newCompatibility.softwareVersion())
                && compatibility.getComponentName().equals(newCompatibility.componentName())
                && compatibility.getComponentVersion().equals(newCompatibility.componentVersion());
    }

}

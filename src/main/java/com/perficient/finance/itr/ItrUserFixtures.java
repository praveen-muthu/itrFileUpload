package com.perficient.finance.itr;

import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.asList;

@Component
public class ItrUserFixtures {

    public List<ItrUserStore> load() {
        return asList(
            new ItrUserStore("Massive Attack", "Mezzanine", 1998, 9),
            new ItrUserStore("Radiohead", "OK Computer", 1997, 8),
            new ItrUserStore("Radiohead", "Kid A", 2000, 9)
        );
    }
}

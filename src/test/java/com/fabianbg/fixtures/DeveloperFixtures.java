package com.fabianbg.fixtures;

import java.util.Arrays;
import java.util.List;

import com.fabianbg.domain.dto.DeveloperUpdateDTO;
import com.fabianbg.domain.model.Developer;

public class DeveloperFixtures {

    public static Developer newDeveloper() {
        return new Developer("001", "dev", "link", new String[] { "1", "2" });
    }

    public static DeveloperUpdateDTO newDeveloperUpdate() {
        return new DeveloperUpdateDTO("update_name", "update_link", new String[] { "2" });
    }

    public static Developer newDeveloperUpdated() {
        return new Developer("001", "update_name", "update_link", new String[] { "2" });
    }

    public static List<Developer> newListDevelopers() {
        return Arrays.asList(new Developer("001", "dev", "link", new String[] { "1", "2" }),
                new Developer("002", "dev2", "link2", new String[] { "1" }));
    }

    public static Iterable<Developer> newIterableDevelopers() {
        return Arrays.asList(new Developer("001", "dev", "link", new String[] { "1", "2" }),
                new Developer("002", "dev2", "link2", new String[] { "1" }));
    }
}
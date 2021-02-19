package collections;

import java.util.*;

public class UniqueWordExtractor {

    public Set<String> fetchUniqueWordsInOrder(String input, String sort) {
        if (sort == null) {
            sort = SortOrder.INPUT_ORDER;
        }

        if (input == null || input.isBlank()) {
            return new HashSet<String>();
        }

        switch (sort) {
            case SortOrder.INPUT_ORDER: {
                LinkedHashSet<String> defaultSet = new LinkedHashSet<>();

                for (String word : input.trim().split("\\s+|\\.|,")) {
                    if (word.isBlank()) {
                        continue;
                    }
                    defaultSet.add(word);
                }
                System.out.println(defaultSet);
                return defaultSet;
            }
            case SortOrder.ALPHABETIC_ASCENDING: {
                TreeSet<String> alphaSet = new TreeSet<String>();

                for (String word : input.trim().split("\\s+|\\.|,")) {
                    if (word.isBlank()) {
                        continue;
                    }
                    alphaSet.add(word);
                }
                System.out.println(alphaSet);
                return alphaSet;
            }
            case SortOrder.ALPHABETIC_DESCENDING: {
                TreeSet<String> alphaDescSet = (TreeSet<String>) new UniqueWordExtractor().fetchUniqueWordsInOrder(input, SortOrder.ALPHABETIC_ASCENDING);
                return alphaDescSet.descendingSet();
            }
            case SortOrder.LENGTH_THEN_ALPHABETIC_ASCENDING: {
                return null;
            }

        }
        return null;
    }

}

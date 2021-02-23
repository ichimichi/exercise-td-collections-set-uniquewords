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
                return alphaSet;
            }
            case SortOrder.ALPHABETIC_DESCENDING: {
                TreeSet<String> alphaSet = (TreeSet<String>) new UniqueWordExtractor().fetchUniqueWordsInOrder(input, SortOrder.ALPHABETIC_ASCENDING);
                return alphaSet.descendingSet();
            }
            case SortOrder.LENGTH_THEN_ALPHABETIC_ASCENDING: {
                LinkedHashSet<String> defaultSet = (LinkedHashSet<String>) new UniqueWordExtractor().fetchUniqueWordsInOrder(input, SortOrder.INPUT_ORDER);
                ArrayList<String> wordList = new ArrayList<>(defaultSet);

                Comparator<String> lengthComparator = (String o1, String o2)-> (int) o1.length()-o2.length();
                Comparator<String> alphaComparator = (String o1, String o2)-> (int) o1.compareTo(o2);

                TreeSet<String> sortedSet = new TreeSet<>(lengthComparator.thenComparing(alphaComparator));
                sortedSet.addAll(wordList);

                return sortedSet;
            }

        }


        return null;
    }

}

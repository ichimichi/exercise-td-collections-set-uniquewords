package com.stackroute.collections;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class UniqueWordExtractorTests {

    private static final String MSG_01 = "unique words returned from the method should be in order as appearing in the paragraph";
    private static final String MSG_02 = "unique words returned from the method should be sorted alphabetically";
    private static final String MSG_03 = "unique words returned from the method should be sorted alphabetically in descending order";
    private static final String MSG_04 = "unique words returned from the method should be sorted based on the length of words, then alphabetically";

    private static final String PARAGRAPH = "When I got to the bottom of the stairs, I saw that the stairs were high and steep." +
            " I took a deep breath and began to climb the stairs. When I reached the top of the stairs I looked around me.";

    private UniqueWordExtractor uniqueWordExtractor;


    @BeforeEach
    public void setUp() {
        uniqueWordExtractor = new UniqueWordExtractor();
    }

    @AfterEach
    public void tearDown() {
        uniqueWordExtractor = null;
    }

    @Test
    public void givenAParagraphThenReturnASetWithUniqueWordsInInputOrder() {
        final Set<String> uniqueWords = uniqueWordExtractor.fetchUniqueWordsInOrder(PARAGRAPH, SortOrder.INPUT_ORDER);
        assertThat(uniqueWords, instanceOf(LinkedHashSet.class));
        assertThat(MSG_01, uniqueWords, contains("When", "I", "got", "to", "the", "bottom", "of", "stairs", "saw", "that", "were", "high", "and",
                "steep", "took", "a", "deep", "breath", "began", "climb", "reached", "top", "looked", "around", "me"));
    }

    @Test
    public void givenAParagraphWhenSortOrderIsNullThenReturnASetWithUniqueWordsInInputOrder() {
        final Set<String> uniqueWords = uniqueWordExtractor.fetchUniqueWordsInOrder(PARAGRAPH, null);
        assertThat(uniqueWords, instanceOf(LinkedHashSet.class));
        assertThat(MSG_01, uniqueWords, contains("When", "I", "got", "to", "the", "bottom", "of", "stairs", "saw", "that", "were", "high", "and",
                "steep", "took", "a", "deep", "breath", "began", "climb", "reached", "top", "looked", "around", "me"));
    }

    @Test
    public void givenAParagraphThenReturnASetWithUniqueWordsSortedAscendingAlphabetically() {
        final Set<String> uniqueWords = uniqueWordExtractor.fetchUniqueWordsInOrder(PARAGRAPH, SortOrder.ALPHABETIC_ASCENDING);
        assertThat(uniqueWords, instanceOf(TreeSet.class));
        assertThat(MSG_02, uniqueWords, contains("I", "When", "a", "and", "around", "began", "bottom", "breath", "climb",
                "deep", "got", "high", "looked", "me", "of", "reached", "saw", "stairs", "steep", "that", "the", "to", "took", "top", "were"));
    }

    @Test
    public void givenAParagraphThenReturnASetWithUniqueWordsSortedDescendingAlphabetically() {
        final Set<String> uniqueWords = uniqueWordExtractor.fetchUniqueWordsInOrder(PARAGRAPH, SortOrder.ALPHABETIC_DESCENDING);
        assertThat(uniqueWords, instanceOf(TreeSet.class));
        assertThat(MSG_03, uniqueWords, contains("were", "top", "took", "to", "the", "that", "steep", "stairs", "saw",
                "reached", "of", "me", "looked", "high", "got", "deep", "climb", "breath", "bottom", "began", "around", "and", "a", "When", "I"));
    }

    @Test
    public void givenAParagraphThenReturnASetWithUniqueWordsSortedBasedOnLengthThenAlphabetically() {
        final Set<String> uniqueWords = uniqueWordExtractor.fetchUniqueWordsInOrder(PARAGRAPH, SortOrder.LENGTH_THEN_ALPHABETIC_ASCENDING);
        assertThat(uniqueWords, instanceOf(TreeSet.class));
        assertThat(MSG_04, uniqueWords, contains("I", "a", "me", "of", "to", "and", "got", "saw", "the", "top", "When", "deep", "high", "that", "took",
                "were", "began", "climb", "steep", "around", "bottom", "breath", "looked", "stairs", "reached"));
    }

    @Test
    public void givenAParagraphWhenNullThenReturnAnEmptySet() {
        final Set<String> uniqueWords = uniqueWordExtractor.fetchUniqueWordsInOrder(null, null);
        assertThat(uniqueWords, is(notNullValue()));
        assertThat(uniqueWords, is(empty()));
    }

}
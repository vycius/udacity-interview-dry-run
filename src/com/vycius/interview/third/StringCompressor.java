package com.vycius.interview.third;

import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class StringCompressor {

    public static String compress(String input) {
        Deque<CharacterOccur> characterOccurs = input.chars().mapToObj(i -> (char) i)
                .reduce(new ArrayDeque<>(),
                        (charOcc, c) -> {
                            if(charOcc.isEmpty() || charOcc.getLast().character != c) {
                                charOcc.addLast(new CharacterOccur(c));
                            } else {
                                charOcc.getLast().occ++;
                            }
                            return charOcc;
                        },
                        (p1, p2) -> {
                            throw new UnsupportedOperationException("Combining is not supported. This shouldn't be used in parallel.");
                        }
                );

        String compressedString = characterOccurs.stream()
                .map(co -> co.character.toString() + co.occ)
                .collect(Collectors.joining(""));

        return (compressedString.length() < input.length()) ? compressedString : input;
    }

    private static class CharacterOccur {
        public Character character;
        public int occ;

        public CharacterOccur(Character character) {
            this.character = character;
            occ = 1;
        }
    }

    @RunWith(JUnit4.class)
    public static class StringCompressionTests {
        @Test
        public void compressString() {
            String input = "aabcccccaaa";
            String expected = "a2b1c5a3";
            String result = compress(input);

            assertEquals(expected, result);
        }

        @Test
        public void compressRepeatingString() {
            String input = "aaaaaaaaaaaaaaaaaaaaa";
            String expected = "a21";
            String result = compress(input);

            assertEquals(expected, result);
        }

        @Test
        public void compressOneCharString() {
            String input = "Z";
            String expected = "Z";
            String result = compress(input);

            assertEquals(expected, result);
        }

        @Test
        public void compressedStringWhichIsLongerAfterCompression() {
            String input = "ABCDEFFGHHHF";
            String expected = "ABCDEFFGHHHF";
            String result = compress(input);

            assertEquals(expected, result);
        }

        @Test
        public void compressEmptyString() {
            String input = "";
            String result = compress(input);

            assertEquals(input, result);
        }
    }

    @RunWith(JUnitQuickcheck.class)
    public static class StringCompressionProps {

        public static Set<Character> getDistinctChars(String input) {
            return input.chars().distinct().mapToObj(i -> (char) i).collect(Collectors.toSet());
        }

        @Property
        public void hasAllLetters(String input) {
            Set<Character> originalChars = getDistinctChars(input);

            String output = compress(input);

            Set<Character> compressedCharacters = getDistinctChars(output);

            originalChars.removeAll(compressedCharacters);
            assertEquals(originalChars.size(), 0);
        }

    }


}

package com.practise.playground.interview;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InterviewQuestionsTest {

    @Test
    @DisplayName("Test with standard vowels and positive rotation")
    void testStandardVowelsWithPositiveRotation() {
        String input = "hello world";
        String vowels = "aeiou";
        int k = 1;
        // Expected: 'e' -> 'i', 'o' -> 'u'
        String expected = "hillu wurld";

        assertEquals(expected, InterviewQuestions.vowelConversion(input, vowels, k));
    }

    @Test
    @DisplayName("Test with standard vowels and negative rotation")
    void testStandardVowelsWithNegativeRotation() {
        String input = "hello world";
        String vowels = "aeiou";
        int k = -1;
        // Expected: 'e' -> 'a', 'o' -> 'i'
        // For negative k, we need to ensure proper wraparound
        // -1 + 5 (vowel length) = 4, so negative rotation wraps correctly
        String expected = "halli wirld";

        assertEquals(expected, InterviewQuestions.vowelConversion(input, vowels, k));
    }

    @Test
    @DisplayName("Test with empty input string")
    void testEmptyInputString() {
        String input = "";
        String vowels = "aeiou";
        int k = 1;

        assertEquals("", InterviewQuestions.vowelConversion(input, vowels, k));
    }

    @Test
    @DisplayName("Test with empty vowels string")
    void testEmptyVowelsString() {
        String input = "hello world";
        String vowels = "";
        int k = 1;
        // No vowels to convert, so input remains unchanged

        assertEquals(input, InterviewQuestions.vowelConversion(input, vowels, k));
    }

    @Test
    @DisplayName("Test with no vowels in input")
    void testNoVowelsInInput() {
        String input = "xyz bcdfg";
        String vowels = "aeiou";
        int k = 1;
        // No vowels in input, so it remains unchanged

        assertEquals(input, InterviewQuestions.vowelConversion(input, vowels, k));
    }

    @Test
    @DisplayName("Test with all vowels in input")
    void testAllVowelsInInput() {
        String input = "aeiou";
        String vowels = "aeiou";
        int k = 1;
        // Each vowel shifts by 1: a->e, e->i, i->o, o->u, u->a
        String expected = "eioua";

        assertEquals(expected, InterviewQuestions.vowelConversion(input, vowels, k));
    }

    @Test
    @DisplayName("Test with k equal to vowel length (full cycle)")
    void testFullCycleRotation() {
        String input = "hello world";
        String vowels = "aeiou";
        int k = 5; // Same as vowel length
        // Full cycle means no change

        assertEquals(input, InterviewQuestions.vowelConversion(input, vowels, k));
    }

    @Test
    @DisplayName("Test with k larger than vowel length")
    void testLargeRotation() {
        String input = "hello world";
        String vowels = "aeiou";
        int k = 6; // vowel.length() + 1
        // Equivalent to k = 1 (6 % 5 = 1)
        String expected = "hillu wurld";

        assertEquals(expected, InterviewQuestions.vowelConversion(input, vowels, k));
    }

    @Test
    @DisplayName("Test with custom vowel set")
    void testCustomVowelSet() {
        String input = "xyz abc";
        String vowels = "abc"; // Here a, b, c are considered as vowels
        int k = 1;
        // Expected: 'a' -> 'b', 'b' -> 'c', 'c' -> 'a'
        String expected = "xyz bca";

        assertEquals(expected, InterviewQuestions.vowelConversion(input, vowels, k));
    }

    @Test
    @DisplayName("Test with uppercase vowels")
    void testUppercaseVowels() {
        String input = "HELLO WORLD";
        String vowels = "AEIOU"; // Uppercase vowels
        int k = 1;
        // 'E' -> 'I', 'O' -> 'U'
        String expected = "HILLU WURLD";

        assertEquals(expected, InterviewQuestions.vowelConversion(input, vowels, k));
    }

    @Test
    @DisplayName("Test mixed case vowels and input")
    void testMixedCaseVowelsAndInput() {
        String input = "Hello World";
        String vowels = "aeiouAEIOU"; // Both uppercase and lowercase vowels
        int k = 1;
        // 'e' -> 'i', 'o' -> 'u', 'O' -> 'U'
        String expected = "Hillu Wurld";

        assertEquals(expected, InterviewQuestions.vowelConversion(input, vowels, k));
    }

    @Test
    @DisplayName("Test zero rotation")
    void testZeroRotation() {
        String input = "hello world";
        String vowels = "aeiou";
        int k = 0;
        // No rotation, so input remains unchanged

        assertEquals(input, InterviewQuestions.vowelConversion(input, vowels, k));
    }

    @Test
    @DisplayName("Test rotation with repeating vowels")
    void testRepeatingVowels() {
        String input = "hello world";
        String vowels = "aeioua"; // 'a' appears twice
        int k = 1;
        // In this case, 'e' -> 'i', 'o' -> 'u'
        // Note: The behavior might depend on how indexOf handles duplicates
        String expected = "hillu wurld";

        assertEquals(expected, InterviewQuestions.vowelConversion(input, vowels, k));
    }

    @Test
    @DisplayName("Test with long input")
    void testLongInput() {
        // Create a long string with many vowels
        StringBuilder longInput = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longInput.append("aeiou");
        }
        String input = longInput.toString();
        String vowels = "aeiou";
        int k = 1;

        // Create expected output with each vowel shifted by 1
        StringBuilder expectedOutput = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            expectedOutput.append("eioua");
        }
        String expected = expectedOutput.toString();

        assertEquals(expected, InterviewQuestions.vowelConversion(input, vowels, k));
    }

    @Test
    @DisplayName("Test with multiple rotation cycles")
    void testMultipleRotationCycles() {
        String input = "hello world";
        String vowels = "aeiou";
        int k = 11; // 2 full cycles + 1
        // After 2 full cycles (k=10), additional k=1 rotation
        String expected = "hillu wurld";

        assertEquals(expected, InterviewQuestions.vowelConversion(input, vowels, k));
    }

    @ParameterizedTest
    @CsvSource({
            "hello, aeiou, 1, hillu",
            "test, aeiou, 2, tost",
            "aeiou, aeiou, 4, uaeio"
    })
    @DisplayName("Parameterized test with various inputs")
    void testParameterized(String input, String vowels, int k, String expected) {
        assertEquals(expected, InterviewQuestions.vowelConversion(input, vowels, k));
    }
}


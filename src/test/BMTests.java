package test;

import main.BoyerMoore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BMTests {

    private String text = "Hello, world!";

    @Test
    void testPattern1() {
        String pattern1 = "world";
        int expectedMatch1 = 7;  // "world" starts at index 7
        int match1 = BoyerMoore.search(pattern1, text);
        assertEquals(expectedMatch1, match1, "Matched at an incorrect position.");
    }

    @Test
    void testPattern2() {
        String pattern2 = "Hello";
        int expectedMatch2 = 0;  // "Hello" starts at index 0
        int match2 = BoyerMoore.search(pattern2, text);
        assertEquals(expectedMatch2, match2, "Matched at an incorrect position.");
    }

    @Test
    void testPattern3() {
        String pattern3 = "Java";
        int expectedMatch3 = -1; // "Java" is not found
        int match3 = BoyerMoore.search(pattern3, text);
        assertEquals(expectedMatch3, match3, "Matched at an incorrect position.");
    }
}

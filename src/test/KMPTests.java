package test;

import main.algorithms.KMP;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KMPTests {
    public String text = "Hello, world!";


    @Test
    public void test_1() {
        String pattern1 = "world";
        kpm_test_helper(pattern1, text, 7);
    }

    @Test
    public void test_2() {
        String pattern2 = "Hello";
        kpm_test_helper(pattern2, text, 0);
    }

    @Test
    public void test_3() {
        String pattern3 = "Java";
        kpm_test_helper(pattern3, text, -1);
    }

    @Test
    public void test_4() {
        String pattern = "Test4";
        kpm_test_helper(pattern, text, -1);
    }

    /**
     * Empty string test
     */
    @Test
    public void test_5() {
        String pattern = "";
        kpm_test_helper(pattern, text, -1);
    }

    @Test
    public void test_6() {
        String text = "This is a test!";
        String pattern = "This ";
        kpm_test_helper(pattern, text, 0);
    }

    @Test
    public void test_7() {
        String pattern = "world?";
        kpm_test_helper(pattern, text, -1);
    }

    @Test
    public void test_8() {
        String pattern = "world! Itsa me";
        kpm_test_helper(pattern, text, -1);
    }

    @Test
    public void test_9() {
        String pattern = "llo, w";
        kpm_test_helper(pattern, text, 2);
    }


    private void kpm_test_helper(String pattern, String text, int expected) {
        int match = KMP.search(pattern, text);
        assertEquals(match, expected);
    }

}

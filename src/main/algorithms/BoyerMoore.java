package main.algorithms;

import java.util.*;

public class BoyerMoore {

    public static int search(String pattern, String text) {
        if (pattern == null || pattern.length() == 0)
            return 0;

        if (text == null || text.length() == 0)
            return -1;

        Map<Character, Integer> badCharShift = preprocessBadCharShift(pattern);
        int shift = 0;

        while (text.length() - shift >= pattern.length()) {
            int i = pattern.length() - 1;
            while (text.charAt(shift + i) == pattern.charAt(i)) {
                if (i == 0) {
                    return shift;
                }
                i--;
            }

            shift += Math.max(1, i - getBadCharShift(badCharShift, text.charAt(shift + i)));
        }

        return -1;
    }

    private static Map<Character, Integer> preprocessBadCharShift(String pattern) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            map.put(pattern.charAt(i), i);
        }
        return map;
    }

    private static int getBadCharShift(Map<Character, Integer> map, char c) {
        return map.getOrDefault(c, -1);
    }
}

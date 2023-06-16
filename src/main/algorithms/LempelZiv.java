package main.algorithms;

import java.util.*;

public class LempelZiv {

    // Tuple class to store the compressed data
    public static class Tuple {
        int offset;
        int length;
        String nextChar;

        Tuple(int offset, int length, String nextChar) {
            this.offset = offset;
            this.length = length;
            this.nextChar = nextChar;
        }

        @Override
        public String toString() {
            return String.format("[%d|%d|%s]", offset, length, nextChar);
        }
    }

    /**
     * Take uncompressed input as a text string, compress it, and return it as a
     * text string.
     */
    public static String compress(String input) {
        System.out.println("Compressing: " + input);
        int cursor = 0;
        int windowSize = 100;
        List<Tuple> result = new ArrayList<>();

        while (cursor < input.length()) {
            int length = 0;
            int bestOffset = 0;
            String bestMatch = "";

            int searchStartIndex = Math.max(0, cursor - windowSize);
            String searchBuffer = input.substring(searchStartIndex, cursor);

            for (int i = searchBuffer.length(); i >= 0; i--) {
                String currentMatch = searchBuffer.substring(i);
                if (input.startsWith(currentMatch, cursor)) {
                    bestOffset = cursor - (searchStartIndex + i);
                    bestMatch = currentMatch;
                    length = currentMatch.length();
                    break;
                }
            }

            String nextChar = cursor + length < input.length() ? String.valueOf(input.charAt(cursor + length)) : null;
            if (nextChar != null && nextChar.equals(" ")) {
                nextChar = "\\s";
            }
            result.add(new Tuple(bestOffset, length, nextChar));
            cursor += length + (nextChar != null ? 1 : 0);
        }

        return result.toString().replaceAll(", ", ",");
    }


    /**
     * Take compressed input as a text string, decompress it, and return it as a
     * text string.
     */
    public static String decompress(String compressed) {
        compressed = compressed.substring(1, compressed.length() - 1);
        String[] tuples = compressed.split("\\],\\[");

        StringBuilder output = new StringBuilder();
        int cursor = 0;
        for (String tuple : tuples) {
            tuple = tuple.replace("[", "").replace("]", "");

            String[] parts = tuple.split("\\|");
            int offset = Integer.parseInt(parts[0].trim());
            int length = Integer.parseInt(parts[1].trim());
            String nextChar = parts.length > 2 ? parts[2].trim() : null;
            if (nextChar != null && nextChar.equals("\\s")) {
                nextChar = " ";
            }

            if (offset == 0 && length == 0) {
                output.append(nextChar);
                cursor++;
            } else {
                for (int j = 0; j < length; j++) {
                    output.append(output.charAt(cursor - offset));
                    cursor++;
                }
                if (nextChar != null) {
                    output.append(nextChar);
                    cursor++;
                }
            }
        }

        return output.toString();
    }


    /**
     * The getInformation method is here for your convenience, you don't need to
     * fill it in if you don't want to. It is called on every run and its return
     * value is displayed on-screen. You can use this to print out any relevant
     * information from your compression.
     */
    public String getInformation() {
        return "";
    }
}

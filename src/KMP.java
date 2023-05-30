import java.util.Arrays;

/**
 * A new KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {


	/**
	 * Perform KMP substring search on the given text with the given pattern.
	 * <p>
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public static int search(String pattern, String text) {
		// TODO fill this in.
		int[] matchTable = buildMatchTable(pattern);

		Arrays.stream(matchTable).forEach(System.out::println);

		// Declare vars
		int k = 0;
		int i = 0;

		while ((k + i) < text.length()) {
			if (pattern.charAt(i) == text.charAt(k + 1)) { // match
				i++;
				if (i == matchTable.length) {
					return k;
				} // found S
				else if (matchTable[i] == -1) {
					k += i + 1;
					i = 0;
				} else {
					k = k + i - matchTable[i];
					i = matchTable[i];
				}

			}
		}

		return -1;
	}

	private static int[] buildMatchTable(String pattern) {
		// Initalise
		int[] matchTable = new int[pattern.length()];
		matchTable[0] = -1;
		matchTable[1] = 0;
		int j = 0; // position in prefix
		int pos = 2; // position in table

		while (pos < pattern.length()) {
			if (pattern.charAt(pos - 1) == pattern.charAt(j)) {
				matchTable[pos] = j + 1;
				pos++;
				j++;
			} else if (j > 0) {
				j = matchTable[j]; // mismatch, restart the prefix
			} else { // run out of candidate prefixes
				matchTable[pos] = 0;
				pos++;
			}
		}

		return matchTable;
	}
}

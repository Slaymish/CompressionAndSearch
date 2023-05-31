package main.algorithms;

/**
 * A new java.assignment.KMP instance is created for every substring search performed. Both the
 * pattern and the text are passed to the constructor and the search method. You
 * could, for example, use the constructor to create the match table and the
 * search method to perform the search itself.
 */
public class KMP {


	/**
	 * Perform java.assignment.KMP substring search on the given text with the given pattern.
	 * <p>
	 * This should return the starting index of the first substring match if it
	 * exists, or -1 if it doesn't.
	 */
	public static int search(String pattern, String text) {
		// TODO fill this in.
		int[] matchTable = buildMatchTable(pattern);

		printMatchTable(matchTable);

		/*
		input: string S[0..m-1], text T[0 .. n-1], partial match table M[0 .. m-1]
		output: the position in T at which S is found, or -1 if not present

		// declare vars
		k = 0; // start of current match in T
		i = 0; // pos of current character in S

		// loop
		while ((k+i)<n){
			if S[i] = T[k+i] then // match
				i = i + 1
				if i=m then return k // found S
			else if M[i] = -1 then // mismatch, no self overlap
				k = k + i + 1
				i = 0
			else // mismatch, with self overlap
				k = k + i - M[i] // match pos jumps forward
				i = M[i]
		}

		return -1 // failed to find S
		 */

		// Declare vars
		int k = 0;
		int i = 0;
		int n = text.length() - 1;
		while ((k + i) < n) {
			if (pattern.charAt(i) == text.charAt(k + 1)) { // match
				i++;
				if (i == matchTable.length) {
					System.out.println("found k: " + k);
					return k;
				}
			}// found S
			else if (matchTable[i] == -1) {
				k += i + 1;
				i = 0;
			} else {
				k = (k + i) - matchTable[i];
				i = matchTable[i];
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

	private static void printMatchTable(int[] matchTable) {
		System.out.println("Match table:");
		for (int i = 0; i < matchTable.length; i++) {
			System.out.print(i + " ");
		}
		System.out.println();
		for (int i = 0; i < matchTable.length; i++) {
			System.out.print(matchTable[i] + " ");
		}
	}
}

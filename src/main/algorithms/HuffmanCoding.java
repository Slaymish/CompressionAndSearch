package main.algorithms;

import java.util.*;

public class HuffmanCoding {

	private final Node root;
	private final Map<Character, String> charPrefixHashMap = new HashMap<>();

	private class Node implements Comparable<Node> {
		char ch;
		int freq;
		Node left = null, right = null;

		/**
		 * Constructor used for symbol nodes
		 *
		 * @param ch
		 * @param freq
		 */
		Node(char ch, int freq) {
			this.ch = ch;
			this.freq = freq;
		}

		/**
		 * Constructor used for internal nodes
		 *
		 * @param ch
		 * @param freq
		 * @param left
		 * @param right
		 */
		public Node(char ch, int freq, Node left, Node right) {
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}

		public boolean isLeaf() {
			return this.left == null && this.right == null;
		}

		public int compareTo(Node o) {
			return this.freq - o.freq;
		}
	}

	/**
	 * A new instance of java.assignment.HuffmanCoding is created for every run. The constructor is
	 * passed the full text to be encoded or decoded, so this is a good place to
	 * construct the tree. You should store this tree in a field and then use it in
	 * the encode and decode methods.
	 */
	public HuffmanCoding(String text) {
		root = buildHuffmanTree(text);
		buildCode("", root);
	}

	/**
	 * Take a text string, text, and determine the frequency of occurrence of each
	 *
	 * @param text
	 * @return
	 */
	private Node buildHuffmanTree(String text) {
		Map<Character, Integer> freqMap = new HashMap<>();
		for (char c : text.toCharArray()) {
			freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
		}

		PriorityQueue<Node> pq = new PriorityQueue<>();

		// Create a leaf node for each character and add it
		for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
			pq.add(new Node(entry.getKey(), entry.getValue()));
		}

		// do till there is more than one node in the queue
		while (pq.size() != 1) {
			Node left = pq.poll();
			Node right = pq.poll();
			int sum = left.freq + right.freq;
			pq.add(new Node('\0', sum, left, right));
		}

		// root stores pointer to root of Huffman Tree
		return pq.peek();
	}

	/**
	 * Take a text string, text, and return the encoded binary string
	 *
	 * @param s
	 * @param node
	 */
	private void buildCode(String s, Node node) {
		if (!node.isLeaf()) {
			buildCode(s + '0', node.left);
			buildCode(s + '1', node.right);
		} else {
			charPrefixHashMap.put(node.ch, s);
		}
	}

	/**
	 * Take an input string, text, and encode it with the stored tree. Should
	 * return the encoded text as a binary string, that is, a string containing
	 * only 1 and 0.
	 *
	 * @param text
	 */
	public String encode(String text) {
		StringBuilder sb = new StringBuilder();

		for (char c : text.toCharArray()) {
			sb.append(charPrefixHashMap.get(c));
		}

		return sb.toString();
	}

	/**
	 * Take encoded input as a binary string, decode it using the stored tree,
	 * and return the decoded text as a text string.
	 */
	public String decode(String encoded) {
		StringBuilder sb = new StringBuilder();
		Node node = root;
		for (int i = 0; i < encoded.length(); i++) {
			node = encoded.charAt(i) == '0' ? node.left : node.right;
			if (node.isLeaf()) {
				sb.append(node.ch);
				node = root;
			}
		}
		return sb.toString();
	}

	/**
	 * The getInformation method is here for your convenience, you don't need to
	 * fill it in if you don't want to. It is called on every run and its return
	 * value is displayed on-screen. You could use this, for example, to print
	 * out the encoding tree.
	 */
	public String getInformation() {
		StringBuilder sb = new StringBuilder();
		sb.append("There are " + charPrefixHashMap.size() + " distinct characters.\n");

		// string == binary value
		Map<Character, String> sortedMap = charPrefixHashMap.entrySet()
				.stream()
				.sorted(Comparator.comparingInt(e -> e.getValue().length()))
				.collect(LinkedHashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), LinkedHashMap::putAll);


		int totalBits = 0;
		for (Map.Entry<Character, String> entry : sortedMap.entrySet()) {
			String keyString = entry.getKey() == '\n' ? "\\n" : entry.getKey().toString();
			sb.append("'" + keyString + "' has prefix code " + entry.getValue() + "\n");
			totalBits += entry.getValue().length();
		}
		sb.append("The text requires " + totalBits + " bits.\n");
		return sb.toString();

	}
}

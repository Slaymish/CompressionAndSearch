package test;

import main.HuffmanCoding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanTests {

    private String text;
    private int expectedEncodedLength;
    private String expectedEncodedText;
    private String expectedDecodedText;
    private HuffmanCoding huffman;

    @BeforeEach
    void setUp() {
        text = "AABBCCCCDD\n";
        expectedEncodedLength = 25;
        expectedEncodedText = "1101101111110000101101100"; //Just a possible encoded sequence. It is not the only correct answer!
        expectedDecodedText = "AABBCCCCDD\n";
        huffman = new HuffmanCoding(text);
    }

    @Test
    void testEncode() {
        String encodedText = huffman.encode(text);
        assertEquals(expectedEncodedText, encodedText, "Encoding result is incorrect.");
    }

    @Test
    void testEncodedLength() {
        String encodedText = huffman.encode(text);
        assertEquals(expectedEncodedLength, encodedText.length(), "Encoded length does not match the expected encoded length.");
    }

    @Test
    void testDecode() {
        String encodedText = huffman.encode(text);
        String decodedText = huffman.decode(encodedText);
        assertEquals(expectedDecodedText, decodedText, "Decoding result is incorrect.");
    }
}

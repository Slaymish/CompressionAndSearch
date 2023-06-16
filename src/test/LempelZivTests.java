package test;

import main.algorithms.LempelZiv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LempelZivTests {
    @Test
    void testCompressionAndDecompression() {
        String text1 = "ABABABA,,,...";
        String compressedText1 = LempelZiv.compress(text1);
        String decompressedText1 = LempelZiv.decompress(compressedText1);
        assertEquals(text1, decompressedText1, "Decompressed text does not match the original text.");
    }

    @Test
    void testCompressionAndDecompression2() {
        String text2 = "ABABBAABAAAAB";
        String compressedText2 = LempelZiv.compress(text2);
        String decompressedText2 = LempelZiv.decompress(compressedText2);
        assertEquals(text2, decompressedText2, "Decompressed text does not match the original text.");
    }

    @Test
    void testCompressionAndDecompress3() {
        String text3 = "Hello Hello how are you today";
        String compressedText3 = LempelZiv.compress(text3);
        String decompressedText3 = LempelZiv.decompress(compressedText3);
        assertEquals(text3, decompressedText3, "Decompressed text does not match the original text.");
    }

    @Test
    void testCompressionAndDecompress4() {
        String text4 = "Hellloo, how are you today?";
        String compressedText4 = LempelZiv.compress(text4);
        String decompressedText4 = LempelZiv.decompress(compressedText4);
        assertEquals(text4, decompressedText4, "Decompressed text does not match the original text.");
    }

    @Test
    void testCompressionAndDecompression5() {
        String text = "AABANA NAAABBN,B";
        String compressedText2 = LempelZiv.compress(text);
        String decompressedText2 = LempelZiv.decompress(compressedText2);
        assertEquals(text, decompressedText2, "Decompressed text does not match the original text.");
    }
}

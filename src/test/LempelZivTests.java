package test;

import main.algorithms.LempelZiv;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LempelZivTests {

    private String text1;
    private LempelZiv lempelZiv;

    @BeforeEach
    void setUp() {
        text1 = "ABABABA,,,...";
        lempelZiv = new LempelZiv();
    }

    @Test
    void testCompressionAndDecompression() {
        String compressedText1 = lempelZiv.compress(text1);
        String decompressedText1 = lempelZiv.decompress(compressedText1);
        assertEquals(text1, decompressedText1, "Decompressed text does not match the original text.");
    }
}

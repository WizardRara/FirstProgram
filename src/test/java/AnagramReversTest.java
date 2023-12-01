import org.example.AnagramRevers;
import org.example.ExeptionScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnagramReversTest {
    private static AnagramRevers input;

    @BeforeEach
    public void CreateNewAnagramRevers() {input = new AnagramRevers();}
    @Test
    public void AnagramReversShouldReverseAllLettersCharacters() {
        String expected = "Hello";
        String actual = input.reversedString(expected);
        Assertions.assertEquals("olleH", actual);
    }
    @Test
    public void AnagramReversShouldNotReverseAnyNonLetterCharacters() {
        String expected = "18-85=!?3498";
        String actual = input.reversedString(expected);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void AnagramReversShouldNotHaveExtraCharacters() {
        String expected = "Hello, World!";
        String actual = input.reversedString(expected);
        Assertions.assertEquals("olleH, dlroW!", actual);
    }
    @Test
    public void AnagramReversShouldThrowExeptionScannerIfStringIsEmpty() {
        Assertions.assertThrows(ExeptionScanner.class,() ->{
            input.reversedString("");
        });
    }
    @Test
    public void AnagramReversShouldThrowExeptionScannerIfStringIsNull() {
        Assertions.assertThrows(ExeptionScanner.class,() ->{
            input.reversedString(null);
        });
    }
}

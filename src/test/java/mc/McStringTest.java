package mc;




import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class McStringTest {

    @Test
    public void limitByLength() {
        assertEquals("ConCac", McString.limitByLength("ConCacNe", 6));
        assertNull(McString.limitByLength(null, 6));
    }

    @Test
    void removeLastChar() {
        assertEquals("ConCac", McString.removeLastChar("ConCac1"));
    }

    @Test
    void removeLastChars() {
        assertEquals("ConCac", McString.removeLastChars("ConCac1", 1));
        assertEquals("ConCa", McString.removeLastChars("ConCac1", 2));
        assertEquals("", McString.removeLastChars("", 1));
        assertEquals("", McString.removeLastChars("Avc", 991));
        assertNull(McString.removeLastChars(null, 1));
    }
}
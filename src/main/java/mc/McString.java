package mc;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isBlank;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Log4j2
public final class McString {

    public static String neverNullStr(String s) {
        if (s == null) {
            return "";
        }
        return s;
    }

    public static List<String> extractLines(String s) {
        if (isBlank(s)) {
            return new ArrayList<>();
        }

        String lines[] = s.split("\\r?\\n");
        return new ArrayList<>(Arrays.asList(lines));
    }

    public static String removeLastChar(String str) {
        return removeLastChars(str, 1);
    }

    public static String removeLastChars(String str, int chars) {
        if (str == null || str.isEmpty() || chars < 1) {
            return str;
        }

        if (chars > str.length()) {
            chars = str.length();
        }

        return str.substring(0, str.length() - chars);
    }

    public static String limitByLength(String s, int maxLength) {
        if (s == null || s.length() <= maxLength) {
            return s;
        }

        return s.substring(0, maxLength);
    }

    /**
     * Truncates a string to the number of characters that fit in X bytes avoiding multi byte characters being cut in
     * half at the cut off point. Also handles surrogate pairs where 2 characters in the string is actually one literal
     * character.
     * <p>
     * Based on: http://www.jroller.com/holy/entry/truncating_utf_string_to_the
     */
    public static String limitByUtf8Bytes(String s, int maxBytes) {
        if (s == null) {
            return null;
        }

        Charset charset = Charset.forName("UTF-8");
        CharsetDecoder decoder = charset.newDecoder();
        byte[] bytes = s.getBytes(charset);
        if (bytes.length <= maxBytes) {
            return s;
        }

        // Ensure truncation by having byte buffer = maxBytes
        ByteBuffer bb = ByteBuffer.wrap(bytes, 0, maxBytes);
        CharBuffer cb = CharBuffer.allocate(maxBytes);
        // Ignore an incomplete character
        decoder.onMalformedInput(CodingErrorAction.IGNORE);
        decoder.decode(bb, cb, true);
        decoder.flush(cb);
        return new String(cb.array(), 0, cb.position());
    }
}

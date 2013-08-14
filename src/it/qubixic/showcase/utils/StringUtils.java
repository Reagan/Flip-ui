package it.qubixic.showcase.utils;

import java.util.Vector;
import javax.microedition.lcdui.Font;

public class StringUtils {    
   
    /**
     * Truncates the string to fit the maxWidth. If truncated, an ellipsis "..."
     * is displayed to indicate this.
     *
     * @param str string to be truncated
     * @param font font to be used
     * @param maxWidth max width of the line
     * @return String - truncated string with ellipsis added to end of the
     * string
     */
    public static String truncate(final String text, final Font font,
            final int maxWidth) {
        if (font.stringWidth(text) <= maxWidth) {
            return text;
        }

        final String ELIPSIS = "...";
        int length = text.length();
        int position = 0;
        for (int i = 0; i < length && font.stringWidth(
                text.substring(0, i) + ELIPSIS)
                <= maxWidth; i++) {
            position = i;
        }
        return text.substring(0, position) + ELIPSIS;
    }

    /**
     * Wrap text to multiple lines.
     *
     * @param text
     * @param wrapWidth
     * @param font
     * @return a vector of text lines
     */
    public static Vector wrapTextToWidth(final String text, int wrapWidth,
            final Font font) {
        if (wrapWidth < 20) {
            wrapWidth = 200;
        }

        Vector lines = new Vector();

        int start = 0;
        int position = 0;
        int length = text.length();
        while (position < length - 1) {
            start = position;
            int lastBreak = -1;
            int i = position;
            for (; i < length && font.stringWidth(
                    text.substring(position, i))
                    <= wrapWidth; i++) {
                if (text.charAt(i) == ' ') {
                    lastBreak = i;
                } else if (text.charAt(i) == '\n') {
                    lastBreak = i;
                    break;
                }
            }
            if (i == length) {
                position = i;
            } else if (lastBreak <= position) {
                position = i;
            } else {
                position = lastBreak;
            }

            lines.addElement(text.substring(start, position));

            if (position == lastBreak) {
                position++;
            }
        }
        return lines;
    }

    /**
     * Concatenates an int and a string
     * @param value int value to be used for the concatenation
     * @param string String value to be used for the concatenation
     * @return A string that has the integer and string concatenated
     */
    public static String concatIntAndString(int value, String string) {
        if (value == 0 || value > 1) {
            return value + " " + string + "s";
        }
        return value + "  " + string;
    }
    
    /**
     * This method validates a string to ensure that 
     * it has the supported formats for a URL
     * @param URL URL to be verified 
     * @return whether the URL supplied has the structure 
     * required for a URL
     */
    public static boolean validateURL(String URL) {
        if (URL.startsWith("/") || URL.startsWith("http:")
                || URL.startsWith("https:")) {
            return true ;
        }
        return false ;
    }
}

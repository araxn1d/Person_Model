/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.infrastructure.convertors;

/**
 *
 * @author Jeka
 */
public class ByteConverter {

    private ByteConverter() {
    }

    /**
     * Converts int to the byte array
     *
     * @param value - number to convert
     * @return - converting array of byte
     */
    public static final byte[] IntToByteArray(int value) {
        return new byte[]{
                    (byte) (value >>> 24),
                    (byte) (value >>> 16),
                    (byte) (value >>> 8),
                    (byte) value};
    }

    /**
     * Convert byte array to the int value
     *
     * @param b - array to convert
     * @return - new int
     */
    public static final int ByteArrayToInt(byte[] b) {
        return (b[0] << 24)
                + ((b[1] & 0xFF) << 16)
                + ((b[2] & 0xFF) << 8)
                + (b[3] & 0xFF);
    }
}

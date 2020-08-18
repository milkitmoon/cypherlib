package com.milkit.core.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

public class UTF8Codec {

    public UTF8Codec() {}

    public static byte[] encode(CharSequence string) {
        try {
            ByteBuffer bytes = CHARSET.newEncoder().encode(CharBuffer.wrap(string));
            byte bytesCopy[] = new byte[bytes.limit()];
            System.arraycopy(bytes.array(), 0, bytesCopy, 0, bytes.limit());
            return bytesCopy;
        } catch(CharacterCodingException e) {
            throw new IllegalArgumentException("Encoding failed", e);
        }
    }

    public static String decode(byte bytes[]) {
        try {
            return CHARSET.newDecoder().decode(ByteBuffer.wrap(bytes)).toString();
        } catch(CharacterCodingException e) {
            throw new IllegalArgumentException("Decoding failed", e);
        }
    }

    private static final Charset CHARSET = Charset.forName("UTF-8");
}

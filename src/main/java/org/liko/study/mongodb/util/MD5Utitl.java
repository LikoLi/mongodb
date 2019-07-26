package org.liko.study.mongodb.util;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author liko
 * @Date 2019/7/26
 * @Version 1.0
 * @Description MD5Utitl
 */
public class MD5Utitl {

    protected static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    protected static MessageDigest messageDigest = null;

    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String getMD5String(String str) {
        return getMD5String(str.getBytes());
    }

    public static String getFileMD5String(InputStream fis) throws IOException {
        byte[] buffer = new byte[1024];
        int numRead;
        while ((numRead = fis.read(buffer)) > 0) {
            messageDigest.update(buffer, 0, numRead);
        }
        return bufferToHex(messageDigest.digest());
    }

    private static String getMD5String(byte[] bytes) {
        messageDigest.update(bytes);
        return bufferToHex(messageDigest.digest());
    }

    private static String bufferToHex(byte[] bytes) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte[] bytes, int m, int n) {
        StringBuffer sb = new StringBuffer(2 * n);
        int k = m + n;
        for (int i = m; i < k; i++) {
            appendHexPair(bytes[i], sb);
        }
        return sb.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer sb) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        sb.append(c0);
        sb.append(c1);
    }
}

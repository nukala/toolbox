package org.ravi.jmh;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// TODO: see which of these toHex methods is most efficient.
// https://www.mkyong.com/java/java-how-to-convert-bytes-to-hex/
//  see for benchmark related tests:
// https://www.baeldung.com/java-collections-complexity
public class ToHexBenchmark {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String password = "123456789";

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

        System.out.println(bytesToHex(hashInBytes));
        System.out.println(bytesToHex2(hashInBytes));
        System.out.println(bytesToHex3(hashInBytes));

    }

    private static String bytesToHex(byte[] hashInBytes) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashInBytes.length; i++) {
            sb.append(Integer.toString((hashInBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();

    }

    private static String bytesToHex2(byte[] hashInBytes) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashInBytes.length; i++) {
            String hex = Integer.toHexString(0xff & hashInBytes[i]);
            if (hex.length() == 1) sb.append('0');
            sb.append(hex);
        }
        return sb.toString();

    }

    private static String bytesToHex3(byte[] hashInBytes) {

        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();

    }
}

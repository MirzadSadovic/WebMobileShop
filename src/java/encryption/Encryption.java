package encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Encryption {

    private static MessageDigest messageDigest;

    public static String encrypt(String password) {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            byte[] passBytes = password.getBytes();
            messageDigest.reset();
            byte[] digested = messageDigest.digest(passBytes);
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < digested.length; i++) {
                stringBuilder.append(Integer.toHexString(0xff & digested[i]));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

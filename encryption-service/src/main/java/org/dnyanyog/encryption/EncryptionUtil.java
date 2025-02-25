package org.dnyanyog.encryption;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {
  public static final String Secret_Key = "nNdOpe1rP4OhtnKiFf7smtujfMUgo5LYHAkwmooo790=";
  public static final String Algorithm = "AES";
  public static SecretKey secretKey;
  public static Cipher cipher;

  static {
    byte[] decodedKey = Base64.getDecoder().decode(Secret_Key);

    if (decodedKey.length != 32) {
      throw new IllegalArgumentException("Invalid AES key length. Expected 32 bytes.");
    }
    secretKey = new SecretKeySpec(decodedKey, Algorithm);
    try {
      cipher = Cipher.getInstance(Algorithm);
      cipher.init(Cipher.DECRYPT_MODE, secretKey);
    } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
      e.printStackTrace();
    }
  }

  public static String encrypt(Integer integer) throws Exception {
    byte[] inputBytes = integer.toString().getBytes(StandardCharsets.UTF_8);
    byte[] encryptedData = cipher.doFinal(inputBytes);
    BigInteger bigInt = new BigInteger(1, encryptedData);
    return Base64.getEncoder().encodeToString(encryptedData);
  }

  public static String decrypt(String encryptedData) throws Exception {
    byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
    return new String(decryptedData, StandardCharsets.UTF_8);
  }

  public static SecretKey generateAesKey() {
    KeyGenerator keyGenerator;
    try {
      keyGenerator = KeyGenerator.getInstance("AES");
      keyGenerator.init(256);
      return keyGenerator.generateKey();

    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new RuntimeException("Error generating AES key");
    }
  }
}

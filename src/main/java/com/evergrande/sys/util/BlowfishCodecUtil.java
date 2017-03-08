package com.evergrande.sys.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 *
 * For Java script side demo: refer to
 * http://sladex.org/blowfish.js/
 *
 * use
 * Cipher mode= "ECB"
 * Output type= "HEX"
 *
 *
 * @author Changhua, Wu
 *         Created on: 5/17/16,2:15 PM
 */
public class BlowfishCodecUtil {


    private final static String KEY = "ever3769";
    private final static String CIPHER_MODE_DEFAULT_ECB = "Blowfish";

    public static String encode(String plainText) throws EncoderException {

        SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), "Blowfish");

        try {
            Cipher cipher = Cipher.getInstance( CIPHER_MODE_DEFAULT_ECB );

            if ( cipher == null || key == null) {
                throw new NoSuchAlgorithmException("Invalid key or cypher");
            }
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encryptedData = cipher.doFinal(plainText.getBytes("UTF-8") );

            String hexStr = Hex.encodeHexString(encryptedData);
            return hexStr;
        } catch (Throwable e) {
            throw new EncoderException( e );
        }


    }


    public static String decode(String encryptedHex) throws DecoderException {

        SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), "Blowfish");

        try {
            Cipher cipher = Cipher.getInstance( CIPHER_MODE_DEFAULT_ECB );

            if ( cipher == null || key == null) {
                throw new NoSuchAlgorithmException("Invalid key or cypher");
            }
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] encryptedData = Hex.decodeHex(encryptedHex.toLowerCase().toCharArray() );
            byte[] decryptedData = cipher.doFinal( encryptedData );

            String plainText = new String( decryptedData );
            plainText.trim();
            return plainText;
        } catch (Throwable e) {
            throw new DecoderException( e );
        }

    }

}

package encryption.cipher;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.lang.invoke.MethodHandles;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public final class AesCipher {

    private Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static final String KEY_ALGO = "AES";
    public static final int KEY_BYTES = 16;
    public static final int KEY_BITS = KEY_BYTES * 8;

    private static final Charset CHARSET_ENCODING = Charset.forName("UTF-8");
    private static final String RNG_ALGORITHM = "SHA1PRNG";
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int IV_BYTES = 16;

    private final Cipher cipher;
    private final SecureRandom secureRandom;
    private SecretKeySpec secretKeySpec;

    /**
     * This initialize trhe cipther with proper algorithm and random number generator(RNG)
     * also it seeds the secret key with random bytes.
     * This constructor should be mainly used for encrypting data and make sure user pulls the secret key and store it safely
     * Later while while decryptPropertyValue the ciphertext user has to initialize the class with the same encrypt key.
     *
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     */
    public AesCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        this.secureRandom = SecureRandom.getInstance(RNG_ALGORITHM);
        initKey(secureRandom.generateSeed(KEY_BYTES));
    }

    /**
     * Use this constructor when user has the encrypted value key and he wants
     * to use it to decryptPropertyValue. The property vaue must be encrypted using the same
     * encrypted value.
     *
     * @param secretKey
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     */
    public AesCipher(final String secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException {
        this(Base64.decodeBase64(secretKey));
    }

    public AesCipher(byte[] decodeBase64) throws NoSuchAlgorithmException, NoSuchPaddingException {
        this();
        initKey(decodeBase64);
    }

    private void initKey(final byte[] keyBytes) {
        assert keyBytes.length == KEY_BYTES;
        this.secretKeySpec = new SecretKeySpec(keyBytes.clone(), KEY_ALGO);
    }

    public String getEncryptedKey() {
        return Base64.encodeBase64String(secretKeySpec.getEncoded());
    }

    /**
     * This method is used to encrypt the string to Base64Encoded string
     */
    public String encrypt(char[] charSet) throws InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException {
        return encrypt(new String(charSet));
    }

    public String encrypt(final String encodedString) throws IllegalBlockSizeException,
            BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        IvParameterSpec ivParameterSpec = new IvParameterSpec(secureRandom.generateSeed(IV_BYTES));
        byte[] cipherText = encrypt(encodedString.getBytes(CHARSET_ENCODING), ivParameterSpec);
        byte[] cipherWithIv = new byte[IV_BYTES + cipherText.length];
        System.arraycopy(ivParameterSpec.getIV(), 0, cipherWithIv, 0, IV_BYTES);
        System.arraycopy(cipherText, 0, cipherWithIv, IV_BYTES, cipherText.length);
        return Base64.encodeBase64String(cipherWithIv);
    }

    public String decrypt(final String cipherText) throws InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException {
        byte[] decodeCipherText = Base64.decodeBase64(cipherText);
        return new String(decrypt(Arrays.copyOfRange(decodeCipherText, IV_BYTES, decodeCipherText.length),
                new IvParameterSpec(Arrays.copyOf(decodeCipherText, IV_BYTES))), CHARSET_ENCODING);
    }

    public byte[] encrypt(final byte[] bytes, final IvParameterSpec ivParameterSpec) throws InvalidAlgorithmParameterException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec, secureRandom);
        return cipher.doFinal();
    }

    public byte[] decrypt(final byte[] bytes, final IvParameterSpec ivParameterSpec) throws InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException {
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec, secureRandom);
            return cipher.doFinal(bytes);
        } catch (BadPaddingException ex) {
            String msg = "Bad krypt key was used. Check config for correct or valid KRYPT key.";
            log.error(msg, ex);
            throw new com.aes.encryption.cipher.AesDecryptionError(msg, ex);
        }
    }
}

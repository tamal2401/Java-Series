package encryption.cipher;

import encryption.core.StringEncryptor;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.KeyGenerator;

public class AesMasterEncrypter {
    private static final byte[] RANDOM_BYTES = {(byte)0xe5, (byte)0xcb,(byte)0xc9,(byte)0xff,(byte)0x92,(byte)0x84,
            (byte)0xdb,(byte)0xf6,(byte)0x15,(byte)0xc6,(byte)0x16,(byte)0xad,
            (byte)0x55,(byte)0x17,(byte)0x9b,(byte)0xa1};

    private static AesCipher masterEncypter;
    private AesCipher stringEncrypter;

    public AesMasterEncrypter(final String encryptKey) throws KryptException {
        try{
            stringEncrypter = new AesCipher(masterEncypter.decrypt(encryptKey));
        }catch (Exception e){
            throw new KryptException(e);
        }
    }

    public StringEncryptor getStringEncryptor(){
        return new AESStringEncryptorWrapper(stringEncrypter);
    }

    public static String getEncryptedKey(){
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance(AesCipher.KEY_ALGO);
            keyGen.init(AesCipher.KEY_BITS);
            return masterEncypter.encrypt(
                    Base64.encodeBase64String(keyGen.generateKey().getEncoded())
            );
        }catch (Exception ex){
            throw new KryptException(ex);
        }
    }

    static{
        try{
            masterEncypter = new AesCipher(RANDOM_BYTES);
        } catch (Exception e) {
            throw new KryptException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(getEncryptedKey());
    }
}

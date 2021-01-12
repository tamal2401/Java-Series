package encryption.cipher;


import encryption.core.StringEncryptor;

public class AESStringEncryptorWrapper implements StringEncryptor {

    private static final String ENCRYPTED_VALUE_PREFIX = "KRYPT(";
    private static final String ENCRYPTED_VALUE_SUFFIX = ")";

    private final AesCipher aesCipher;

    public AESStringEncryptorWrapper(AesCipher stringEncrypter) {
        this.aesCipher = stringEncrypter;
    }

    public String getInnerEncryptedValue(final String value){
        return value.substring(ENCRYPTED_VALUE_PREFIX.length(),(value.length()-ENCRYPTED_VALUE_SUFFIX.length()));
    }

    @Override
    public boolean isEncrypted(String value) {
        if(null==value){
            return false;
        }
        final String trimmedValue = value.trim();
        return (trimmedValue.startsWith(ENCRYPTED_VALUE_PREFIX) &&
                trimmedValue.endsWith(ENCRYPTED_VALUE_SUFFIX));
    }

    @Override
    public String encrypt(String value) {
        try{
            this.aesCipher.encrypt(value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public String decrypt(String value) {
        try{
            if(!isEncrypted(value)){
                value = ENCRYPTED_VALUE_PREFIX + value + ENCRYPTED_VALUE_SUFFIX;
            }
            return aesCipher.decrypt(getInnerEncryptedValue(value.trim()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }
}

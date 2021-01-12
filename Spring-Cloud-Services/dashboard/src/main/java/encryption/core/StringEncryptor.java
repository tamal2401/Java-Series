package encryption.core;

public interface StringEncryptor {
    boolean isEncrypted(String value);

    String encrypt(String value);

    String decrypt(String value);
}

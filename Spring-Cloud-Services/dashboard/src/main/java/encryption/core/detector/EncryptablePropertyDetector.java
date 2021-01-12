package encryption.core.detector;

/**
 * Contracts that identifies property with PREFIX: ENC( and SUFFIX: ) nad contains methods that helps to identify
 * if the property is encrpted or not and if encrpted then unwrap the property and returns the encrypt value of the property
 * {@link #isEncrypted(String)} would take the encrypted value as input and return boolean indicating if the value is encrypted or not
 * {@link #unwrapEncryptedValue(String)} would take string as i/p and return the only encrypted value without the prefix and suffix
 */
public interface EncryptablePropertyDetector {

    boolean isEncrypted(String encryptedValue);

    String unwrapEncryptedValue(String encryptedValue);
}

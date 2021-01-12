package encryption.core.resolver;

/**
 * An interface to resolve property values that may be encrypted
 */
public interface EncryptablePropertyResolver {
    /**
     * returns an unencrypted value of the provided value or the same actual value if no
     * encryption detected.
     */
    String resolvePropertyValue(String value);
}

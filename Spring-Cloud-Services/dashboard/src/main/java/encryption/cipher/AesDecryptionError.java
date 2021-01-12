package encryption.cipher;

public class AesDecryptionError extends Error{
    public AesDecryptionError() {
        super();
    }

    public AesDecryptionError(String message) {
        super(message);
    }

    public AesDecryptionError(String message, Throwable cause) {
        super(message, cause);
    }
}

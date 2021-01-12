package encryption.core.detector;

import org.springframework.util.Assert;

public class DefaultPropertyDetector implements EncryptablePropertyDetector {

    private String prefix = "ENC(";
    private String suffix = ")";

    public DefaultPropertyDetector() {
    }

    public DefaultPropertyDetector(String prefix, String suffix){
        Assert.notNull(prefix, "prefix can not be null");
        this.prefix = prefix;
        Assert.notNull(suffix, "suffix can not be null");
        this.suffix = suffix;
    }

    @Override
    public boolean isEncrypted(String encryptedValue) {
        if(null==encryptedValue){
            return false;
        }
        String trimmedValue = encryptedValue.trim();

        return (trimmedValue.startsWith(prefix) && trimmedValue.endsWith(suffix));
    }

    @Override
    public String unwrapEncryptedValue(String encryptedValue) {
        return encryptedValue.substring(prefix.length(), (encryptedValue.length()-suffix.length()));
    }
}

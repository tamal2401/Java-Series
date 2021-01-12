package encryption.core.resolver;


import encryption.core.StringEncryptor;
import encryption.core.detector.DefaultPropertyDetector;
import encryption.core.detector.EncryptablePropertyDetector;
import org.apache.http.util.Asserts;
import org.springframework.util.Assert;


public class DefaultPropertyResolver implements EncryptablePropertyResolver{

    private StringEncryptor stringEncryptor;
    private EncryptablePropertyDetector detector;

    public DefaultPropertyResolver(StringEncryptor stringEncryptor) {
        this(stringEncryptor, new DefaultPropertyDetector());
    }

    public DefaultPropertyResolver(StringEncryptor stringEncryptor, EncryptablePropertyDetector detector) {
        Asserts.notNull(stringEncryptor, "Encrypter can not be null");
        Assert.notNull(detector, "Detector can not be null");
        this.stringEncryptor = stringEncryptor;
        this.detector = detector;
    }

    @Override
    public String resolvePropertyValue(String value) {
        String temp = value;
        if(detector.isEncrypted(temp)){
            return detector.unwrapEncryptedValue(temp.trim());
        }
        return temp;
    }
}

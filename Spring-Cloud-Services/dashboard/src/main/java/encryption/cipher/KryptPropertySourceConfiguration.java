package encryption.cipher;

import encryption.core.StringEncryptor;
import encryption.core.configuration.EncryptablePropertySourceCofigurationSupport;
import encryption.core.detector.DefaultPropertyDetector;
import encryption.core.detector.EncryptablePropertyDetector;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class KryptPropertySourceConfiguration extends EncryptablePropertySourceCofigurationSupport {
    @Override
    public StringEncryptor stringEncryptor(Environment environment) {
        String encryptedKey = environment.getProperty("demo.encrypt.key");
        return new AesMasterEncrypter(encryptedKey).getStringEncryptor();
    }

    @Bean
    @ConditionalOnMissingBean(EncryptablePropertyDetector.class)
    public EncryptablePropertyDetector encryptablePropertyDetector(EnvCopy envCopy, BeanFactory beanFactory){
        String prefix = envCopy.get().resolvePlaceholders("${demo.encrypt.prefix:KRYPT(}");
        String suffix = envCopy.get().resolvePlaceholders("${demo.encrypt.suffix:)}");
        return new DefaultPropertyDetector(prefix, suffix);
    }
}

package encryption.core.configuration;

import encryption.core.StringEncryptor;
import encryption.core.detector.DefaultPropertyDetector;
import encryption.core.detector.EncryptablePropertyDetector;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

@Configuration
public abstract class EncryptablePropertySourceCofigurationSupport {

    public static final String ENCRYPTOR_BEAN_NAME = "stringEncryptor";
    public static final String DETECTOR_BEAN_NAME = "encryptablePropertyDetector";
    public static final String RESOLVER_BEAN_NAME = "encryptablePropertyResolver";

    @Bean
    public static EnableEncryptablePropertiesBeanFactoryPostProcessor enableEncryptablePropertiesBeanFactoryPostProcessor(
            ConfigurableEnvironment environment
    ) {
        boolean proxyPropertySources = environment.getProperty(
                "demo.encrypt.proxyPropertySources", Boolean.TYPE, false
        );
        InterceptionMode interceptionMode = proxyPropertySources ? InterceptionMode.PROXY :
                InterceptionMode.WRAPPER;
        return new EnableEncryptablePropertiesBeanFactoryPostProcessor(environment,
                interceptionMode);
    }

    @Bean
    public static EncryptablePropertySourceBeanFactoryPostProcessor encryptablePropertySourceCofigurationSupport(
            ConfigurableEnvironment environment
    ) {
        return new EncryptablePropertySourceBeanFactoryPostProcessor(environment);
    }

    @Bean(name = ENCRYPTOR_BEAN_NAME)
    @ConditionalOnMissingBean(StringEncryptor.class)
    public abstract StringEncryptor stringEncryptor(Environment environment);

    @Bean(name = DETECTOR_BEAN_NAME)
    @ConditionalOnMissingBean(EncryptablePropertyDetector.class)
    public EncryptablePropertyDetector encryptablePropertyDetector(EnvCopy envCopy, BeanFactory beanFactory) {
        String prefix = envCopy.get().resolvePlaceholders("${demo.encrypt.prefix:ENC(}");
        String suffix = envCopy.get().resolvePlaceholders("${demo.encrypt.suffix:)}");
        return new DefaultPropertyDetector(prefix, suffix);
    }

    @Bean
    EnvCopy getEnvCopy(ConfigurableEnvironment env) {
        return new EnvCopy(env);
    }


    public static class EnvCopy {
        StandardEnvironment copy;

        public EnvCopy(ConfigurableEnvironment env) {
            copy = new StandardEnvironment();
            env.getPropertySources()
                    .forEach(ps -> {
                        PropertySource<?> original = ps instanceof EncryptablePropertySource ?
                                ((EncryptablePropertySource) ps).getDelegate() : ps;
                        copy.getPropertySources().addLast(original);
                    });
        }

        public ConfigurableEnvironment get() {
            return copy;
        }
    }
}

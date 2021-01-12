package encryption.core.configuration;

import com.aes.encryption.core.resolver.EncryptablePropertyResolver;
import encryption.core.configuration.EncryptablePropertySourceCofigurationSupport;
import encryption.core.configuration.EncryptablePropertySourceConverter;
import encryption.core.configuration.InterceptionMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;

import java.lang.invoke.MethodHandles;

public class EnableEncryptablePropertiesBeanFactoryPostProcessor implements BeanFactoryPostProcessor,
        ApplicationListener<ApplicationEvent>, Ordered {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private ConfigurableEnvironment environment;
    private InterceptionMode interceptionMode;

    public EnableEncryptablePropertiesBeanFactoryPostProcessor() {
        this.interceptionMode = InterceptionMode.PROXY;
    }

    public EnableEncryptablePropertiesBeanFactoryPostProcessor(
            ConfigurableEnvironment environment,
            InterceptionMode interceptionMode) {
        this.environment = environment;
        this.interceptionMode = interceptionMode;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("Post-processing property sources instances");
        EncryptablePropertyResolver propertyResolver = beanFactory.getBean(
                EncryptablePropertySourceCofigurationSupport.RESOLVER_BEAN_NAME, EncryptablePropertyResolver.class
        );
        MutablePropertySources propertySources = environment.getPropertySources();
        EncryptablePropertySourceConverter.convertPropertySources(interceptionMode, propertyResolver, propertySources);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        log.info("Application event raised: {}", applicationEvent.getClass().getSimpleName());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}

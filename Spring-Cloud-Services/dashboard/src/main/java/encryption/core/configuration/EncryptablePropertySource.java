package encryption.core.configuration;

import encryption.core.resolver.EncryptablePropertyResolver;
import org.springframework.core.env.PropertySource;

public interface EncryptablePropertySource<T> {

    PropertySource<T> getDelegate();

    default Object getProperty(
            EncryptablePropertyResolver propertyResolver,
            PropertySource<T> source,
            String name) {
        Object value = source.getProperty(name);
        if(value instanceof String){
            String stringVal = String.valueOf(value);
            return propertyResolver.resolvePropertyValue(stringVal);
        }
        return value;
    }
}

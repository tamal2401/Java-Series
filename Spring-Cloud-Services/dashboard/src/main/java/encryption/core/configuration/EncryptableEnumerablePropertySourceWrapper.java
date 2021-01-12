package encryption.core.configuration;


import encryption.core.resolver.EncryptablePropertyResolver;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.util.Assert;

public class EncryptableEnumerablePropertySourceWrapper<T> extends EnumerablePropertySource<T> implements EncryptablePropertySource<T> {

    private final EnumerablePropertySource<T> delegate;
    private final EncryptablePropertyResolver resolver;

    public EncryptableEnumerablePropertySourceWrapper(EnumerablePropertySource<T> delegate, EncryptablePropertyResolver resolver) {
        super(delegate.getName(), delegate.getSource());
        Assert.notNull(delegate, "PropertySource delegate can not be null");
        Assert.notNull(resolver, "PropertySource resolver can not be null");
        this.delegate = delegate;
        this.resolver = resolver;
    }

    @Override
    public PropertySource<T> getDelegate() {
        return this.delegate;
    }

    @Override
    public String[] getPropertyNames() {
        return this.delegate.getPropertyNames();
    }

    @Override
    public Object getProperty(String name) {
        return getProperty(resolver, delegate, name);
    }
}

package encryption.core.configuration;


import encryption.core.annotation.EncryptablePropertySouce;
import encryption.core.annotation.EncryptablePropertySouces;
import encryption.core.resolver.EncryptablePropertyResolver;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.CompositePropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.util.Assert;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class EncryptablePropertySourceBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {

    private final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private ConfigurableEnvironment env;

    public EncryptablePropertySourceBeanFactoryPostProcessor(ConfigurableEnvironment environment) {
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        MutablePropertySources propertySources = env.getPropertySources();
        Stream<AnnotationAttributes> encryptablePropertySources = getEncryptablePropertySourcesMetaData(beanFactory);
        EncryptablePropertyResolver encryptablePropertyResolver = beanFactory.getBean(EncryptablePropertySourceCofigurationSupport.RESOLVER_BEAN_NAME,
                EncryptablePropertyResolver.class);
        List<PropertySourceLoader> propertySourceLoaderList = initPropertyLoader();
        encryptablePropertySources.forEach(ps -> loadEncryptablePropertySource(ps, env, resourceLoader,
                encryptablePropertyResolver, propertySourceLoaderList, propertySources));
    }

    private Stream<AnnotationAttributes> getEncryptablePropertySourcesMetaData(ConfigurableListableBeanFactory beanFactory){
        Stream<AnnotationAttributes> source = getBeanDefinationsForAnnotations(beanFactory, EncryptablePropertySouce.class);
        Stream<AnnotationAttributes> sources = getBeanDefinationsForAnnotations(beanFactory, EncryptablePropertySouces.class)
                .flatMap(map -> Arrays.stream((AnnotationAttributes[])map.get("value")));
        return Stream.concat(source, sources);
    }

    private Stream<AnnotationAttributes> getBeanDefinationsForAnnotations(ConfigurableListableBeanFactory beanFactory,
                                                                          Class<? extends Annotation> annotation){
        return Arrays.stream(beanFactory.getBeanNamesForAnnotation(annotation))
                .map(beanFactory::getBeanDefinition)
                .filter(bd-> bd instanceof AnnotatedBeanDefinition)
                .map(bd -> (AnnotatedBeanDefinition)bd)
                .map(bd -> bd.getMetadata())
                .filter(md -> md.hasAnnotation(annotation.getName()))
                .map(md -> (AnnotationAttributes)md.getAnnotationAttributes(annotation.getName()));
    }

    private List<PropertySourceLoader> initPropertyLoader() {
        return SpringFactoriesLoader.loadFactories(PropertySourceLoader.class, getClass().getClassLoader());
    }

    private void loadEncryptablePropertySource(AnnotationAttributes encryptablePropetiesMetaData,
                                               ConfigurableEnvironment env,
                                               ResourceLoader resourceLoader,
                                               EncryptablePropertyResolver encryptablePropertyResolver,
                                               List<PropertySourceLoader> propertySourceLoaderList,
                                               MutablePropertySources propertySources) {
        PropertySource<?> ps = createPropertySource(encryptablePropetiesMetaData, env, resourceLoader, encryptablePropertyResolver, propertySourceLoaderList);
        propertySources.addLast(ps);
        log.info("");
    }

    private PropertySource<?> createPropertySource(AnnotationAttributes encryptablePropetiesMetaData,
                                                   ConfigurableEnvironment env,
                                                   ResourceLoader resourceLoader,
                                                   EncryptablePropertyResolver encryptablePropertyResolver,
                                                   List<PropertySourceLoader> propertySourceLoaderList) {
        String name = generateName(encryptablePropetiesMetaData.getString("name"));
        String[] locations = encryptablePropetiesMetaData.getStringArray("value");
        boolean ignoreResourceNotFound = encryptablePropetiesMetaData.getBoolean("ignoreResourceNotFound");
        CompositePropertySource compositePropertySource = new CompositePropertySource(name);
        Assert.isTrue(locations.length > 0, "At least one PropertySource(value) location is required.");
        for(String location : locations){
            String resolvedloaction = env.resolveRequiredPlaceholders(location);
            Resource resource = resourceLoader.getResource(resolvedloaction);
            if(!resource.exists()){
                if(!ignoreResourceNotFound){
                    throw new IllegalStateException(String.format(
                            "Encryptable resource %s not found at %s location.",name, resolvedloaction));
                }else{
                    log.info("Ignorable property resource {} not found in location {}", name, resolvedloaction);
                }
            }else{
                String actualName = name + "#" + resolvedloaction;
                loadPropertyResource(propertySourceLoaderList, resource, actualName)
                        .ifPresent(compositePropertySource::addPropertySource);
            }
        }
        return new EncryptableEnumerablePropertySourceWrapper<>(compositePropertySource, encryptablePropertyResolver);
    }

    private Optional<PropertySource<?>> loadPropertyResource(List<PropertySourceLoader> resourceLoader, Resource resource, String actualName) {
        return Optional.of(resource)
                .filter(this::isFile)
                .map(res -> resourceLoader.stream()
                        .filter(psLoader -> canLoadFileExtension(psLoader, resource))
                        .findFirst()
                        .map(loader -> load(loader, actualName, resource))
                        .orElse(null));
    }

    private PropertySource<?> load(PropertySourceLoader loader, String sourceName, Resource resource){
        try{
            // CompositePropertySource is a list of resources.
            CompositePropertySource composite = new CompositePropertySource(sourceName);
            loader.load(sourceName, resource)
                    .forEach(composite::addPropertySource);
            return composite;
        }catch (IOException ex){
            throw  new IllegalArgumentException("Unable to load property", ex);
        }
    }

    private boolean canLoadFileExtension(PropertySourceLoader loader, Resource resource){
        return Arrays.stream(loader.getFileExtensions()).anyMatch(extension -> resource.getFilename().toLowerCase().endsWith(
                "." + extension
        ));
    }

    private boolean isFile(Resource resource){
        return resource!=null &&
                resource.exists() &&
                //resource.isFile() &&
                org.springframework.util.StringUtils.hasText(
                        org.springframework.util.StringUtils.getFilenameExtension(
                                resource.getFilename()));
    }

    private String generateName(String name){
        return !StringUtils.isEmpty(name) ? name : "EncryptablePropertySource#" + System.currentTimeMillis();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

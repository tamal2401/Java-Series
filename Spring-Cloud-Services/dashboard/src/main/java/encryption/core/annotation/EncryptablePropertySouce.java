package encryption.core.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(com.aes.encryption.core.annotations.EncryptablePropertySouces.class)
public @interface EncryptablePropertySouce {

    String name() default "";

    String[] value();

    boolean ignoreResourceNotFound() default false;
}
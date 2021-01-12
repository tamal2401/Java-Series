package encryption.core.annotation;


import encryption.core.annotation.EncryptablePropertySouce;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptablePropertySouces {

    EncryptablePropertySouce[] value();
}

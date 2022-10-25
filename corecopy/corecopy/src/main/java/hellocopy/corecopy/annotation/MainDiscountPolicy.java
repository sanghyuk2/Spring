package hellocopy.corecopy.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

//@Qualifier의 annotation을 가져온다.
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}

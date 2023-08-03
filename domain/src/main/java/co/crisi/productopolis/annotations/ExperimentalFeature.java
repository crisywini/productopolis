package co.crisi.productopolis.annotations;
import java.lang.annotation.*;

/**
 *
 * This element is an experimental feature.  Use with caution.
 * Not used or tested or implemented in the prod env yet!
 * 
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.CONSTRUCTOR, ElementType.LOCAL_VARIABLE, ElementType.PACKAGE,
        ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE, ElementType.TYPE_PARAMETER
})
public @interface ExperimentalFeature {

}

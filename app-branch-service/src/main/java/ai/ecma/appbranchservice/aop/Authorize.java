package ai.ecma.appbranchservice.aop;

import ai.ecma.library.enums.Permission;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
public @interface Authorize {
    Permission[] permissions() default {};
//    RoleType[] roleTypes() default {};
}

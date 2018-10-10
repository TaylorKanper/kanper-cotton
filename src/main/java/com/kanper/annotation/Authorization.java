package com.kanper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 鉴权注解，注解在类上，则类所有方法都要进行token鉴权
 * 注解在方法上，则该方法需要token鉴权
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {
}

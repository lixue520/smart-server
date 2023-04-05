package cn.edu.guet.config;

import java.lang.annotation.*;

/**
 * 操作日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    /**
     * 描述
     */
    String value() default "";

    /**
     * spel 表达式
     */
    String expression() default "";

}
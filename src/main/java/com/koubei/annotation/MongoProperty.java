package com.koubei.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MongoProperty {
	public String name() default "";

	public boolean isObject() default false;
	public Class type() default Object.class;

	public boolean isArray() default false;
}
